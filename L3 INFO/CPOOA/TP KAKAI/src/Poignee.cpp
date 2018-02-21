/*
 * Poignee.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#include "Poignee.h"

Poignee::Poignee(const Poignee& objet)
	: Option(objet), prix(objet.prix)
{
}

Poignee::Poignee(const string& libelle, const double p)
	: Option(libelle), prix(p)
{
}

Poignee::~Poignee() {
}

const Poignee& Poignee::operator=(const Poignee& objet) {
	if (this != &objet) {
		Option::operator=(objet);
		prix = objet.prix;
	}
	return *this;
}

double Poignee::getCout(const Meuble&) const {
	return prix;
}

double Poignee::getPrix() const {
	return prix;
}

void Poignee::setPrix(double prix) {
	this->prix = prix;
}

bool Poignee::operator==(const Option& objet) const {
	if (typeid(objet)!=typeid(*this))
		return false;
	const Poignee&	poignee = (const Poignee&)objet;

	return Option::operator==(objet) && (prix==poignee.prix);
}

void Poignee::afficher(ostream& flux)	const {
	flux << "Poignee[";
	Option::afficher(flux);
	flux << ";prix=" << prix << "]";
}
