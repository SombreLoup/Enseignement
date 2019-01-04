/*
 * AStar.h
 *
 *  Created on: 30 déc. 2018
 *      Author: yann
 */

#ifndef ASTAR_H_
#define ASTAR_H_

#include	"Liste.h"
#include	"InfoAStar.h"
#include	"HeuristiquesAStar.h"


template <class Graphe, class Sommet>
class AStar {
public:

	static Liste<Sommet*> reconstruireChemin(Sommet* sommet) {
		Liste<Sommet*> chemin;

		while (sommet != NULL) {
			chemin.ajouter(sommet);
			sommet = (Sommet*)(sommet->getInformation().getParent());
		}

		return chemin;
	}



	static Liste<Sommet*> executer(Graphe* graphe, Sommet* depart, Sommet* arrivee, Heuristique hh) {
		Liste<Sommet*> ouverts;
		Liste<Sommet*> fermes;
		Liste<Sommet*> chemin;

		ouverts.ajouter(depart);

		InfoAStar infoDepart  = depart->getInformation();
		infoDepart.setG(0);
		infoDepart.setF(infoDepart.getG()+calculerCoutHeuristique(depart,arrivee,hh));
		depart->setInformation(infoDepart);

		while (ouverts.estVide() == false) {
			Sommet* courant = rechercherSommetLePlusPret(ouverts);
			InfoAStar infoCourant = courant->getInformation();

			if (courant==arrivee) {
				chemin = reconstruireChemin(arrivee);
				return chemin;
			}

			ouverts.supprimer(courant);
			fermes.ajouter(courant);

			Liste<Sommet*> voisins = graphe->voisins(courant);
			Iterateur<Sommet*> it = voisins.getIterateur();
			while (it.hasNext()) {
				Sommet*	voisin = it.next();
				InfoAStar infoVoisin = voisin->getInformation();

				if (fermes.contient(voisin)) {
					continue;
				}

				double	gTentative = infoCourant.getG()+calculerCoutHeuristique(courant,voisin,hh);

				if (! ouverts.contient(voisin)) {
					ouverts.ajouter(voisin);
				}
				else if (gTentative >= infoVoisin.getG()) {
					continue;
				}

				infoVoisin.setG(gTentative);
				infoVoisin.setF(gTentative+calculerCoutHeuristique(voisin,arrivee,hh));
				infoVoisin.setParent(courant);
				voisin->setInformation(infoVoisin);
			}
		}


		return chemin;
	}

	static Sommet* rechercherSommetLePlusPret(Liste<Sommet*> l) {
		Sommet*	s = NULL;
		Sommet*	temp = NULL;
		Iterateur<Sommet*>	it = l.getIterateur();

		it.begin();
		s = it.next();

		while (it.hasNext()) {
			temp = it.next();
			if (s->getInformation().getF() > temp->getInformation().getF())
				s = temp;
		}

		return s;
	}


	static double calculerCoutHeuristique(Sommet* s1, Sommet* s2, Heuristique hh) {
		double d;

		Point2D	p1, p2;

		p1 = s1->getInformation().getPosition();
		p2 = s2->getInformation().getPosition();

		d = hh(p1, p2);

		return d;
	}
};




#endif /* ASTAR_H_ */
