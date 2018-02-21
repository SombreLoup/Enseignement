/*
 * Composant.cpp
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#include "Composant.h"

Composant::Composant(const char* desc)
	: description(strdup(desc)) {
}

Composant::Composant(const Composant& c)
	: description(strdup(c.description)) {
}

Composant::~Composant() {
	free(description);
}

const char*	Composant::getDescription()	const {
	return description;
}

void		Composant::setDescription(const char* desc) {
	if (description!=desc) {
		free(description);
		description = strdup(desc);
	}
}


const Composant&	Composant::operator=(const Composant& c) {
	if (this != &c) {
		setDescription(c.description);
	}
	return *this;
}

bool	Composant::operator==(const Composant& c)	const {
	return !strcmp(description, c.description);
}

void Composant::afficher(ostream& flux)	const {
	flux << "Composant[description=" << description << "]";
}
