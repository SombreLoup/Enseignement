/*
 * Element.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef ELEMENT_H_
#define ELEMENT_H_

#include	<iostream>
#include	<string>
using namespace std;

class Element {

private:
	/* liste des champs */
	string	designation;

public:
	Element();
	Element(const Element& objet);
	Element(const string& designation);

	virtual ~Element();

	const string& getDesignation() const;
	void setDesignation(const string& designation);

	virtual double	getPrix()	const = 0;

	const Element& operator=(const Element& objet);

	virtual bool operator==(const Element& objet) const;
	bool operator!=(const Element& objet) const {
		return !(*this == objet);
	}

	friend ostream& operator<<(ostream& flux, const Element& objet) {
		objet.afficher(flux);
		return flux;
	}

	virtual void afficher(ostream&)	const = 0;

};

#endif /* ELEMENT_H_ */
