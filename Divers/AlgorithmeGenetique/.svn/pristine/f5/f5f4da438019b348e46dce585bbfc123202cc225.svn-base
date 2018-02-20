/*
 * AlgoGenetique.h
 *
 *  Created on: 17 nov. 2011
 *      Author: yann
 */

#ifndef ALGOGENETIQUE_H_
#define ALGOGENETIQUE_H_

#include	<vector>
#include	<string>
using namespace std;

#include	"Affectation.h"
#include	"Population.h"
#include	"../graphe/Graphe.h"
#include	"../securite/ContreMesure.h"
#include	"Traceur/Traceur.h"

class AlgoGenetique {
protected:
	Graphe					graphe;
	vector<Arc>				tousLesArcs;
	vector<ContreMesure>	ensembleMesure;
	double					budget;
	Noeud					E0, Ef;

	unsigned int	nombreIndividus;
	unsigned int	nombreGeneration;
	double			tauxMutation;

	Population	populationCourante;
	Population	nouvellePopulation;
	unsigned int numeroGeneration;


	char*		nomFichierStat;

	bool		estInstancie;

	Traceur		*traceur;

public:
	AlgoGenetique();

	AlgoGenetique(	const Graphe& graphe,
					const vector<ContreMesure>& ensembleMesure,
					const Noeud&	E0,
					const Noeud&	Ef,
					double budget = 100,
					unsigned int nbIndividus = 50,
					unsigned int nbGenerations = 50,
					double tauxMutation = 0.05);

	virtual void instancier(const Graphe& graphe,
			const vector<ContreMesure>& ensembleMesure,
			const Noeud&	E0,
			const Noeud&	Ef);


	virtual ~AlgoGenetique() {}

	virtual	Population	creerPopulation();
	virtual	Affectation	creerIndividu();

	virtual	void	croiser(const Affectation& pere, const Affectation& mere, Affectation& fils, Affectation& fille);
	virtual	void	muter(const Affectation& original, Affectation& mutant);

	double	moyenne(Population& population);


	virtual	Affectation&	meilleureAffectation(Population& population);


	virtual	void	evoluer();
	virtual	double	evaluer(Affectation&);
	virtual	void	selectionner();
	virtual	void	reproduire();
	virtual	void	muter();


    virtual	double getValeurTotale(Population & population);

    void	setBudget(double);
    void	setNombreGenerations(int);
    void	setNombreIndividus(int);
    void	setTauxMutation(double);

    Traceur*	getTraceur() const;
    void 	setTraceur(Traceur*);

    void	setNomStat(const char* nomFichier);

    unsigned int	getNumeroGeneration()	const;
    Population&	getPopulationCourante();

private:
	void operator=(const AlgoGenetique&) {}


};

#endif /* ALGOGENETIQUE_H_ */
