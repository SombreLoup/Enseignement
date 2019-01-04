/*
 * Arete.h
 *
 *  Created on: 24 déc. 2018
 *      Author: yann
 */

#ifndef ARETE_H_
#define ARETE_H_

#include <cassert>
using namespace std;

#include	"../graphe_src/GElement.h"
#include	"Sommet.h"

template <class T, class S>
class Arete : public GElement<S> {
private:
	Sommet<T>	*origine;
	Sommet<T>	*extremite;

public:
	Arete(Sommet<T>* o, Sommet<T>* e, S info) : GElement<S>(info),origine(o), extremite(e) {
		assert(o != NULL);
		assert(e != NULL);

		o->incDegre();
		e->incDegre();
	}

	Arete(const Arete&) : GElement<S>(),origine(NULL), extremite(NULL) {
		assert(0);
	}

	const Arete& operator=(const Arete&) {
		assert(0);

		return*this;
	}

	virtual ~Arete() {
		origine->decDegre();
		extremite->decDegre();
	}

	Sommet<T>* getOrigine() const {
		return origine;
	}

	Sommet<T>* getExtremite() const {
		return extremite;
	}

	operator string() const {
		ostringstream o;
		o << "Arete[" << (GElement<S>&)(*this) << ", origine=" << *origine << ", extremite=" << *extremite <<"]";
		return o.str();

	}

	friend ostream& operator<<(ostream& f, const Arete& a) {
		f << (string)a;
		return f;
	}

	bool estEgal(Sommet<T>* s1, Sommet<T>* s2) {
		return (origine == s1 && extremite==s2) || (origine==s2 && extremite==s1);
	}

};

#endif /* ARETE_H_ */
