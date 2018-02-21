/*
 * Materiau.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef Materiau_H_
#define Materiau_H_

#include	<iostream>
#include	<string>
using namespace std;

#include "Option.h"

class Materiau : public Option {

private:
	/* liste des champs */
	double	majoration; // un pourcentage du prix du meuble

public:
	Materiau();
	Materiau(const Materiau& objet);
	Materiau(const string& libelle, const double majoration);

	~Materiau();

	double 	getMajoration() const;
	void	setMajoration(const double maj);

	double	getCout(const Meuble&) const;

	const Materiau& operator=(const Materiau& objet);

	bool operator==(const Option& objet) const;
	// Attention : surcharge de op== de Option donc respect du paramètre
	// op!= hérité et convient dans tous les cas. Inutile de le refaire

	void afficher(ostream& flux)	const;

};

#endif /* Materiau_H_ */
