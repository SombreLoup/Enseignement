/*
 * Meuble.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#include "Meuble.h"
#include "Option.h"

Meuble::Meuble(const string& des, const double pr, const int l, const int h, const int p)
	: Element(des), prixBase(pr), largeur(l), hauteur(h), profondeur(p), options()
{
}

Meuble::Meuble(const Meuble& objet)
	: Element(objet), prixBase(objet.prixBase), largeur(objet.largeur), hauteur(objet.hauteur), profondeur(objet.profondeur), options(objet.options)
{
}

Meuble::~Meuble() {
}

const Meuble& Meuble::operator=(const Meuble& objet) {
	if (this != &objet) {
		Element::operator =(objet);
		prixBase = objet.prixBase;
		largeur = objet.largeur;
		hauteur = objet.hauteur;
		profondeur = objet.profondeur;
		options = objet.options;
	}
	return *this;
}

bool Meuble::operator==(const Element& objet) const {
	if (typeid(objet)!=typeid(*this))
		return false;
	const Meuble& meuble = (const Meuble&)objet;
	return Element::operator ==(objet) && (prixBase==meuble.prixBase) && (largeur==meuble.largeur) && (hauteur==meuble.hauteur) && (profondeur==meuble.profondeur) && (options==meuble.options);
}

double Meuble::getPrixBase() const {
	return prixBase;
}

void Meuble::setPrixBase(double prixBase) {
	this->prixBase = prixBase;
}

void Meuble::afficher(ostream& flux)	const {
	flux << "Meuble[";
	Element::afficher(flux);
	flux << ";prix de base=" << prixBase << ";l=" << largeur << ";h=" << hauteur << ";p=" << profondeur << "]" << endl;
	for (unsigned int i = 0; i < options.size(); ++i) {
		flux << *(options[i]) << endl;
	}
	flux << "---" << endl;
}

double	Meuble::getPrix()	const {
	double p = prixBase;
	for (unsigned int i = 0; i < options.size(); ++i) {
		p+= options[i]->getCout(*this);
	}
	return p;
}

int Meuble::getHauteur() const {
	return hauteur;
}

void Meuble::setHauteur(int hauteur) {
	this->hauteur = hauteur;
}

int Meuble::getLargeur() const {
	return largeur;
}

void Meuble::setLargeur(int largeur) {
	this->largeur = largeur;
}

int Meuble::getProfondeur() const {
	return profondeur;
}

void Meuble::setProfondeur(int profondeur) {
	this->profondeur = profondeur;
}

void Meuble::operator+=(Option* option) {
	options.push_back(option);
}

Option* Meuble::operator[](const int i)	const {
	return options[i];
}

void Meuble::supprimer(const int i) {
	options.erase(options.begin()+i);
}

int Meuble::getNombreOptions()	const {
	return options.size();
}
