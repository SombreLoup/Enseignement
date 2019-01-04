/*
 * HeuristiquesAStar.cpp
 *
 *  Created on: 2 janv. 2019
 *      Author: yann
 */


#include	"HeuristiquesAStar.h"

double calculerDistanceEuclidienne(const Point2D& p1, const Point2D& p2) {
	double d;

	d = p1.getDistance(p2);

	return d;
}

double calculerDistanceManhattan(const Point2D& p1, const Point2D& p2) {
	double d;

	d = fabs(p1.getX()-p2.getX())+fabs(p1.getY()-p2.getY());

	return d;
}
