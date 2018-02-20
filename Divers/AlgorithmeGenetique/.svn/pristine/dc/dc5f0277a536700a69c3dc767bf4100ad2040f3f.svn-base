/*
 * TraceurCSV.h
 *
 *  Created on: 9 oct. 2012
 *      Author: yann
 */

#ifndef TRACEURCSV_H_
#define TRACEURCSV_H_

#include "Traceur.h"

#include	<fstream>
using namespace std;

class TraceurCSV: public Traceur {
private:
	ofstream	csv;

public:
	TraceurCSV(const char* nomFichier, Traceur *suivant);
	virtual ~TraceurCSV();


	bool	peutTracer(const char* action)				const;
	void	tracerAction(const char* action, void* objet)	const;
};

#endif /* TRACEURCSV_H_ */
