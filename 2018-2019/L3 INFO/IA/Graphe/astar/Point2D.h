/*
 * Point2D.h
 *
 *  Created on: 29 déc. 2018
 *      Author: yann
 */

#ifndef ASTAR_POINT2D_H_
#define ASTAR_POINT2D_H_

#include	<math.h>

#include	<iostream>
#include	<string>
#include 	<sstream>
using namespace std;

#define	EPS	0.0000001

class Point2D {
private:
	double	x,y;

public:
	Point2D() : x(0), y(0) {}
	Point2D(double x, double y) : x(x), y(y) {}
	Point2D(const Point2D& p) : x(p.x), y(p.y) {}

	virtual ~Point2D() {}

	double	getX()	const { return x; }
	double	getY()	const { return y; }

	double 	getDistance(const Point2D& p) const {
		double dx = x-p.x;
		double dy = y-p.y;
		return sqrt (dx*dx+dy*dy);
	}

	const Point2D&	operator=(const Point2D& p) {
		if (this != &p) {
			this->x = p.x;
			this->y = p.y;
		}

		return *this;
	}

	operator string() const {
		ostringstream	os;

		os << "Point2D[x=" << x << ", y=" << y << "]";

		return os.str();
	}

	bool operator==(const Point2D& p) const {
		return (fabs(x-p.x) <= EPS) && (fabs(y-p.y) <= EPS);
	}

	friend ostream& operator<<(ostream& f, const Point2D& p) {
		f << (string)p;
		return f;
	}
};

#endif /* ASTAR_POINT2D_H_ */
