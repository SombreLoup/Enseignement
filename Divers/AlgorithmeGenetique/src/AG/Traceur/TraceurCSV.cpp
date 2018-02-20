/*
 * TraceurCSV.cpp
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#include "TraceurCSV.h"
#include "../AlgoGenetique.h"


TraceurCSV::TraceurCSV(const char* n, Traceur *suivant)
: Traceur(suivant) {
	csv.open(n);

	csv << "Budget;Longueur;Moyenne" << endl;
}

TraceurCSV::~TraceurCSV() {
}

bool	TraceurCSV::peutTracer(const char* action)				const {
	if (!strcmp(action,"Generation"))
		return true;
	return false;
}

void	TraceurCSV::tracerAction(const char* action, void* objet)	const {
	AlgoGenetique* ag = (AlgoGenetique*)objet;

	TraceurCSV *t = const_cast<TraceurCSV*>(this);

	t->csv << ag->meilleureAffectation(ag->getPopulationCourante()).getBudget() << ";";
	t->csv << ag->meilleureAffectation(ag->getPopulationCourante()).getLongueur() << ";";
	t->csv << ag->moyenne(ag->getPopulationCourante()) << endl;

}
