/*
 * Ilot.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#include "Ilot.h"
#include "Option.h"

Ilot::Ilot(const string& des)
	: Element(des), elements()
{
}

Ilot::Ilot(const Ilot& objet)
	: Element(objet), elements(objet.elements)
{
}

Ilot::~Ilot() {
}

const Ilot& Ilot::operator=(const Ilot& objet) {
	if (this != &objet) {
		Element::operator =(objet);
		elements = objet.elements;
	}
	return *this;
}

bool Ilot::operator==(const Element& objet) const {
	if (typeid(objet)!=typeid(*this))
		return false;
	const Ilot& ilot = (const Ilot&)objet;

	return Element::operator ==(objet) && (elements==ilot.elements);
}

void Ilot::afficher(ostream& flux)	const {
	flux << "Ilot[";
	Element::afficher(flux);
	for (unsigned int i = 0; i < elements.size(); ++i) {
		flux << *(elements[i]) << endl;
	}
	flux << "---" << endl;
}

double	Ilot::getPrix()	const {
	double p = 0;
	for (unsigned int i = 0; i < elements.size(); ++i) {
		p+= elements[i]->getPrix();
	}
	return p;
}

void Ilot::operator+=(Element* element) {
	elements.push_back(element);
}

Element* Ilot::operator[](const int i)	const {
	return elements[i];
}

void Ilot::supprimer(const int i) {
	elements.erase(elements.begin()+i);
}

int Ilot::getNombreElements()	const {
	return elements.size();
}
