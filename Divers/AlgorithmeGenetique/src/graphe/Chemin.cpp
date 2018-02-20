/*
 * Chemin.cpp
 *
 *  Created on: 17 nov. 2011
 *      Author: yann
 */

#include "Chemin.h"

Chemin::Chemin(const Chemin& c)
: chemin(c.chemin)
{}

Chemin::Chemin()
: chemin()
{}

Chemin::~Chemin()
{}

void	Chemin::add(const Noeud& n)
{
	chemin.push_back(n);
}

double	Chemin::getLongueur(const Graphe& graphe)	const
{
	double	l = 0;

	for (unsigned int i=0; i<chemin.size()-1;i++)
		l += graphe.getArc(chemin[i], chemin[i+1]).getPoids();

	return l;
}

const Chemin&		Chemin::operator=(const Chemin& c)
{
	chemin = c.chemin;


	return *this;
}

ostream&		operator<<(ostream& f, const Chemin& c)
{
	for (unsigned int i=0; i<c.chemin.size();i++)
		f << c.chemin[i] << " ";

	return f;
}


unsigned int Chemin::getNombreNoeuds()	const
{
	return chemin.size();
}

const Noeud&	Chemin::getNoeud(unsigned int i)	const
{
	if (i>=getNombreNoeuds())
		throw Exception("Chemin::getNoeud --> indicie du noeud incorrect");

	return chemin[i];
}
