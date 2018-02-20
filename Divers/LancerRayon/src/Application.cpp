//---------------------------------------------------------------------------

#include "Points3D.h"
#include "Matrices4D.h"
#include "Spheres.h"
#include "Source.h"
#include "MacroMath.h"
#include "bitmap_image.hpp"

#define	LARGEUR	400
#define	HAUTEUR	400
bitmap_image image(LARGEUR,HAUTEUR);


//---------------------------------------------------------------------------
Points3D		Observateur;
Vecteurs3D     VecteurVisee;
Vecteurs3D	VecteurDebout;
double		DistanceFocale;
Matrices4D     MatricePerspective;

Spheres		Scene[100];
int			NbObjets;
Source		Studio[100];
int			NbSources;
Vecteurs3D	CouleurFond(12/255.,192/255.,224/255.);
//Vecteurs3D	CouleurFond(0.2,0.2,0.2);

Materiau		Bronze(0.1,0.3,1,0,Vecteurs3D(113/255.,69/255.,4/255.), Vecteurs3D(183/255., 176/255., 98/255.),10);
Materiau		PlastiqueBleu(0.05,0.1,0.8,0.99,Vecteurs3D(50/255.,50/255.,250/255.), Vecteurs3D(160/255., 160/255., 160/255.),25);
Materiau		Acier(0.1,0.3,1,0,Vecteurs3D(0.38,0.38,0.38), Vecteurs3D(141/255.,103/255.,33/255.),10);
Materiau		Obsidienne(0.1,0,1,0,Vecteurs3D(0.38,0.38,0.38), Vecteurs3D(45/255.,191/255.,191/255.),10);



void	InitialiserTransformationPerspective()
{
	Points3D		P = Observateur;

	Vecteurs3D	X,Y,Z;

     Z = VecteurVisee;
     Y = VecteurDebout;
     X = Y&Z;

     Matrices4D	M;

     M.ModifM(0,0,X.X()); 	M.ModifM(0,1,Y.X());	M.ModifM(0,2,Z.X());	M.ModifM(0,3,P.X());
     M.ModifM(1,0,X.Y());	M.ModifM(1,1,Y.Y());	M.ModifM(1,2,Z.Y());	M.ModifM(1,3,P.Y());
     M.ModifM(2,0,X.Z());	M.ModifM(2,1,Y.Z());	M.ModifM(2,2,Z.Z());	M.ModifM(1,3,P.Z());

     M.ModifM(3,3,1);

     MatricePerspective = M;
}

void	Initialisations()
{
     Observateur	= Points3D(0,0,0);
     VecteurVisee	= Vecteurs3D(0,0,1).VecteurNorme();
     VecteurDebout	= Vecteurs3D(0,1,0);
     DistanceFocale	= 450;

     InitialiserTransformationPerspective();

 	 Scene[0] = Spheres(Points3D(0,-2,20),5, PlastiqueBleu);
 	 Scene[1] = Spheres(Points3D(-5,5,25),4, Acier);
	 Scene[2] = Spheres(Points3D(5,5,30),5, Obsidienne);
     NbObjets = 3;

     Studio[0] = Source(Points3D(40,40,-30), Vecteurs3D(1,1,1));
     Studio[1] = Source(Points3D(-40,40,-30), Vecteurs3D(1,1,1));
     NbSources = 2;
}


void	EcrireRGB(int x, int y, const Vecteurs3D &V)
{
	unsigned int r,v,b;

     if (V.X() < 0)
     	r = 0;
     else if (V.X()>1)
     	r = 255;
     else
     	r = (unsigned int)(V.X()*255);

     if (V.Y() < 0)
     	v = 0;
     else if (V.Y()>1)
     	v = 255;
     else
     	v = (unsigned int)(V.Y()*255);

     if (V.Z() < 0)
     	b = 0;
     else if (V.Z()>1)
     	b = 255;
     else
     	b = (unsigned int)(V.Z()*255);

     image.set_pixel(x,y,r,v,b);

}

// Recherche de l'intersection la plus proche de l'origine du rayon
void IntersectionScene(const Points3D& O, const Vecteurs3D &V, Points3D& PMin, Vecteurs3D &NMin, Materiau& MatMin, bool &Trouve)
{
     Points3D		P;
     Vecteurs3D	N;
     Materiau		Mat;
     bool			B, Premier = true;

     Trouve = false;

     for (int i=0; i<NbObjets; i++)
     {
    	 Scene[i].Intersection(O,V,P,N,Mat,B);
         if (B && (Premier || (O.DistanceCarree(P) < O.DistanceCarree(PMin))))
         {
        	 PMin = P;
             NMin = N;
             MatMin = Mat;
             Premier = false;
             Trouve = true;
          }
     }
}

// Recherche d'une intersection entre l'origine du rayon et la source
void IntersectionOmbrageScene(const Points3D& P, const Points3D &L, bool &Trouve)
{
     Points3D		I;
     Vecteurs3D	N;
     Materiau		Mat;

     Vecteurs3D	PL = Vecteurs3D(P,L).VecteurNorme();
     double		DPL = P.DistanceCarree(L);

     Trouve = false;

     for (int i=0; !Trouve && (i<NbObjets); i++)
     {
    	 Scene[i].Intersection(P,PL,I,N,Mat,Trouve);
         Trouve = Trouve && (P.DistanceCarree(I)<DPL);
     }
}


// Traitement d'un rayon quelconque (primaire ou secondaire)
void LancerUnRayon(const Points3D& Origine, const Vecteurs3D& Rayon, Vecteurs3D& Couleur, int Niveau)
{
    Couleur = Vecteurs3D(0,0,0);

	if (Niveau>2)
    {
          return;
    }


	Points3D		P;
    Vecteurs3D		N, R, L;
    Materiau		Mat;
    bool 			Trouve;

     // Recherche d'une intersection avec les objets de la sc�ne
	IntersectionScene(Origine, Rayon, P, N, Mat, Trouve);

	// Si intersection alors Eclairement
	if (Trouve)
	{
		bool	TrouveOmbre;

        //   Pour chaque source
        for (int s=0; s<NbSources; s++)
        {
        	//	Ajoute de la composante ambiante
          	Couleur = Couleur+Mat.ka*Studio[s].Couleur()%Mat.CDif;

          	//	Construction du vecteur norm� L, celui qui pointe la source
            L = Vecteurs3D(P,Studio[s].Position()).VecteurNorme();

          	// 	Construction du rayon r�flechi
            R = L.VecteurNorme().VecteurReflechi(N);

          	//	Recherche d'une intersection avec les objets de la sc�ne
            IntersectionOmbrageScene(P, Studio[s].Position(), TrouveOmbre);

          	//	Si pas d'intersection (donc �clair�)
            if (!TrouveOmbre)
            {
          		//	Ajoute de la composante diffuse et sp�culaire
				double		N_L = MAX(0,N*L.VecteurNorme());
               	double		R_V = MAX(-Rayon*R,0);
				//	Mod�le de Phong
               	Couleur = Couleur + Studio[s].Couleur()%(Mat.kd*N_L*Mat.CDif + Mat.ks*pow(R_V,Mat.Refr)*Mat.CSpec);
          	}
        }

        // Eclairement r�fl�chis
        if (Sup(Mat.ks,0,EPS8))
        {
        	Vecteurs3D	CouleurReflechie;

            LancerUnRayon(P, -Rayon.VecteurReflechi(N), CouleurReflechie, Niveau+1);

            Couleur += Mat.ks*CouleurReflechie;
        }

        // Eclairement transmis (pas de d�viation)
        if (Sup(Mat.kt,0,EPS8))
        {
        	Vecteurs3D	CouleurTransmise;

            LancerUnRayon(P, Rayon, CouleurTransmise, Niveau+1);

            Couleur += Mat.kt*CouleurTransmise;
        }
	}
}




//---------------------------------------------------------------------------
int main()
{
	Vecteurs3D	Rayon;
     Vecteurs3D	Couleur;
     Materiau		Mat;
     Points3D		P;
     Vecteurs3D	N;


	Initialisations();

	// Balayage de l'image
	for (int y= -(HAUTEUR/2); y<(HAUTEUR/2); y++)
          for (int x=-(LARGEUR/2); x<(LARGEUR/2); x++)
          {
			// Construction du rayon dans le rep�re de l'observateur
			Rayon = Vecteurs3D(x,y,DistanceFocale).VecteurNorme();

               // Transformation du rayon dans le rep�re du monde
               Rayon = Rayon*MatricePerspective;

               // Lancement du rayon primaire
               LancerUnRayon(Observateur, Rayon, Couleur, 0);

               // Affichage de la couleur
              EcrireRGB(x+(LARGEUR/2), y+(HAUTEUR/2),Couleur);
          }

	image.save_image("MonImage.bmp");

	return 0;
}
//---------------------------------------------------------------------------

