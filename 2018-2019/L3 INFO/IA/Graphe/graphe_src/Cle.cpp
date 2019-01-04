/*
 * Cle.cpp
 *
 *  Created on: 23 déc. 2018
 *      Author: yann
 */

#include "../graphe_src/Cle.h"

unsigned int Cle::compteur = 0;

Cle::Cle() : valeur(compteur++) {
}

Cle::Cle(const Cle& c) : valeur(c.valeur) {
}

Cle::~Cle() {
}

unsigned int Cle::getValeur()	const {
	return valeur;
}

const Cle& Cle::operator =(const Cle& c) {
	this->valeur = c.valeur;

	return *this;
}

bool Cle::operator==(const Cle& c )const {
	return this->valeur==c.valeur;
}


ostream& operator<<(ostream& f, const Cle& c) {
	f << "Cle[valeur=" << c.valeur << "]";
	return f;
}
