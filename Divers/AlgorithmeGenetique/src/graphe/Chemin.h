/*
 * Chemin.h
 *
 *  Created on: 17 nov. 2011
 *      Author: yann
 */

#ifndef CHEMIN_H_
#define CHEMIN_H_

#include	<vector>
#include	<iostream>
using namespace std;

#include	"Noeud.h"
#include	"Graphe.h"

class Chemin {
private:
	vector<Noeud>	chemin;

public:
	Chemin();
	Chemin(const Chemin&);
	virtual ~Chemin();

	void			add(const Noeud&);
	unsigned int	getNombreNoeuds()			const;
	const Noeud&	getNoeud(unsigned int i)	const;

	double	getLongueur(const Graphe& graphe)	const;

	const Chemin&		operator=(const Chemin& chemin);
	friend ostream&		operator<<(ostream&, const Chemin&);
};

#endif /* CHEMIN_H_ */
