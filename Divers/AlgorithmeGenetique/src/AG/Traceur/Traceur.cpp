/*
 * Traceur.cpp
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#include "Traceur.h"

Traceur::Traceur() : suivant(NULL) {
}

Traceur::Traceur(Traceur *suiv) : suivant(suiv) {
}

Traceur::~Traceur() {
}

void	Traceur::tracer(const char* action, void* objet) {
	Traceur	*traceurCourant = this;

	while (traceurCourant!=NULL) {
		if (traceurCourant->peutTracer(action)) {
			traceurCourant->tracerAction(action, objet);
		}
		traceurCourant = traceurCourant->suivant;
	}
}

bool Traceur::peutTracer(const char*) const {
	return false;
}

void Traceur::tracerAction(const char* action, void* objet) const {

}
