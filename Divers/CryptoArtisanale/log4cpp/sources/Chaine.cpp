/*
 * Chaine.cpp
 *
 *  Created on: 18 nov. 2008
 *      Author: Yan
 */

#include "Chaine.h"

Chaine::Chaine() : chaine()
{}

Chaine::Chaine(const char* c) : chaine(c)
{}

Chaine::Chaine(int n) : chaine()
{
	ostringstream	stream(chaine);
	stream << n;
	chaine = stream.str();
}

Chaine::Chaine(const string& s) : chaine(s)
{}

Chaine::Chaine(const Chaine& s) : chaine(s.chaine)
{}

Chaine::~Chaine()
{}

Chaine	Chaine::operator+(const Chaine& ch)
{
	return Chaine((chaine+ch.chaine).c_str());
}

Chaine	Chaine::operator+(const char* ch)
{
	ostringstream	stream;
	stream << chaine << ch;
	return Chaine(stream.str());
}

Chaine	Chaine::operator+(unsigned int n)
{
	ostringstream	stream;
	stream << n;
	return Chaine(chaine+stream.str());
}

Chaine	Chaine::operator+(int n)
{
	ostringstream	stream;
	stream << n;
	return Chaine(chaine+stream.str());
}

Chaine	Chaine::operator+(double n)
{
	ostringstream	stream;
	stream << n;
	return Chaine(chaine+stream.str());
}

Chaine	Chaine::operator+(char n)
{
	ostringstream	stream;
	stream << n;
	return Chaine(chaine+stream.str());
}

const Chaine&	Chaine::operator=(const Chaine& c)
{
	if (&c != this)
		chaine = c.chaine;

	return *this;
}


Chaine::operator string()	const
{
	return chaine;
}
