//*****************************************************************************
// 
// Module	: Vecteurs3D
// 
// Fichier	: Vecteurs3D.h
// 
// Auteur	: Y. LANUEL							
// 
// Date creat.	: 18/05/96							
// 
// Commentaires	: Interface de la classe Vecteurs3D.
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
//		VecteurHt(T,ni,nt)
//	Surcharge des operateurs
//		Operations Vecteurs/Vecteurs
//		Operations Vecteurs/double
//		Operations Vecteurs/Matrices
//		Operations Vecteurs/Points
//	Partie privee
//
//*****************************************************************************
//*****************************************************************************



#ifndef __Vecteurs3D__h__
#define	__Vecteurs3D__h__	1



//*****************************************************************************
// 
// Includes
// 
//*****************************************************************************
#include	<iostream>
using namespace std;


#include	"Points3D.h"
#include	"Matrices4D.h"



//*****************************************************************************
//
// Classe Vecteurs3D
//
//*****************************************************************************
class Vecteurs3D
{

public:


//*****************************************************************************
//	Constructeurs et destructeur
//*****************************************************************************
	Vecteurs3D();
	Vecteurs3D(const Vecteurs3D&);
	~Vecteurs3D();

	Vecteurs3D(double, double, double);
	Vecteurs3D(const Points3D&, const Points3D&);


//*****************************************************************************
//	Accesseurs
//*****************************************************************************
	inline	double	X()	const;
	inline	double	Y()	const;
	inline	double	Z()	const;


	inline	void	ModifX(double);
	inline	void	ModifY(double);
	inline	void	ModifZ(double);


//*****************************************************************************
//	Methodes
//*****************************************************************************


	double		Norme()					const;

	double		NormeCarree()			const;

	Vecteurs3D	VecteurNorme()				const;

	double		EstColineaire (const Vecteurs3D &V) const ; // faux (0) ou k tq *this = k.V

//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************
	friend	ostream&    operator << (ostream&, const Vecteurs3D&);

	const	Vecteurs3D& operator =  (const Vecteurs3D&);

	const	Vecteurs3D& operator +=	(const Vecteurs3D&);
	const	Vecteurs3D& operator -=	(const Vecteurs3D&);
	const	Vecteurs3D& operator *=	(double);
	const	Vecteurs3D& operator /=	(double);

friend	bool		operator == (const Vecteurs3D&, const Vecteurs3D&);
friend	bool		operator != (const Vecteurs3D&, const Vecteurs3D&);


//*****************************************************************************
//		Operations Vecteurs/Vecteurs
//*****************************************************************************
friend	Vecteurs3D   operator - (const Vecteurs3D&);

friend	double	   operator * (const Vecteurs3D&, const Vecteurs3D&);

friend	Vecteurs3D   operator + (const Vecteurs3D&, const Vecteurs3D&);

friend	Vecteurs3D   operator - (const Vecteurs3D&, const Vecteurs3D&);

friend	Vecteurs3D   operator & (const Vecteurs3D&,const Vecteurs3D&);

friend	Vecteurs3D operator % (const Vecteurs3D& V1, const Vecteurs3D& V2);

//*****************************************************************************
//		Operations Vecteurs/double
//*****************************************************************************
	friend	Vecteurs3D operator * (double, const Vecteurs3D&);

	friend	Vecteurs3D operator / (const Vecteurs3D&, double);


//*****************************************************************************
//		Operations Vecteurs/Matrices
//*****************************************************************************
	friend	Vecteurs3D operator * (const Vecteurs3D&, const Matrices4D&);

//*****************************************************************************
//		Operations Vecteurs/Points
//*****************************************************************************
	friend	Points3D   operator + (const Points3D&, const Vecteurs3D&);
	friend	Points3D   operator - (const Points3D&, const Vecteurs3D&);

//*****************************************************************************
//	Partie privee
//*****************************************************************************
public:

	double	m[3];



	Vecteurs3D	VecteurReflechi(
					const Vecteurs3D& N)	const;

	Vecteurs3D	VecteurTransmis(
					const Vecteurs3D& N,
					double ni,
					double nt	)	const;

	Vecteurs3D	VecteurH(const Vecteurs3D& L)	const;


	Vecteurs3D	VecteurHt(
					const Vecteurs3D& T,
				 	double ni,
				  	double nt	)	const;
};


#include	"Vecteurs3D.inl"



#endif	__Vecteurs3D__h__
