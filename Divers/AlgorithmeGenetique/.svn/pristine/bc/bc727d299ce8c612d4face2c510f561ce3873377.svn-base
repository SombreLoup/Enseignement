/*
 * Mesure.h
 *
 *  Created on: 14 nov. 2011
 *      Author: yann
 */

#ifndef CONTREMESURE_H_
#define CONTREMESURE_H_

#define MAXFLOAT	3.40282347e+38F

#include	<math.h>

#include	<string>
#include	<vector>
#include	<iostream>
using namespace std;

#include	"../graphe/Graphe.h"

class ContreMesure {
private:
	string			description;
	double			taxe;
	vector<Arc>	p;

public:
	ContreMesure();
	ContreMesure(string description, double taxe=MAXFLOAT);
	ContreMesure(const ContreMesure&);
	virtual ~ContreMesure();

	string		getDescription()		const;
	double		getCout()				const;

	void			addArc(const Arc&);	// le poids de cette arete repr�sente p(e,k)
	unsigned int	getNombreArcs()			const;
	const Arc&		getArc(unsigned int)		const;
	bool			peutAmeliorer(const Arc&)	const;
	const Arc&		getArc(const Noeud& origine, const Noeud& extremite);

	const ContreMesure&	operator=(const ContreMesure&);

	friend ostream&	operator<<(ostream&, const ContreMesure&);

	static void genererContreMesures(Graphe & g, vector<ContreMesure> & M);

private:
	static	int getValeurAleatoire(int min, int max);
};

#endif /* CONTREMESURE_H_ */
