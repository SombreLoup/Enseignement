/*
 * Ordinateur.h
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#ifndef ORDINATEUR_H_
#define ORDINATEUR_H_

#include	"Composant.h"

class Ordinateur {
public:
	static const int	MAX_COMPOSANT = 10;

private:
	// Readonly aussi pour cette donnée membre
	char*	description;

	Composant*	composants[MAX_COMPOSANT];
	int			nbComposants;

public:
	Ordinateur(const char* desc);
	// manque le constructeur par copie (paresse de ma part...)
	~Ordinateur();

	const char*	getDescription()	const;

	void	operator+= (Composant* composant);
	void	supprimer(const int i);

	double	getPrix()	const;

	bool	operator==(const Ordinateur&)const;
	bool	operator!=(const Ordinateur& o) const { return !(*this==o); }

	const Ordinateur&	operator=(const Ordinateur&);

	friend ostream&	operator<<(ostream&, const Ordinateur&);
};

#endif /* ORDINATEUR_H_ */
