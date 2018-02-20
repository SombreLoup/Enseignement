//*****************************************************************************
//										
// Module	: Vecteurs3D
//										
// Fichier	: Vecteurs3D.cc
//
// Auteur	: Y. LANUEL							
//
// Date creat.	: 21/05/96							
//
// Commentaires	: Implementation de la classe Vecteurs3D
//
//
//*****************************************************************************
// Includes
// Classe Vecteurs3D
//	Constructeurs et destructeur
//	Accesseurs
//	Methodes
//		Norme()
//		NormeCarree()
//		VecteurNorme()
//		VecteurReflechi(N)
//		VecteurTransmis(N,ni,nt)
//		VecteurH(N)
//		ecteurHt(T,ni,nt)
//	Surcharge des operateurs
//		Operations Vecteurs/Vecteurs
//		Operations Vecteurs/double
//		Operations Vecteurs/Matrices
//		Operations Vecteurs/Points
//
//*****************************************************************************

//*****************************************************************************



//*****************************************************************************
//										
// Includes
//										
//*****************************************************************************
#include	"Vecteurs3D.h"

#include	"MacroMath.h"



//*****************************************************************************
//
// Classe Vecteurs3D
//
//*****************************************************************************



//*****************************************************************************
//	Constructeurs et destructeur
//*****************************************************************************

Vecteurs3D::Vecteurs3D()
{
	for (int i=0; i<3; i++)
		m[i]	= 0;
}


Vecteurs3D::Vecteurs3D(const Vecteurs3D& V)
{
	for (int i=0; i<3; i++)
		m[i]	= V.m[i];
}

	
Vecteurs3D::~Vecteurs3D()
{
}




Vecteurs3D::Vecteurs3D(double a, double b, double c)
{
	m[0]	= a;
	m[1]	= b;
	m[2]	= c;
}


Vecteurs3D::Vecteurs3D(const Points3D& P1, const Points3D& P2)
{
	m[0]	= P2.X()-P1.X();
	m[1]	= P2.Y()-P1.Y();
	m[2]	= P2.Z()-P1.Z();
}



//*****************************************************************************
//	Accesseurs
//*****************************************************************************

// Inline


//*****************************************************************************
//	Methodes
//*****************************************************************************

double	Vecteurs3D::Norme()		const
{
	return sqrt(m[0]*m[0]+m[1]*m[1]+m[2]*m[2]);
}


double	Vecteurs3D::NormeCarree()	const
{
	return m[0]*m[0]+m[1]*m[1]+m[2]*m[2];
}


Vecteurs3D	Vecteurs3D::VecteurNorme()	const
{
	static	Vecteurs3D	V_Resultat;

	double	norme	= Norme();

	V_Resultat.m[0] = m[0]/norme;
	V_Resultat.m[1] = m[1]/norme;
	V_Resultat.m[2] = m[2]/norme;


	return V_Resultat;
}


Vecteurs3D	Vecteurs3D::VecteurReflechi(const Vecteurs3D& N)	const
{
	static	Vecteurs3D	V_Resultat;

	const Vecteurs3D&	V	= *this;
	double			k	= 2*(N*V);

	V_Resultat = (k*N)-V;


	return V_Resultat;
}


Vecteurs3D	Vecteurs3D::VecteurTransmis(	const Vecteurs3D& N,
						double ni,
						double nt	)	const
{
static	Vecteurs3D	V_Resultat;

	const Vecteurs3D&	V	= *this;

static	double		Produit;
static	double		nr,a,Cn;


	Produit = V * N;

	nr = ni/nt;

	a = 1-nr*nr*(1-Produit*Produit);
	if (a<0)
	{
		V_Resultat	= Vecteurs3D(0,0,0);
	}
	else
	{
		Cn = nr*Produit - sqrt(a);


		V_Resultat	= Cn*N - nr*V;

	}



	return V_Resultat.VecteurNorme();
}


Vecteurs3D	Vecteurs3D::VecteurH( const Vecteurs3D& L)		const
{
	static	Vecteurs3D	V_Resultat;

	const Vecteurs3D&	V	= *this;


	V_Resultat = (L+V).VecteurNorme();


	return V_Resultat;
}


Vecteurs3D	Vecteurs3D::VecteurHt(	const Vecteurs3D& T,
					double ni,
					double nt	)		const
{
	static	Vecteurs3D	V_Resultat;

	const Vecteurs3D&	V	= *this;


	double		VxT	= -(V*T);
	double		d;

	if (ni<nt)
	{
		if (VxT >= ni/nt)
		{
			d	= (nt/ni)-1;
			V_Resultat = (-((V+T)/d + T)).VecteurNorme();
		}
		else
			V_Resultat	= Vecteurs3D();
	}
	else
	{
		if (VxT >= nt/ni)
		{
			d	= (ni/nt)-1;
			V_Resultat = (((V+T)/d + V)).VecteurNorme();
		}
		else
			V_Resultat	= Vecteurs3D();
	}

	return V_Resultat;
}


double Vecteurs3D::EstColineaire (const Vecteurs3D &V) const
// retourne faux (0) si les vecteurs ne sont pas colinéaires. S'ils le sont,
// le résultat est k tel que *this = k.V
// k peut donc être négatif
{	const Vecteurs3D &W = *this & V ;

	// Christian : le 22.02.2000, j'augmente les epsilons de EPS8 a EPS4
   // Ca paraît énorme mais il faut ça, même si V et *this sont normés !
	if (Egal (0,W.X(),EPS4) && Egal (0,W.Y(),EPS4) && Egal (0,W.Z(),EPS4))
		return (*this * V) / V.NormeCarree () ;
	else
		return 0 ;
}


//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************
ostream& operator << (ostream& s, const Vecteurs3D& V)
{
	s << "V.X=" << V.X() << "\tV.Y=" << V.Y() << "\tV.Z=" << V.Z();

	return s;
}


const Vecteurs3D& Vecteurs3D::operator = (const Vecteurs3D& V)
{
	if (&V != this)
	{
		for (int i=0; i<3; i++)
			m[i]	= V.m[i];
	}
	return *this;
}


const Vecteurs3D& Vecteurs3D::operator += (const Vecteurs3D& V)
{
	for (int i=0; i<3; i++)
		m[i]	+= V.m[i];

	return *this;
}


const Vecteurs3D& Vecteurs3D::operator -= (const Vecteurs3D& V)
{
	for (int i=0; i<3; i++)
		m[i]	-= V.m[i];

	return *this;
}


const Vecteurs3D& Vecteurs3D::operator *= (double t)
{
	for (int i=0; i<3; i++)
		m[i]	*= t;

	return *this;
}


const Vecteurs3D& Vecteurs3D::operator /= (double t)
{
	for (int i=0; i<3; i++)
		m[i]	/= t;

	return *this;
}


bool	operator == (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	// Christian : le 22.02.2000, j'augmente les epsilons de EPS8 a EPS6
	return	Egal(V1.X(), V2.X(), EPS6)	&&
        	Egal(V1.Y(), V2.Y(), EPS6)	&&
                Egal(V1.Z(), V2.Z(), EPS6);
}


bool	operator != (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	return	!(V1==V2);
}


Vecteurs3D operator - (const Vecteurs3D& V)
{
	static	Vecteurs3D	V_Resultat;


	V_Resultat.m[0]	= -V.m[0];
	V_Resultat.m[1]	= -V.m[1];
	V_Resultat.m[2]	= -V.m[2];


	return V_Resultat;
}


double operator * (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	return (V1.m[0]*V2.m[0]+V1.m[1]*V2.m[1]+V1.m[2]*V2.m[2]);
}


Vecteurs3D operator + (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	static	Vecteurs3D	V_Resultat;


	V_Resultat.m[0]	= V1.m[0]+V2.m[0];
	V_Resultat.m[1]	= V1.m[1]+V2.m[1];
	V_Resultat.m[2]	= V1.m[2]+V2.m[2];


	return V_Resultat;
}


Vecteurs3D operator - (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	static	Vecteurs3D	V_Resultat;


	V_Resultat.m[0]	= V1.m[0]-V2.m[0];
	V_Resultat.m[1]	= V1.m[1]-V2.m[1];
	V_Resultat.m[2]	= V1.m[2]-V2.m[2];


	return V_Resultat;
}


Vecteurs3D operator % (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	static	Vecteurs3D	V_Resultat;


	V_Resultat.m[0]	= V1.m[0]*V2.m[0];
	V_Resultat.m[1]	= V1.m[1]*V2.m[1];
	V_Resultat.m[2]	= V1.m[2]*V2.m[2];


	return V_Resultat;
}


Vecteurs3D operator & (const Vecteurs3D& V1, const Vecteurs3D& V2)
{
	static	Vecteurs3D	V_Resultat;


	V_Resultat.m[0]	= V1.m[1]*V2.m[2]-V1.m[2]*V2.m[1];
	V_Resultat.m[1]	= V1.m[2]*V2.m[0]-V1.m[0]*V2.m[2];
	V_Resultat.m[2]	= V1.m[0]*V2.m[1]-V1.m[1]*V2.m[0];


	return V_Resultat;
}




Vecteurs3D operator * (const Vecteurs3D& V, const Matrices4D& M)
{
	static	Vecteurs3D	V_Resultat;


	for (int c=0; c<3; c++)
	{
		V_Resultat.m[c]	= 0;
		for (int l=0; l<3; l++)
			V_Resultat.m[c]	+= V.m[l]*M.m[l][c];
	}

	return V_Resultat;
}


Vecteurs3D operator * (double t, const Vecteurs3D& V)
{
	static	Vecteurs3D	V_Resultat;


	for (int c=0; c<3; c++)
		V_Resultat.m[c]	= t*V.m[c];

	return V_Resultat;
}


Vecteurs3D operator / (const Vecteurs3D& V, double t)
{
	static	Vecteurs3D	V_Resultat;


	for (int c=0; c<3; c++)
		V_Resultat.m[c]	= V.m[c]/t;

	return V_Resultat;
}
