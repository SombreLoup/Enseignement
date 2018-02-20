/*
 * Noeud.h
 *
 *  Created on: 13 nov. 2011
 *      Author: yann
 */

#ifndef NOEUD_H_
#define NOEUD_H_

#include	<string>
#include	<iostream>
using namespace std;

class Noeud {
private:
	string	label;

public:
	Noeud(string);
	Noeud(const Noeud&);
	virtual ~Noeud();

	string	getLabel()	const;

	const Noeud&	operator=(const Noeud&);

	bool			operator==(const Noeud&)	const;
	bool			operator!=(const Noeud&)	const;

	friend ostream&	operator<<(ostream&, const Noeud&);
};

#endif /* NOEUD_H_ */
