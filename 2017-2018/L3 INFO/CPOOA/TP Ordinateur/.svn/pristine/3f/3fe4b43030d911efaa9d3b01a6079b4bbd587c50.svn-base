/*
 * Composant.h
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#ifndef COMPOSANT_H_
#define COMPOSANT_H_

#include	<iostream>
using namespace std;

class Composant {
private:
	char*	description;

public:
	Composant(const char*);
	Composant(const Composant&);
	virtual ~Composant();

	const char*	getDescription()	const;
	void		setDescription(const char*);

	virtual double	getPrix()	const = 0;

	const Composant&	operator=(const Composant&);

	virtual	bool	operator==(const Composant&)	const;
			bool	operator!=(const Composant& c)	const {	return !(*this == c);	}

	friend	ostream&	operator<<(ostream& flux, const Composant& c) {	c.afficher(flux); return flux; }
	virtual	void afficher(ostream&)	const = 0;
};

#endif /* COMPOSANT_H_ */
