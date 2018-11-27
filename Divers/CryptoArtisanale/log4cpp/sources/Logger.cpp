/*
 * Logger.cpp
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#include 	<Logger.h>
#include	<ConsoleAppender.h>

#include	<iostream>
using namespace std;

Logger::Logger(string n) : nom(n), niveau(Appender::DEBUG), nombreAppender(0), tableauAppender(NULL)
{
}

Logger::~Logger()
{
	free(tableauAppender);
}

void	Logger::add(Appender* appender)
{
	Appender**	nouveauTableau = (Appender**)malloc(sizeof(Appender*)*(nombreAppender+1));

	for (int i=0; i<nombreAppender; i++)
		nouveauTableau[i] = tableauAppender[i];

	nouveauTableau[nombreAppender++] = appender;

	Appender **tableauTemp = tableauAppender;
	tableauAppender = nouveauTableau;
	free(tableauTemp);
}

void Logger::clear()
{
	nombreAppender=0;
}

void	Logger::debug(const Chaine& message, const Information& info)
{
	if (nombreAppender==0)
		initialiserParDefaut();

	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->debug((string)message, info);
}

void	Logger::info(const Chaine& message, const Information& info)
{
	if (nombreAppender==0)
		initialiserParDefaut();

	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->info((string)message, info);
}

void	Logger::warn(const Chaine& message, const Information& info)
{
	if (nombreAppender==0)
		initialiserParDefaut();

	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->warn((string)message, info);
}

void	Logger::error(const Chaine& message, const Information& info)
{
	if (nombreAppender==0)
		initialiserParDefaut();

	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->error((string)message, info);
}

void	Logger::fatal(const Chaine& message, const Information& info)
{
	if (nombreAppender==0)
		initialiserParDefaut();

	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->fatal((string)message, info);

	system("pause");
	exit(-1);
}


void	Logger::setNiveau(int n)
{
	niveau = n;
	for (int i=0; i<nombreAppender; i++)
		tableauAppender[i]->setNiveau(n);
}


string		Logger::getNom()		const
{
	return nom;
}

/**
 * Singleton
 */

Logger*	Logger::getLogger(string nom, int niveau)
{
	static	list<Logger*>	list_logger;

	list<Logger*>::iterator it = list_logger.begin();

	bool	trouve = false;
	unsigned int		n = 0;

	while (!trouve && (n < list_logger.size()))
	{
		if ((*it)->nom == nom)
		{
			trouve = true;
		}
		else
		{
			it++;
			n++;
		}

	}

	if (trouve)
		return *it;
	else
	{
		Logger	*l = new Logger(nom);
		l->setNiveau(niveau);
		l->initialiserParDefaut();
		list_logger.push_back(l);
		return l;
	}
}

void	Logger::initialiserParDefaut()
{
	this->add(new ConsoleAppender(this->getNiveau()));
}

