/*
 * Arete.h
 *
 *  Created on: 13 nov. 2011
 *      Author: yann
 */

#ifndef ARC_H_
#define ARC_H_

#include	"Noeud.h"

class Arc {
private:
	Noeud	origine, extremite;
	double	poids, poidsSecondaire;

public:
	Arc(Noeud origine, Noeud extremite, double poids=1.0);
	Arc(const Arc&);
	virtual ~Arc();

	Noeud	getOrigine()	const;
	Noeud	getExtremite()	const;
	double	getPoids()		const;
	void	setPoids(double);
	double	getPoidsSecondaire()		const;
	void	setPoidsSecondaire(double);

	const Arc&	operator=(const Arc&);

	bool	operator==(const Arc&)	const;
	bool	operator!=(const Arc&)	const;

	friend ostream&	operator<<(ostream&, const Arc&);
};

#endif /* ARC_H_ */
