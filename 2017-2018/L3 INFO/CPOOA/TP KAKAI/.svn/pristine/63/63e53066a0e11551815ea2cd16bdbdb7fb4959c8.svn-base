/*
 * Materiau.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#include "Materiau.h"

Materiau::Materiau()
	: Option(), majoration(0)
{
}

Materiau::Materiau(const Materiau& objet)
	: Option(objet), majoration(objet.majoration)
{
}

Materiau::Materiau(const string& libelle, const double m)
	: Option(libelle), majoration(m)
{
}

Materiau::~Materiau() {
}

const Materiau& Materiau::operator=(const Materiau& objet) {
	if (this != &objet) {
		Option::operator=(objet);
		majoration = objet.majoration;
	}
	return *this;
}

double Materiau::getCout(const Meuble& m) const {
	return majoration*m.getPrixBase()/100;
}

double Materiau::getMajoration() const {
	return majoration;
}

void Materiau::setMajoration(double m) {
	this->majoration = m;
}

bool Materiau::operator==(const Option& objet) const {
	if (typeid(objet)!=typeid(*this))
		return false;
	const Materiau&	materiau = (const Materiau&)objet;

	return Option::operator==(objet) && (majoration==materiau.majoration);
}

void Materiau::afficher(ostream& flux)	const {
	flux << "Materiau[";
	Option::afficher(flux);
	flux << ";majoration=" << majoration << "]";
}
