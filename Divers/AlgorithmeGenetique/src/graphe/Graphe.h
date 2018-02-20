/*
 * Graphe.h
 *
 *  Created on: 13 nov. 2011
 *      Author: yann
 */

#ifndef GRAPHE_H_
#define GRAPHE_H_

#include	<vector>
#include	<list>
#include	<iostream>
using namespace std;

#include	"Noeud.h"
#include	"Arc.h"
class		Chemin;
#include	"../commun/Exception.h"

class Graphe {
private:
	vector<vector<Noeud> >	tableListeAdjacence;
	vector<vector<double> >	tableListePoids;
	vector<Noeud>			tableauNoeud;
	vector<Arc>				tableauArc;

public:
	Graphe();
	Graphe(const Graphe&);
	virtual ~Graphe();

	void	addNoeud(const Noeud&);
	void	addArc(const Arc&);
	void	setArc(const Arc&);

	bool	existeNoeud(const Noeud&)						const;
	bool	existeArc(const Noeud& n1, const Noeud& n2) 	const;

	const Arc&			getArc(const Noeud&, const Noeud&) 	const;
	const vector<Arc>&	getTousLesArcs()					const;
	int		getIndiceArcDansLaListe(const Arc& a)			const;

	Chemin	PlusCourtChemin(const Noeud& source, const Noeud& destination) const;

	friend ostream&	operator<<(ostream&, const Graphe&);	// Format DOT
	friend istream&	operator>>(istream&, Graphe&);			// Format DOT

	static	void	genererInstance(Graphe& graphe, int nbNoeuds,int nbNiveaux,double densite, double poidsMin,double PoidsMax);


private:
	bool	existeArc(const Arc& a) const;
	int 	getIndiceNoeud(const Noeud& n) const;
	int		getIndiceArc(unsigned int indiceNoeud, const Arc& a) const;
	void 	miseAJour(const Noeud& n1, double d1, const vector<Noeud>& Q, vector<double> &D, vector<Noeud> &P) const;
	int		trouverMin(const vector<Noeud>& Q, const vector<double>& d) const;

};

#endif /* GRAPHE_H_ */
