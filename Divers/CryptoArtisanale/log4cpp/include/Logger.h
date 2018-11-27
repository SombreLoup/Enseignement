/*
 * Logger.h
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#ifndef LOGGER_H_
#define LOGGER_H_

#include	<string>
#include	<list>
#include	<sstream>
using namespace std;

#include	"Appender.h"
#include	"Information.h"
#include	"Chaine.h"

#define	STR(a)	(Chaine()+a)
#define	DEBUG(a)	debug(	STR(a)	,Information(__FILE__, __FUNCTION__, __LINE__, __DATE__))
#define	INFO(a)		info(	STR(a)	,Information(__FILE__, __FUNCTION__, __LINE__, __DATE__))
#define	WARN(a)		warn(	STR(a)	,Information(__FILE__, __FUNCTION__, __LINE__, __DATE__))
#define	ERROR(a)	error(	STR(a)	,Information(__FILE__, __FUNCTION__, __LINE__, __DATE__))
#define	FATAL(a)	fatal(	STR(a)	,Information(__FILE__, __FUNCTION__, __LINE__, __DATE__))


class Logger {
public:

	void	setNiveau(int niveau);
	inline int		getNiveau()					const	{ return niveau;	}

	string	getNom()					const;

	void	debug(const Chaine& message, const Information& info);
	void	info(const Chaine& message, const Information& info);
	void	warn(const Chaine& message, const Information& info);
	void	error(const Chaine& message, const Information& info);
	void	fatal(const Chaine& message, const Information& info);

	inline	void	rien() {}

	void	add(Appender*);
	void	clear();

	/**
	 * Singleton
	 */
	static	Logger*	getLogger(string nom="root", int niveau = Appender::DEBUG);

private:
	string		nom;
	int			niveau;
	int			nombreAppender;
	Appender**	tableauAppender;



private:
	Logger(string n);
	Logger(const Logger&);
	virtual ~Logger();

	void initialiserParDefaut();
};

#endif /* LOGGER_H_ */
