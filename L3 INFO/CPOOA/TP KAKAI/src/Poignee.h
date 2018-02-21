/*
 * Poignee.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef POIGNEE_H_
#define POIGNEE_H_

#include	<iostream>
#include	<string>
using namespace std;

#include "Option.h"

class Poignee : public Option {

private:
	/* liste des champs */
	double	prix;

public:
	Poignee(const Poignee& objet);
	Poignee(const string& libelle="Poignée non libellée", const double prix=50);

	~Poignee();

	double getPrix() const;
	void setPrix(double prix);

	double	getCout(const Meuble&) const;

	const Poignee& operator=(const Poignee& objet);

	bool operator==(const Option& objet) const;
	// Attention : surcharge de op== de Option donc respect du param√®tre
	// op!= h√©rit√© et convient dans tous les cas. Inutile de le refaire

	void afficher(ostream& flux)	const;

};

#endif /* POIGNEE_H_ */
