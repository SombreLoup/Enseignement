//*****************************************************************************
// 
// Module	: Points3D
// 
// Fichier	: Points3D.h
// 
// Auteur	: Y. LANUEL							
// 
// Date creat.	: 18/05/96							
// 
// Commentaires	: Interface de la classe Points3D.
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


#ifndef __Points3D__h__
#define	__Points3D__h__	1



//*****************************************************************************
// 
// Includes
// 
//*****************************************************************************
#include	<iostream>
using namespace std;


#include	"Vecteurs3D.h"
#include	"Matrices4D.h"


//*****************************************************************************
//
// Classe Points3D
//
//*****************************************************************************
class Points3D
{

public:


//*****************************************************************************
//	Constructeurs et destructeur
//*****************************************************************************
	Points3D();
	Points3D(const Points3D&);
	~Points3D();


	Points3D(double, double, double);


//*****************************************************************************
//	Accesseurs
//*****************************************************************************
	inline double	X()	const;
	inline double	Y()	const;
	inline double	Z()	const;


	inline void	ModifX(double);
	inline void	ModifY(double);
	inline void	ModifZ(double);


//*****************************************************************************
//	Methodes
//*****************************************************************************
	double	Distance(const Points3D&)			const;
	double	DistanceCarree(const Points3D&)			const;


//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************
	friend	ostream&  operator << (ostream&, const Points3D&);
	friend	istream&  operator >> (istream&, Points3D&);
	const	Points3D& operator =  (const Points3D&);


	friend	Points3D operator * (const Points3D&, const Matrices4D&);
	friend	Points3D operator * (const Matrices4D&, const Points3D&);

	friend  	Points3D operator * (double , const Points3D &) ;

	friend	Points3D operator + (const Points3D&, const Points3D&);
	friend	Points3D operator - (const Points3D&, const Points3D&);

	friend	Points3D operator + (const Points3D&, const Vecteurs3D&);
	friend	Points3D operator - (const Points3D&, const Vecteurs3D&);

	friend bool operator == (const Points3D& , const Points3D&) ;


//*****************************************************************************
//	Partie privee
//*****************************************************************************
private:

	double	m[3];

};



#include	"Points3D.inl"



#endif	__Points3D__h__
