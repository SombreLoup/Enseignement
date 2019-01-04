/*
 * Graphe.h
 *
 *  Created on: 25 déc. 2018
 *      Author: yann
 */

#ifndef GRAPHE_H_
#define GRAPHE_H_

#include "../graphe_src/Arete.h"
#include "../graphe_src/Liste.h"
#include "../graphe_src/Sommet.h"

template <class T, class S>
class Graphe {
private:
	Liste<Sommet<T>* >	listeSommets;
	Liste<Arete<T,S>* >	listeAretes;

public:
	Graphe() {}

	Sommet<T>* ajouterSommet(T infoSommet) {
		Sommet<T>* nouveauSommet = new Sommet<T>(infoSommet);

		listeSommets.insererEnTete(nouveauSommet);

		return nouveauSommet;
	}

	int getNombreSommets() const {
		return listeSommets.longueur();
	}


	Arete<T,S>* ajouterArete(Sommet<T>* origine, Sommet<T>* extremite, S infoArete) {
		assert(listeSommets.contient(origine));
		assert(listeSommets.contient(extremite));

		Arete<T,S>* nouvelleArete = new Arete<T,S>(origine,extremite,infoArete);

		listeAretes.insererEnTete(nouvelleArete);
		return nouvelleArete;
	}


	int getNombreAretes() const {
		return listeAretes.longueur();
	}

	Liste< pair< Sommet<T> *, Arete<T,S>* > >  adjacences(const Sommet<T> * sommet) const {
		Liste< pair< Sommet<T> *, Arete<T,S>* > > adja;
		Sommet<T> *s = const_cast<Sommet<T>* >(sommet);

		Iterateur<Arete<T,S>* > it = listeAretes.getIterateur();
		while (it.hasNext()) {
			Arete<T,S>* arete = it.next();
			if ((arete->getOrigine()==s) || (arete->getExtremite()==s)) {
				adja.insererEnTete(pair< Sommet<T>*, Arete<T,S>* >(s,arete));
			}
		}

		return adja;
	}


	Liste<Sommet<T>* > voisins(const Sommet<T>* sommet) const {
		Liste<Sommet<T>* > lVoisins;
		Liste<pair< Sommet<T> *, Arete<T,S>* > > lAdjacents = adjacences(sommet);

		Iterateur<pair< Sommet<T> *, Arete<T,S>* > > it = lAdjacents.getIterateur();

		while (it.hasNext()) {
			pair< Sommet<T> *, Arete<T,S>* > paire = it.next();
			Arete<T,S>* arete = paire.second;
			if (arete->getOrigine()==sommet) {
				lVoisins.insererEnTete(arete->getExtremite());
			}
			else {
				lVoisins.insererEnTete(arete->getOrigine());
			}
		}

		return lVoisins;
	}

	Arete<T,S>* getAreteParSommet(const Sommet<T>* s1, const Sommet<T>* s2) const {
		Iterateur<Arete<T,S>* > it = listeAretes.getIterateur();
		while (it.hasNext()) {
			Arete<T,S>* arete = it.next();
			if ((arete->getOrigine()==s1) && (arete->getExtremite()==s2))
				return arete;
			else if ((arete->getOrigine()==s2) && (arete->getExtremite()==s1))
				return arete;
		}

		return NULL;
	}

	Liste<Sommet<T>* > getListeSommets() const {
		return listeSommets;
	}

};

template <class T, class S>
ostream& operator<<(ostream& f, const pair< Sommet<T> *, Arete<T,S>* >& pr) {
	f << "Pair[first=" << pr.first << ", second=" << pr.second << "]";
	return f;
}




#endif /* GRAPHE_H_ */
