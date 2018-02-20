/*
 * AlgoGenetiqueRelache.h
 *
 *  Created on: 20 nov. 2011
 *      Author: yann
 */

#ifndef ALGOGENETIQUERELACHE_H_
#define ALGOGENETIQUERELACHE_H_

#include	<vector>
using namespace std;

#include	"Affectation.h"
#include	"Population.h"
#include	"../graphe/Graphe.h"
#include	"../securite/ContreMesure.h"
#include	"AlgoGenetique.h"

class AlgoGenetiqueRelache: public AlgoGenetique {
private:
	double					valeurOptimale;
	double					ponderationBudget;
	double					ponderationLongueur;

public:
	AlgoGenetiqueRelache();

	AlgoGenetiqueRelache(	const Graphe& graphe,
					const vector<ContreMesure>& ensembleMesure,
					double budget,
					double valeurOptimale,
					const Noeud&	E0,
					const Noeud&	Ef,
					unsigned int nbIndividus = 100,
					unsigned int nbGenerations = 500,
					double tauxMutation = 0.05);


	virtual ~AlgoGenetiqueRelache() {}

	Population	creerPopulation();
	Affectation	creerIndividu();

	void	croiser(const Affectation& pere, const Affectation& mere, Affectation& fils, Affectation& fille);
	void	muter(const Affectation& original, Affectation& mutant);

	double	moyenne(Population& population);


	Affectation&	meilleureAffectation(Population& population);


	void	evoluer();
	double	evaluer(Affectation&);
	void	selectionner();
	void	reproduire();
	void	muter();


    double getValeurTotale(Population & population);
    double getValeurTotaleFaisable(Population & population);

    void	setValeurOptimale(double);
    void	setPonderationBudget(double);
    void	setPonderationLongueur(double);


private:
	void operator=(const AlgoGenetiqueRelache&) {}


};
#endif /* ALGOGENETIQUERELACHE_H_ */
