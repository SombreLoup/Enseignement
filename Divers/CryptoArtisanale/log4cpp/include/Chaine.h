/*
 * Chaine.h
 *
 *  Created on: 18 nov. 2008
 *      Author: Yan
 */

#ifndef CHAINE_H_
#define CHAINE_H_

#include	<string>
#include	<iostream>
#include	<sstream>
using namespace std;


class Chaine {
private:
	string	chaine;

public:
	Chaine();
	Chaine(const char*);
	Chaine(int);
	Chaine(const string&);
	Chaine(const Chaine& s);
	~Chaine();

	Chaine	operator+(const Chaine&);
	Chaine	operator+(const char*);
	Chaine	operator+(double);
	Chaine	operator+(unsigned int);
	Chaine	operator+(int);
	Chaine	operator+(char);

	const Chaine&	operator=(const Chaine&);

	operator string() const;
};

#endif /* CHAINE_H_ */
