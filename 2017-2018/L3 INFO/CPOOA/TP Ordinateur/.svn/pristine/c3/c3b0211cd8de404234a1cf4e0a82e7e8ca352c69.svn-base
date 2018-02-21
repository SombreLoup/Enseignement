/*
 * DisqueDur.cpp
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#include "DisqueDur.h"

DisqueDur::DisqueDur(const char* desc, const double c, const int v)
	: Composant(desc), capacite(c), vitesse(v) {
}

DisqueDur::DisqueDur(const DisqueDur& d)
	: Composant(d), capacite(d.capacite), vitesse(d.vitesse) {
}


DisqueDur::~DisqueDur() {
}

double		DisqueDur::getCapacite()		const {
	return capacite;
}

int		DisqueDur::getVitesse()		const {
	return vitesse;
}

double	DisqueDur::getPrix()		const {
	return capacite*98*vitesse/12000;
}

const DisqueDur&	DisqueDur::operator=(const DisqueDur& d) {
	if (this != &d) {
		Composant::operator=(d);
		capacite = d.capacite;
		vitesse = d.vitesse;
	}
	return *this;
}

bool DisqueDur::operator==(const DisqueDur& d)	const {
	return Composant::operator==(d) && (vitesse==d.vitesse) && (capacite==d.capacite);
}

void DisqueDur::afficher(ostream& flux)	const {
	flux << "DisqueDur [";
	Composant::afficher(flux);
	flux << ";capacite=" << capacite << ";vitesse=" << vitesse << "]";
}
