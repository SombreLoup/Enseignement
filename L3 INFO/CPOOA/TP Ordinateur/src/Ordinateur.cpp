/*
 * Ordinateur.cpp
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#include "Ordinateur.h"

Ordinateur::Ordinateur(const char* desc)
	: description(strdup(desc)), nbComposants(0) {
}

Ordinateur::~Ordinateur() {
	free(description);
}

const char*	Ordinateur::getDescription()	const {
	return description;
}

void	Ordinateur::operator+= (Composant* composant) {
	// Aucun contrôle pour l'instant. On fera propre lorsque l'on aura les exceptions
	composants[nbComposants++] = composant;
}

void	Ordinateur::supprimer(const int i) {
	for (int j=i; j<nbComposants-1; j++)
		composants[j] = composants[j+1];
	nbComposants--;
}

double	Ordinateur::getPrix()	const {
	double prix = 0;

	for (int i=0; i<nbComposants; i++)
		prix += composants[i]->getPrix();

	return prix;
}

bool	Ordinateur::operator==(const Ordinateur& o)const {
	bool	egal = true;
	int		i = 0;

	if (nbComposants!=o.nbComposants)
		return false;

	while (egal && (i<nbComposants))
		egal = egal && (*(composants[i]) == *(o.composants[i]));

	return egal;
}

const Ordinateur&	Ordinateur::operator=(const Ordinateur& o) {
	if (this == &o)
		return *this;

	free(description);
	description = strdup(o.description);

	nbComposants = o.nbComposants;

	for (int i=0; i<nbComposants; i++)
		composants[i] = o.composants[i]; // les pointeurs sont partagés, conformément au diagramme UML

	return *this;
}

ostream&	operator<<(ostream& flux, const Ordinateur& o) {
	flux << "Ordinateur[" << o.description << "; Prix=" << o.getPrix() << "]" << endl;
	for (int i=0; i<o.nbComposants; i++)
		flux << *(o.composants[i]) << endl;

	return flux;
}
