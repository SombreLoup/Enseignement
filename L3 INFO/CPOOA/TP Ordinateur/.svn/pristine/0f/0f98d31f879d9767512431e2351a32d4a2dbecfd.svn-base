/*
 * Ecran.cpp
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#include "Ecran.h"

Ecran::Ecran(const char* desc, const int f, const int t, const double p)
	: Composant(desc), format(f), taille(t), pitch(p) {
}

Ecran::Ecran(const Ecran& e)
	: Composant(e), format(e.format), taille(e.taille), pitch(e.pitch) {
}


Ecran::~Ecran() {
}

int		Ecran::getFormat()		const {
	return format;
}

int		Ecran::getTaille()		const {
	return taille;
}

double	Ecran::getPitch()		const {
	return pitch;
}

double	Ecran::getPrix()		const {
	if (format==VGA)
		return taille*50+150*pitch;
	if (format==SVGA)
		return taille*60+180*pitch;

	return taille*80+200*pitch;
}

const Ecran&	Ecran::operator=(const Ecran& e) {
	if (this != &e) {
		Composant::operator=(e);
		format = e.format;
		taille = e.taille;
		pitch = e.pitch;
	}
	return *this;
}

bool Ecran::operator==(const Ecran& e)	const {
	return Composant::operator==(e) && (format==e.format) && (taille==e.taille) && (pitch==e.pitch);
}

void Ecran::afficher(ostream& flux)	const {
	flux << "Ecran [";
	Composant::afficher(flux);
	flux << ";format=" << format << ";taille=" << taille << ";pitch=" << pitch << "]";
}
