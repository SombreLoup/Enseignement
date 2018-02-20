/*
 * TraceurGeneration.cpp
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#include "TraceurGeneration.h"
#include "../AlgoGenetique.h"

#include	<iostream>
using namespace std;

TraceurGeneration::TraceurGeneration(Traceur *suivant) : Traceur(suivant) {
}

TraceurGeneration::~TraceurGeneration() {
}


bool	TraceurGeneration::peutTracer(const char* action)				const {
	if (!strcmp(action,"Generation"))
		return true;
	return false;
}
void	TraceurGeneration::tracerAction(const char* action, void* objet)	const {
	AlgoGenetique* ag = (AlgoGenetique*)objet;

	cout << "Generation " << ag->getNumeroGeneration() << endl;

	cout << "Meilleur individu : " << ag->evaluer(ag->meilleureAffectation(ag->getPopulationCourante())) << endl;
	cout << "Chemin            : " << ag->meilleureAffectation(ag->getPopulationCourante()).getChemin() << endl;
	cout << "Longueur          : " << ag->meilleureAffectation(ag->getPopulationCourante()).getLongueur() << endl;
	cout << "Budget            : " << ag->meilleureAffectation(ag->getPopulationCourante()).getBudget() << endl;
	cout << "Moyenne           : " << ag->moyenne(ag->getPopulationCourante()) << endl;

//	csv << meilleureAffectation(populationCourante).getBudget() << ";" << meilleureAffectation(populationCourante).getLongueur() << ";" << moyenne(populationCourante) << endl;

}