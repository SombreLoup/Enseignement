/*
 * Option.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef OPTION_H_
#define OPTION_H_

#include	<iostream>
#include	<string>
using namespace std;

#include "Meuble.h"

class Option {

private:
	/* liste des champs */
	string	libelle;

public:
	Option(const Option& objet);
	Option(const string& libelle="Aucun libellé");

	virtual ~Option();

	const string& getLibelle() const;
	void setLibelle(const string& libelle);

	virtual double	getCout(const Meuble&)	const = 0;

	const Option& operator=(const Option& objet);

	virtual	bool operator==(const Option& objet) const = 0;
			bool operator!=(const Option& objet) const {
				return !(*this == objet);
			}

	friend ostream& operator<<(ostream& flux, const Option& objet);
	virtual void afficher(ostream& flux) const = 0;
};

#endif /* OPTION_H_ */
