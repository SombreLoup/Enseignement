/*
 * Noeud.cpp
 *
 *  Created on: 13 nov. 2011
 *      Author: yann
 */

#include "Noeud.h"

Noeud::Noeud(string l)
: label(l)
{
}

Noeud::Noeud(const Noeud& n)
: label(n.label)
{}

Noeud::~Noeud()
{}

string	Noeud::getLabel()	const
{
	return label;
}

const Noeud&	Noeud::operator=(const Noeud& n)
{
	label = n.label;

	return *this;
}

bool	Noeud::operator==(const Noeud& n)	const
{
	return label==n.label;
}

bool	Noeud::operator!=(const Noeud& n)	const
{
	return !(*this==n);
}

ostream& operator<<(ostream& f, const Noeud& n)
{
	f << n.getLabel();
	return f;
}
