/*
 * Affectation.cpp
 *
 *  Created on: 15 nov. 2011
 *      Author: yann
 */

#include "Affectation.h"
#include "../commun/Exception.h"

Affectation::Affectation()
: affectation(0),
  budget(0),
  budgetDejaCalcule(false),
  chemin(),
  longueur(0),
  longueurDejaCalcule(false)
{}

Affectation::Affectation(unsigned int l)
: affectation(l),
  budget(0),
  budgetDejaCalcule(false),
  chemin(),
  longueur(0),
  longueurDejaCalcule(false)
{}

Affectation::Affectation(const Affectation& a)
: affectation(a.affectation),
  budget(a.budget),
  budgetDejaCalcule(a.budgetDejaCalcule),
  chemin(a.chemin),
  longueur(a.longueur),
  longueurDejaCalcule(a.longueurDejaCalcule)
{}

Affectation::~Affectation()
{}

void	Affectation::affecterMesure(unsigned int numArc, const ContreMesure& mesure)
{
	if ((numArc<0) && (numArc>=affectation.size()))
		throw Exception("Affectation::affecterMesure --> numéro d'arc incorrect");

	budgetDejaCalcule = false;
	longueurDejaCalcule = false;

	affectation[numArc] = mesure;
}


ContreMesure	Affectation::getMesure(unsigned int numArc)	const
{
	if ((numArc<0) && (numArc>=affectation.size()))
		throw Exception("Affectation::getMesure --> numéro d'arc incorrect");

	return affectation[numArc];
}



double	Affectation::getBudget()	const
{
	if (budgetDejaCalcule)
		return budget;

	throw Exception("Affectation::getBudget() --> le budget n'a pas été calculé");
}

void	Affectation::setBudget(double b)
{
	budget = b;
	budgetDejaCalcule = true;
}

const Affectation&	Affectation::operator=(const Affectation& a)
{
	affectation = a.affectation;
	budget = a.budget;
	budgetDejaCalcule = a.budgetDejaCalcule;
	chemin = a.chemin;
	longueur = a.longueur;
	longueurDejaCalcule = a.longueurDejaCalcule;

	return *this;
}


unsigned int	Affectation::getNombreMesure()	const
{
	return affectation.size();
}


ostream&	operator<<(ostream& f, const Affectation& a)
{
	f << "{";
	for (unsigned int i=0; i<a.getNombreMesure(); i++)
		f << a.getMesure(i) << " , ";
	f << "}";

	return f;
}

const Chemin&	Affectation::getChemin()											const
{
	return chemin;
}

void	Affectation::setChemin(const Chemin& c, double l)
{
	chemin = c;
	longueur = l;
	longueurDejaCalcule = true;
}

double	Affectation::getLongueur()	const
{
	if (longueurDejaCalcule)
		return longueur;

	throw Exception("Affectation::getLongueur --> le chemin n'a pas été calculé");
}

bool	Affectation::estLongueurDejaEvaluee()	const
{
	return longueurDejaCalcule;
}

void Affectation::mettreAJour(const Graphe& graphe, const Noeud& E0, const Noeud& Ef)
{
	double b=0;
	for (unsigned int i = 0; i<getNombreMesure(); i++)
	{
		if (getMesure(i).peutAmeliorer(graphe.getTousLesArcs()[i]))
			b += getMesure(i).getCout();
	}

	setBudget(b);

	Graphe	g = graphe;

	if (getNombreMesure() != g.getTousLesArcs().size())
		throw Exception("Affectation::mettreAJour --> l'affectation ne convient pas au graphe");

	for (unsigned int i=0; i<getNombreMesure(); i++)
	{
		const Arc& a = g.getTousLesArcs()[i];
		if (getMesure(i).peutAmeliorer(a))
		{
			const Arc& aa = getMesure(i).getArc(a.getOrigine(), a.getExtremite());
			g.setArc(Arc(a.getOrigine(), a.getExtremite(), a.getPoids() + aa.getPoids()));
		}
	}

//	Chemin chemin1 = graphe.PlusCourtChemin(E0,Ef);
//	cout << "Plus court chemin sans mesures : " << chemin1 << " de longueur " << chemin1.getLongueur(graphe) << endl;
	Chemin chemin = g.PlusCourtChemin(E0,Ef);
//	cout << "Plus court chemin avec mesures : " << chemin << " de longueur " << chemin.getLongueur(g) << endl;
	setChemin(chemin,chemin.getLongueur(g));

}
