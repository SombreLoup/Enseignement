/*
 * Sommet.h
 *
 *  Created on: 24 déc. 2018
 *      Author: yann
 */

#ifndef SOMMET_H_
#define SOMMET_H_

#include <sstream>
using namespace std;

#include "../graphe_src/GElement.h"

template <class T>
class Sommet : public GElement<T>{
private:
	unsigned int degre;

public:
	Sommet() : GElement<T>(), degre(0) {}
	Sommet(T info) : GElement<T>(info), degre(0) {}
	virtual ~Sommet() {}

	unsigned int getDegre() const { return degre; }
	void incDegre() { degre++; }
	void decDegre() { degre--; }

	operator string() const {
		ostringstream o;
		o << "Sommet[" << (GElement<T>&)(*this) << ", degre=" << degre << "]";
		return o.str();
	}

	friend ostream& operator<<(ostream& f, const Sommet& s) {
		f << (string)s;
		return f;
	}
};

#endif /* SOMMET_H_ */
