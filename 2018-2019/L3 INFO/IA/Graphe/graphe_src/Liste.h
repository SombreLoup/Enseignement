/*
 * Liste.h
 *
 *  Created on: 25 déc. 2018
 *      Author: yann
 */

#ifndef LISTE_H_
#define LISTE_H_

#include <stdlib.h>

#include <string>
#include <iostream>
#include <sstream>
#include <cassert>
using namespace std;

template <class T>
class Maillon {
private:
	T 			valeur;
	Maillon*	suivant;


public:
	Maillon(T v, Maillon* s) : valeur(v), suivant(s) {}

	T 			getValeur() const { return valeur; }
	Maillon* 	getSuivant() const { return suivant; }
	void 		setSuivant(Maillon* s) { suivant = s; }

	friend ostream& operator<<(ostream& f, const Maillon<T> m) {
		f << "Maillon[valeur=" << m.valeur << ", suivant=" << m.suivant;
		return f;
	}
};



template <class T>
class Liste;


template <class T>
class Iterateur {
private:
	Maillon<T>* curseur;
	Maillon<T>* tete;

	friend class Liste<T>;

public:
	Iterateur(Maillon<T>* tete) : curseur(tete), tete(tete) {}
	Iterateur(const Iterateur<T>& it) : curseur(it.curseur), tete(it.tete) {}

	void begin() {
		curseur = tete;
	}

	bool hasNext() const {
		return (curseur != NULL);
	}

	T next() {
		assert(hasNext());
		T temp = curseur->getValeur();
		curseur = curseur->getSuivant();
		return temp;
	}

	friend ostream& operator<<(ostream& f, const Iterateur& it) {
		f << "Iterateur[curseur=" << it.curseur << "]";
		return f;
	}
};


template <class T>
class Liste {
private:
	Maillon<T>*	tete;

public:
	Liste() : tete(NULL) {}

	Liste(const Liste& l) {
		tete = copier(l.tete);
	}

	const Liste& operator=(const Liste& l) {
		if (this != &l) {
			liberer(tete);
			tete = copier(l.tete);
		}

		return *this;
	}

	~Liste() {
		liberer(tete);
		tete = NULL;
	}

	void insererEnTete(T v) {
		tete = new Maillon<T>(v, tete);
	}

	void ajouter(T v) {
		insererEnTete(v);
	}

	void supprimer(T v) {
		Maillon<T>* prec=NULL;
		Maillon<T>* courant = tete;
		bool	trouve = false;

		while (!trouve && (courant!=NULL)) {
			if (v==courant->getValeur()) {
				trouve = true;

				if (prec==NULL)
					tete = courant->getSuivant();
				else
					prec->setSuivant(courant->getSuivant());

				delete courant;
			}
			else {
				prec = courant;
				courant = courant->getSuivant();
			}
		}
	}

	bool estVide() const {
		return tete==NULL;
	}

	bool contient(T v) const {
		bool trouve = false;
		Maillon<T>* e = const_cast<Maillon<T>* >(tete);

		while (!trouve && (e != NULL)) {
			if (e->getValeur() == v) {
				trouve = true;
			}
			else
				e = e->getSuivant();
		}

		return trouve;
	}

	int longueur() const {
		return longueur(tete);
	}

	operator string() const {
		string s("(");
		Maillon<T>* m = tete;


		while (m != NULL) {
			s += intToString(m->getValeur());

			if (m->getSuivant() != NULL) {
				s += ",";
			}
			m = m->getSuivant();
		}


		s += ")";
		return s;
	}


	Iterateur<T>	getIterateur() const {
		return Iterateur<T>(const_cast<Maillon<T>*>(tete));
	}



	friend ostream& operator<< (ostream& f, const Liste& l) {
		f << (string)l;
		return f;
	}

private:
	void liberer(Maillon<T>* m) {
		if (m!=NULL) {
			liberer(m->getSuivant());
			delete m;
		}
	}

	Maillon<T>* copier(Maillon<T>* m) {
		if (m == NULL)
			return m;
		else
			return new Maillon<T>(m->getValeur(), copier(m->getSuivant()));
	}

	static string intToString(const T i) {
		stringstream ss;
		ss << i;
		return ss.str();
	}

	int longueur(Maillon<T>* e) const {
		if (e==NULL)
			return 0;
		else
			return 1+longueur(e->getSuivant());
	}
};



#endif /* LISTE_H_ */
