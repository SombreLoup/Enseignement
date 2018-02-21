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
 * Un word contient une valeur de type "big endian", c'est-à-dire que les bits de poids forts
 * sont à gauche et les bits de poids faible à droite. Notamment, le bit 0 est le bit de poids
 * le plus fort et est à gauche
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

	Word<T>	operator<<(int nb)			const;	// Décalage vers la gauche de nb bits
	Word<T>	operator>>(int nb)			const;	// Décalage vers la droite de nb bits

	Word<T>	rotl(int nb)				const;	// Rotation circulaire vers la gauche de nb bits
												// Cette opération est nécessaire dans SHA

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
