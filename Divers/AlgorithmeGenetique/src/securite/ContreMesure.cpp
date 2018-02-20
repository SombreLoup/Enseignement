/*
 * Mesure.cpp
 *
 *  Created on: 14 nov. 2011
 *      Author: yann
 */

#include <stdlib.h>

#include "ContreMesure.h"
#include "../commun/Exception.h"

ContreMesure::ContreMesure()
:description(""), taxe(0)
{}

ContreMesure::ContreMesure(string d, double t)
: description(d), taxe(t)
{}

ContreMesure::ContreMesure(const ContreMesure& m)
: description(m.description), taxe(m.taxe), p(m.p)
{}

ContreMesure::~ContreMesure()
{}

string		ContreMesure::getDescription()		const
{
	return description;
}

double		ContreMesure::getCout()				const
{
	return taxe;
}

void		ContreMesure::addArc(const Arc& a)
{
	p.push_back(a);
}

unsigned int	ContreMesure::getNombreArcs()		const
{
	return p.size();
}

const Arc&		ContreMesure::getArc(unsigned int i)	const
{
	if ((i<0) && (i>=p.size()))
		throw Exception("Mesure::getArc --> indice incorrect");
	return p[i];
}

const Arc&		ContreMesure::getArc(const Noeud& origine, const Noeud& extremite)
{
	unsigned int	i 		= 0;

	while (i<p.size())
	{
		if ((p[i].getOrigine() == origine) && (p[i].getExtremite() == extremite))
			return p[i];
		else
			i++;
	}

	throw Exception("ContreMesure::getArc --> l'arc recherch� n'existe pas");
}


const ContreMesure&	ContreMesure::operator=(const ContreMesure& m)
{
	description = m.description;
	taxe = m.taxe;
	p = m.p;

	return *this;
}

ostream&	operator<<(ostream& f, const ContreMesure& m)
{
	f << "Mesure [" << m.description << ";" << m.taxe << ";";

	if (m.p.size()>0)
	{

		f << m.getArc(0);
		for (unsigned int i=1; i<m.p.size(); i++)
			f << "," << m.getArc(i);
	}

	f << "]";

	return f;
}

bool	ContreMesure::peutAmeliorer(const Arc& arc)	const
{
	bool			trouve 	= false;
	unsigned int	i 		= 0;

	while (!trouve && (i<p.size()))
	{
		if (p[i] == arc)
			trouve = true;
		else
			i++;
	}

	return trouve;
}


int	ContreMesure::getValeurAleatoire(int min, int max)
{
	return	min+rand()%(max-min);
}


void ContreMesure::genererContreMesures(Graphe & g, vector<ContreMesure> & M)
{
    for(unsigned int numArc = 0;numArc < g.getTousLesArcs().size();numArc++){
        int nombreMesure = getValeurAleatoire(5, 10);
        for(int i = 0;i < nombreMesure;i++){
            int numeroMesure;
            do{
                numeroMesure = getValeurAleatoire(0, M.size());
            } while(M[numeroMesure].peutAmeliorer(g.getTousLesArcs()[numArc]));
            Arc	a = g.getTousLesArcs()[numArc];
            float poids = getValeurAleatoire(5,50);

            if (getValeurAleatoire(0,100)<10)
            	poids = MAXFLOAT;

            M[numeroMesure].addArc(Arc(a.getOrigine(), a.getExtremite(),poids));
        }
    }
}


