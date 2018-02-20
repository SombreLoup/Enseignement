//*****************************************************************************
//										
// Module	: Points3D
//										
// Fichier	: Points3D.cc
//
// Auteur	: Y. LANUEL							
//
// Date creat.	: 21/05/96							
//
// Commentaires	: Implementation de la classe Points3D
//
//
//*****************************************************************************
// Includes
// Classe Points3D
//	Constructeurs et destructeur
//	Accesseurs
//	Methodes
//		Distance()
//		DistanceCarree()
//	Surcharge des operateurs
//	Partie privee
//*****************************************************************************



//*****************************************************************************
//										
// Includes
//										
//*****************************************************************************
#include	"Points3D.h"

#include	<math.h>



//*****************************************************************************
//										
// Classe Points3D
//										
//*****************************************************************************


//*****************************************************************************
//	Constructeurs et destructeur
//*****************************************************************************
Points3D::Points3D()
{
	m[0]	= 0;
	m[1]	= 0;
	m[2]	= 0;
}


Points3D::Points3D(const Points3D& P)
{
	for (int i=0; i<3; i++)
		m[i]	= P.m[i];
}

	
Points3D::~Points3D()
{
}



Points3D::Points3D(double a, double b, double c)
{
	m[0]	= a;
	m[1]	= b;
	m[2]	= c;
}



//*****************************************************************************
//	Accesseurs
//*****************************************************************************

// Inline


//*****************************************************************************
//	Methodes
//*****************************************************************************
double	Points3D::Distance(const Points3D& P)		const
{
	double	x	= m[0]-P.m[0],
		y	= m[1]-P.m[1],
		z	= m[2]-P.m[2];

	return sqrt(x*x+y*y+z*z);
}


double	Points3D::DistanceCarree(const Points3D& P)		const
{
	double	x	= m[0]-P.m[0],
		y	= m[1]-P.m[1],
		z	= m[2]-P.m[2];

	return x*x+y*y+z*z;
}


//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************
ostream& operator << (ostream& s, const Points3D& P)
{
	s << "(" << P.X() << "," << P.Y() << "," << P.Z() << ")";
	
	return s;
}


istream& operator >> (istream& s, Points3D& P)
{
	double	x,y,z;
	char	p1,p2,v1,v2;

	s >> p1 >> x >> v1 >> y >> v2 >> z >> p2;

	P.ModifX(x); P.ModifY(y); P.ModifZ(z);
	
	return s;
}


const Points3D& Points3D::operator = (const Points3D& P)
{
	if (&P != this)
	{
		for (int i=0; i<3; i++)
			m[i]	= P.m[i];
	}
	return *this;
}


Points3D operator * (const Points3D& P, const Matrices4D& M)
{
	static Points3D	R;

	for (int c=0; c<3; c++)
	{
		R.m[c]	= M.m[3][c];	// on suppose P[3] = 1
		for (int l=0; l<3; l++)
			R.m[c]	+= P.m[l]*M.m[l][c];
	}

	return R;
}


Points3D operator * (double coeff , const Points3D & P)
{
	static Points3D R ;
	for (int l=0; l<3; l++)
	{
		R.m[l]	= coeff * P.m[l] ;
	}

	return R;
}


Points3D operator + (const Points3D& P1, const Points3D& P2)
{
	static Points3D	R;

	for (int c=0; c<3; c++)
		R.m[c]	= P1.m[c]+P2.m[c];

	return R;
}


Points3D operator - (const Points3D& P1, const Points3D& P2)
{
	static Points3D	R;

	for (int c=0; c<3; c++)
		R.m[c]	= P1.m[c]-P2.m[c];
	
	return R;
}


Points3D operator + (const Points3D& P, const Vecteurs3D& V)
{
	static Points3D	R;
	
	for (int c=0; c<3; c++)
		R.m[c]	= P.m[c]+V.m[c];
	
	return R;
}


Points3D operator - (const Points3D& P, const Vecteurs3D& V)
{
	static Points3D	R;
	
	for (int c=0; c<3; c++)
		R.m[c]	= P.m[c]-V.m[c];
	
	return R;
}

bool operator == (const Points3D & P1 , const Points3D & P2)
{
	return (	(P1.m[0] == P2.m[0])
	       &&	(P1.m[1] == P2.m[1])
	       &&	(P1.m[2] == P2.m[2])
	       );
}
