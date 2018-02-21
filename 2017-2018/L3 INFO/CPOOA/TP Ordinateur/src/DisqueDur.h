/*
 * DisqueDur.h
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#ifndef DISQUEDUR_H_
#define DISQUEDUR_H_

#include	"Composant.h"

class DisqueDur : public Composant {
private:
	// données membres en readonly
	double		capacite;
	int			vitesse;

public:
	DisqueDur(const char* desc, const double capacite=0.5, const int vitesse=7500);
	DisqueDur(const DisqueDur&);
	~DisqueDur();

	double	getCapacite()		const;
	int		getVitesse()		const;

	double	getPrix()			const;

	const DisqueDur&	operator=(const DisqueDur&);
	bool				operator==(const DisqueDur&)	const;

	void afficher(ostream& flux)	const;
};

#endif /* DISQUEDUR_H_ */
