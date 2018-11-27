/*
 * Information.h
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#ifndef INFORMATION_H_
#define INFORMATION_H_

#include	<string>
using namespace std;

class Information {
public:
	string			fichier;
	string			fonction;
	unsigned int	ligne;
	string			date;

public:
	Information(string fichier, string fonction, unsigned int ligne, string date);
	~Information();

};

#endif /* INFORMATION_H_ */
