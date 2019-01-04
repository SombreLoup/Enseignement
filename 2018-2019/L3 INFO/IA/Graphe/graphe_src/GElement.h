/*
 * GElement.h
 *
 *  Created on: 23 déc. 2018
 *      Author: yann
 */

#ifndef GELEMENT_H_
#define GELEMENT_H_

#include <iostream>
#include <string>
#include <sstream>
using namespace std;
#include <stdlib.h>
#include "../graphe_src/Cle.h"

template <class T>
class GElement : public Cle {
private:
	T	information;

public:
	GElement() : Cle(), information() {}
	GElement(const T& info) : Cle(), information(info) {}
	GElement(const GElement& e) : information (e.information) {}

	virtual ~GElement() {}

	T getInformation() const { return information; }
	void setInformation(const T& info) { information = info; }

	const GElement& operator=(const GElement& e) {
		Cle::operator=(e);
		this->information = e.information;
		return *this;
	}

	operator string()  const {
		ostringstream o;
		o << "GElement[" << (Cle&)(*this) << ", information=" << information << "]";
		return o.str();
	}

	friend ostream& operator<<(ostream& f, const GElement& e) {
		f << (string)e;
		return f;
	}
};


#endif /* GELEMENT_H_ */
