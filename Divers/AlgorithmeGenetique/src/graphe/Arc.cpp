/*
 * Arete.cpp
 *
 *  Created on: 13 nov. 2011
 *      Author: yann
 */

#include "Arc.h"

Arc::Arc(Noeud o, Noeud e, double p)
: origine(o), extremite(e), poids(p)
{}

Arc::Arc(const Arc& a)
: origine(a.origine), extremite(a.extremite), poids(a.poids)
{}

Arc::~Arc()
{}

Noeud	Arc::getOrigine()	const
{
	return origine;
}

Noeud	Arc::getExtremite()	const
{
	return extremite;
}

double	Arc::getPoids()		const
{
	return poids;
}

void	Arc::setPoidsSecondaire(double p)
{
	poidsSecondaire = p;
}

double	Arc::getPoidsSecondaire()		const
{
	return poidsSecondaire;
}

void	Arc::setPoids(double p)
{
	poids = p;
}

const Arc&	Arc::operator=(const Arc& a)
{
	origine = a.origine;
	extremite = a.extremite;
	poids = a.poids;

	return *this;
}

bool	Arc::operator==(const Arc& a)	const
{
	return (origine==a.origine) && (extremite==a.extremite);
}

bool	Arc::operator!=(const Arc& a)	const
{
	return !(*this==a);
}

ostream& operator<<(ostream& f, const Arc& a)
{
	f << "(" << a.getOrigine() << "," << a.getExtremite() << "){" << a.getPoids() << "}";
	return f;
}
