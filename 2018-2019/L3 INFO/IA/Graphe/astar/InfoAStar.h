/*
 * InfoAStar.h
 *
 *  Created on: 30 déc. 2018
 *      Author: yann
 */

#ifndef ASTAR_INFOASTAR_H_
#define ASTAR_INFOASTAR_H_

#include	<string>
#include	"Point2D.h"

class InfoAStar {
private:
	string	nomVille;
	Point2D	position;
	double	f,g,h;
	void	*parent;

public:
	InfoAStar()
		: nomVille(""), position(), f(0), g(0), h(0), parent(NULL)
	{}

	InfoAStar(string nomVille, const Point2D& position)
		: nomVille(nomVille), position(position), f(0), g(0), h(0), parent(NULL)
	{}

	InfoAStar(const InfoAStar& info)
		: nomVille(info.nomVille), position(info.position), f(info.f), g(info.g), h(info.h), parent(info.parent)
	{}

	~InfoAStar() {}

	string		getNomVille()	const { return nomVille; }
	Point2D		getPosition()	const { return position; }
	void		setNomVille(string n) { this->nomVille = n; }
	void		setPosition(const Point2D& p) { this->position = p; }

	double		getF()			const { return f; }
	double		getG()			const { return g; }
	double		getH()			const { return h; }

	void 		setF(double f) 	{ this->f = f; }
	void 		setG(double g) 	{ this->g = g; }
	void 		setH(double h) 	{ this->h = h; }

	void*		getParent()	const {return parent; }
	void		setParent(void* parent) { this->parent = parent; }

	const InfoAStar&	operator=(const InfoAStar& info) {
		if (this != &info) {
			this->nomVille = info.nomVille;
			this->position = info.position;
			this->f = info.f;
			this->g = info.g;
			this->h = info.h;
			this->parent = info.parent;
		}

		return *this;
	}

	operator string() const {
		ostringstream	os;

		os << "InfoAStar[nomVille=" << nomVille <<  ", pos=" << position << "]";

		return os.str();
	}

	friend ostream& operator<<(ostream& f, const InfoAStar& info) {
		f << (string)info;
		return f;
	}
};



#endif /* ASTAR_INFOASTAR_H_ */
