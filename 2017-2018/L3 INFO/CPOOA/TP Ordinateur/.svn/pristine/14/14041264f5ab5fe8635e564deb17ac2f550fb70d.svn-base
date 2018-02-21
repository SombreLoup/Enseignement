/*
 * Ecran.h
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */

#ifndef ECRAN_H_
#define ECRAN_H_

#include	"Composant.h"

class Ecran : public Composant {
public:
	static const int	VGA = 1;
	static const int	SVGA = 2;
	static const int	QSXGA = 3;

private:
	// Ces trois données membres sont en readonly donc pas de setters
	int		format;
	int		taille;	// en pouces
	double	pitch;	// en mm

public:
	Ecran(const char* desc, const int format=VGA, const int taille=27, const double pitch=0.02);
	Ecran(const Ecran&);
	~Ecran();

	int		getFormat()		const;
	int		getTaille()		const;
	double	getPitch()		const;

	double		getPrix()		const;

	const Ecran&	operator=(const Ecran&);
	bool			operator==(const Ecran&)	const;

	void afficher(ostream& flux)	const;

};

#endif /* ECRAN_H_ */
