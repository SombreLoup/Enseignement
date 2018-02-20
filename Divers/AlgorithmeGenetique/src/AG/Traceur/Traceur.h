/*
 * Traceur.h
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#ifndef TRACEUR_H_
#define TRACEUR_H_

#include	<string>
using namespace std;

class Traceur {
private:
	Traceur	*suivant;

public:
			Traceur();
			Traceur(Traceur *suivant);
	virtual	~Traceur();

	void tracer(const char* action, void* objet);

	virtual	bool	peutTracer(const char* action)				const;
	virtual	void	tracerAction(const char* action, void* objet)	const;

};

#endif /* TRACEUR_H_ */
