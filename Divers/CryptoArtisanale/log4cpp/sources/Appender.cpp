/*
 * Appender.cpp
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#include "Appender.h"

Appender::~Appender() {
}

Appender::Appender(int n) : niveau(n)
{
}

int	Appender::getNiveau() const
{
	return niveau;
}

void Appender::setNiveau(int n)
{
	niveau = n;
}

string Appender::formater(string message, int niveauMessage, const Information& info)
{
	string	niveau;
	switch (niveauMessage)
	{
		case	DEBUG :
			niveau="DEBUG";
			break;

		case	INFO :
			niveau="INFO";
			break;

		case	WARN :
			niveau="WARN";
			break;

		case	ERROR :
			niveau="ERROR";
			break;

		case	FATAL :
			niveau="FATAL";
			break;
	}
	char	n[200];
	sprintf(n,"%d",info.ligne);

	return string(n)+"\t"+info.fichier+"\t["+info.fonction+"]\t : "+niveau+" - "+message;
}
