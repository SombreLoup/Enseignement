/*
 * HeuristiquesAStar.h
 *
 *  Created on: 2 janv. 2019
 *      Author: yann
 */

#ifndef ASTAR_HEURISTIQUESASTAR_H_
#define ASTAR_HEURISTIQUESASTAR_H_

#include "Point2D.h"

typedef double (*Heuristique)(const Point2D& p1, const Point2D& p2);

double calculerDistanceEuclidienne(const Point2D& p1, const Point2D& p2);
double calculerDistanceManhattan(const Point2D& p1, const Point2D& p2);


#endif /* ASTAR_HEURISTIQUESASTAR_H_ */
