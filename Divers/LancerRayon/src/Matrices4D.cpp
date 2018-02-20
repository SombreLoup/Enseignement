//*****************************************************************************
//										
// Module	: Matrices4D
//										
// Fichier	: Matrices4D.cc
//
// Auteur	: Y. LANUEL							
//
// Date creat.	: 21/05/96							
//
// Commentaires	: Implementation de la classe Matrices4D
//
//
//*****************************************************************************
// Includes
// Classe Matrices4D
//	Constructeurs et destructeur
//	Accesseurs
//	Methodes
//		Transposee()
//	Surcharge des operateurs
//*****************************************************************************



//*****************************************************************************
//										
// Includes
//										
//*****************************************************************************
#include	"Matrices4D.h"
#include	"Vecteurs3D.h"

#include	<math.h>



//*****************************************************************************
//										
// Classe Matrices4D
//										
//*****************************************************************************
static void	InitialiseIdentite(double m[][4])
{
	for (int l=0; l<4; l++)
	{
		for (int c=0; c<4; c++)
			m[l][c]	= 0;
		m[l][l]	= 1;
	}
}

//*****************************************************************************
//	Constructeurs et destructeur
//*****************************************************************************

Matrices4D::Matrices4D()
{
	InitialiseIdentite(m);
}


Matrices4D::Matrices4D(const Matrices4D& M)
{
	for (int l=0; l<4; l++)
		for (int c=0; c<4; c++)
			m[l][c]	= M.m[l][c];
}


Matrices4D::~Matrices4D()
{
}


//*****************************************************************************
//	Accesseurs
//*****************************************************************************

// Inline


//*****************************************************************************
//	Methodes
//*****************************************************************************
Matrices4D 	Matrices4D::Transposee()		const
{
	Matrices4D	R;

	for (int l=0; l<4; l++)
		for (int c=0; c<4; c++)
			R.m[l][c]	= m[c][l];

	return R;
}





//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************
ostream& operator << (ostream& s, const Matrices4D& M)
{
	s << "M[0,0]=" << M.M(0,0) << "\tM[0,1]=" << M.M(0,1) << "\tM[0,2]=" << M.M(0,2) << "\tM[0,3]=" << M.M(0,3) << endl;
	s << "M[1,0]=" << M.M(1,0) << "\tM[1,1]=" << M.M(1,1) << "\tM[1,2]=" << M.M(1,2) << "\tM[1,3]=" << M.M(1,3) << endl;
	s << "M[2,0]=" << M.M(2,0) << "\tM[2,1]=" << M.M(2,1) << "\tM[2,2]=" << M.M(2,2) << "\tM[2,3]=" << M.M(2,3) << endl;
	s << "M[3,0]=" << M.M(3,0) << "\tM[3,1]=" << M.M(3,1) << "\tM[3,2]=" << M.M(3,2) << "\tM[3,3]=" << M.M(3,3) << endl;

	return s;
}

bool operator == (const Matrices4D & M1 , const Matrices4D & M2)
{
	bool vrai (true) ;
	int l (0) ;
	while (vrai && l<4)
	{
		int c (0) ;
		while (vrai && c<4)
		{
			vrai = (M1.m[l][c] == M2.m[l][c]) ;
			c++ ;
		}
		l++ ;
	}
	return vrai ;
}

bool operator != (const Matrices4D & M1 , const Matrices4D & M2)
{
	return !(M1==M2);
}

const Matrices4D& Matrices4D::operator = (const Matrices4D& M)
{
	if (&M != this)
	{
		for (int l=0; l<4; l++)
			for (int c=0; c<4; c++)
				m[l][c]	= M.m[l][c];
	}
	return *this;
}


Matrices4D operator * (const Matrices4D& M1, const Matrices4D& M2)
{
	static Matrices4D	R;

	for (int l=0; l<4; l++)
		for (int c=0; c<4; c++)
		{
			R.m[l][c]	= 0;
			for (int k=0; k<4; k++)
				R.m[l][c]	+= M1.m[l][k]*M2.m[k][c];
		}

	return R;
}










