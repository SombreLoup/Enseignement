/*
 * Element.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#include "Element.h"

Element::Element()
	: designation("Designation non définie")
{
}

Element::Element(const Element& objet)
	: designation(objet.designation)
{
}

Element::Element(const string& des)
	: designation(des)
{
}

Element::~Element() {
}

const Element& Element::operator=(const Element& objet) {
	if (this != &objet) {
		designation = objet.designation;
	}
	return *this;
}

bool Element::operator==(const Element& objet) const {
	return designation==objet.designation;
}

const string& Element::getDesignation() const {
	return designation;
}

void Element::setDesignation(const string& des) {
	this->designation = des;
}

void Element::afficher(ostream& flux)	const {
	flux << "Element[designation=" << designation << "]";
}

