/*
 * TraceurGeneration.h
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#ifndef TRACEURGENERATION_H_
#define TRACEURGENERATION_H_

#include "Traceur.h"

#include	<string>
using namespace std;


class TraceurGeneration: public Traceur {
public:
	TraceurGeneration(Traceur *suivant);
	virtual ~TraceurGeneration();


	bool	peutTracer(const char* action)				const;
	void	tracerAction(const char* action, void* objet)	const;

};

#endif /* TRACEURGENERATION_H_ */
