/*
 * Population.cpp
 *
 *  Created on: 18 nov. 2011
 *      Author: yann
 */

#include "Population.h"

Population::Population()
: population()
{}

Population::Population(const Population& p)
: population(p.population)
{}

Population::~Population()
{}

void Population::add(const Affectation& aff)
{
	population.push_back(aff);
}

void Population::remove(unsigned int i)
{
	population.erase(population.begin()+i);
}

unsigned int Population::getNombreIndividus()		const
{
	return population.size();
}

Affectation&	Population::getIndividu(unsigned int i)
{
	if ((i<0) || (i>=population.size()))
		throw Exception("Population::getIndividu --> indice incorrect");

	return population[i];
}

const Population&	Population::operator=(const Population& p)
{
	population = p.population;

	return *this;
}

ostream& operator<<(ostream& f, Population& p)
{
	f << "Population" << endl;
	f << "-------------------------------" << endl;
	for (unsigned int i=0; i<p.getNombreIndividus(); i++)
		f << "Budget : " << p.getIndividu(i).getBudget() << " -- " << p.getIndividu(i) << endl;

	f << "-------------------------------" << endl;
	return f;
}
