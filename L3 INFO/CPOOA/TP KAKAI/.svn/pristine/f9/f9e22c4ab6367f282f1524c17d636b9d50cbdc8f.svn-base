/*
 * Meuble.h
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */

#ifndef MEUBLE_H_
#define MEUBLE_H_

#include	<iostream>
#include	<string>
#include 	<vector>
using namespace std;

#include "Element.h"
class Option;

class Meuble : public Element {

private:
	/* liste des champs */
	double prixBase;
	int	largeur, hauteur, profondeur;

	vector<Option*>	options;

public:
	Meuble(const Meuble& objet);
	Meuble(const string& designation, const double prixBase, int l=40, int h=60, int p=60);

	virtual ~Meuble();

	int 	getHauteur() const;
	void 	setHauteur(int hauteur);
	int 	getLargeur() const;
	void 	setLargeur(int largeur);
	int 	getProfondeur() const;
	void 	setProfondeur(int profondeur);
	double 	getPrixBase() const;
	void 	setPrixBase(double prixBase);

	double	getPrix()	const;

	int		getNombreOptions()	const;
	Option*	operator[](const int i)	const;
	void	operator+=(Option* option);
	void	supprimer(const int i);

	const Meuble& operator=(const Meuble& objet);

	bool operator==(const Element& objet) const;

	void	afficher(ostream& flux)	const;

};

#endif /* MEUBLE_H_ */
