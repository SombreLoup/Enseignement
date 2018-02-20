/*
 * Population.h
 *
 *  Created on: 18 nov. 2011
 *      Author: yann
 */

#ifndef POPULATION_H_
#define POPULATION_H_


#include	<vector>
using namespace std;

#include	"Affectation.h"

class Population {
private:
	vector<Affectation>	population;

public:
	Population();
	Population(const Population&);
	virtual ~Population();

	void				add(const Affectation&);
	void				remove(unsigned int);
	unsigned int		getNombreIndividus()		const;
	Affectation&		getIndividu(unsigned int);

	const Population&	operator=(const Population&);
	friend ostream&		operator<<(ostream&, Population&);
};

#endif /* POPULATION_H_ */
