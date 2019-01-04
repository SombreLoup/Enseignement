/*
 * Cle.h
 *
 *  Created on: 23 déc. 2018
 *      Author: yann
 */

#ifndef CLE_H_
#define CLE_H_

#include	<iostream>
using namespace std;

class Cle {
private:
	unsigned int	valeur;

	static unsigned int	compteur;

public:
	Cle(const Cle&);
	Cle();

	virtual ~Cle();

	unsigned int getValeur()	const;

	operator string() const {
		char s[200];
		sprintf(s,"Cle[valeur=%d]",valeur);
		return string(s);
	}

	const Cle&	operator=(const Cle&);

	bool	operator==(const Cle&)	const;
	bool	operator!=(const Cle& c)	const { return !(*this==c); }

	friend ostream& operator<<(ostream&, const Cle&);
};


#endif /* CLE_H_ */
