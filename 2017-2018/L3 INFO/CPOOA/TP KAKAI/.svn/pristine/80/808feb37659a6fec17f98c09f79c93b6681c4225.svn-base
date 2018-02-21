/*
 * Ilot.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef ILOT_H_
#define ILOT_H_

#include	<iostream>
#include	<string>
#include 	<vector>
using namespace std;

#include "Element.h"


class Ilot : public Element {

private:
	/* liste des champs */
	vector<Element*>	elements;

public:
	Ilot(const Ilot& objet);
	Ilot(const string& designation="Aucune désignation");

	virtual ~Ilot();

	double	getPrix()	const;

	int			getNombreElements()	const;
	Element*	operator[](const int i)	const;
	void		operator+=(Element* element);
	void		supprimer(const int i);

	const Ilot& operator=(const Ilot& objet);

	bool 	operator==(const Element& objet) const;

	void	afficher(ostream& flux)	const;

};

#endif /* ILOT_H_ */
