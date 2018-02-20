/*
 * Affectation.h
 *
 *  Created on: 15 nov. 2011
 *      Author: yann
 */

#ifndef AFFECTATION_H_
#define AFFECTATION_H_

#include	<vector>
using namespace std;

#include	"../securite/ContreMesure.h"
#include	"../graphe/Graphe.h"
#include	"../graphe/Chemin.h"

class Affectation {
private:
	vector<ContreMesure>	affectation;
	double					budget;
	bool					budgetDejaCalcule;
	Chemin					chemin;
	double					longueur;
	bool					longueurDejaCalcule;

public:
	Affectation();
	Affectation(unsigned int tailleAffectation);
	Affectation(const Affectation&);
	virtual ~Affectation();

	void			affecterMesure(unsigned int numArc, const ContreMesure& mesure);
	ContreMesure	getMesure(unsigned int numArc)	const;
	unsigned int	getNombreMesure()				const;

	double			getBudget()									const;
	void			setBudget(double budget);

	const Chemin&	getChemin()									const;
	void			setChemin(const Chemin&, double longueur);
	double			getLongueur()								const;
	bool			estLongueurDejaEvaluee()					const;

	void			mettreAJour(const Graphe& graphe, const Noeud& E0, const Noeud& Ef);

	const Affectation&	operator=(const Affectation&);

	friend ostream&	operator<<(ostream&, const Affectation&);
};

#endif /* AFFECTATION_H_ */
