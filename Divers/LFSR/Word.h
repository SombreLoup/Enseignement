/*
 * Word.h
 *
 *  Created on: 20 oct. 2009
 *      Author: yann
 */

#ifndef WORD_H_
#define WORD_H_

#include	<string>
#include	<iostream>
#include	<sstream>
#include	<bitset>
using namespace std;



/**
 * Word
 *
 * Un word contient une valeur de type "big endian", c'est-�-dire que les bits de poids forts
 * sont � gauche et les bits de poids faible � droite. Notamment, le bit 0 est le bit de poids
 * le plus fort et est � gauche
 */
template <int T>
class Word
{
private:
	bitset<T>	bits;
	int			taille;

public:
	Word();
	Word(unsigned char);
	Word(short int);
	Word(string chaine_binaire);
	virtual ~Word();

	Word<T>	operator^(const Word<T>&)	const;	// XOR
	Word<T>	operator|(const Word<T>&)	const;	// OR
	Word<T>	operator&(const Word<T>&)	const;	// AND
	Word<T>	operator~()					const;	// NOT

	Word<T>	operator<<(int nb)			const;	// D�calage vers la gauche de nb bits
	Word<T>	operator>>(int nb)			const;	// D�calage vers la droite de nb bits

	Word<T>	rotl(int nb)				const;	// Rotation circulaire vers la gauche de nb bits
												// Cette op�ration est n�cessaire dans SHA

	bool	operator[](int)				const;

	void	setBit(int, bool);

	operator unsigned int ()	const;

	string	toBin() 		const;
	string	toHex() 		const;

	void	setHexValue(string hexa);
	void	setBinValue(string bin);
};

#include "Word.cpp"

#endif /* WORD_H_ */
