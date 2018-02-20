//*****************************************************************************
// 
// Module	: Matrices4D
// 
// Fichier	: Matrices4D.h
// 
// Auteur	: Y. LANUEL							
// 
// Date creat.	: 18/05/96							
// 
// Commentaires	: Interface de la classe Matrices4D.
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
//	Partie privee
//*****************************************************************************



#ifndef __Matrices4D__h__
#define	__Matrices4D__h__	1



//*****************************************************************************
// 
// 1. Includes
// 
//*****************************************************************************
#include	<iostream>
using namespace std;


class	Points3D;
class	Vecteurs3D;



//*****************************************************************************
//
// 2. Classe Matrices4D
//
//*****************************************************************************
class Matrices4D
{

public:


//*****************************************************************************
//	2.1 Constructeurs et destructeur
//*****************************************************************************
	Matrices4D();
	Matrices4D(const Matrices4D&);
	~Matrices4D();

//*****************************************************************************
//	Accesseurs
//*****************************************************************************
inline 	double		M(int L, int C)	const;
inline	void			ModifM(int L, int C, double Val);


//*****************************************************************************
//	Methodes
//*****************************************************************************
	Matrices4D	Transposee()	const;


//*****************************************************************************
//	Surcharge des operateurs
//*****************************************************************************

const		Matrices4D& 			operator =  (const Matrices4D&);

friend	bool operator == (const Matrices4D & M1 , const Matrices4D & M2);


friend	Points3D 		operator * (const Points3D&, const Matrices4D&);
friend	Vecteurs3D 	operator * (const Vecteurs3D&, const Matrices4D&);
friend	Matrices4D 	operator * (const Matrices4D&,const Matrices4D&);


//*****************************************************************************
//	Partie privee
//*****************************************************************************
private:

	double	m[4][4];

};


#include	"Matrices4D.inl"


#endif	__Matrices4D__h__
