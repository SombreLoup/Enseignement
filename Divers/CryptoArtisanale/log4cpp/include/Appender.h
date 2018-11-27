/*
 * Appender.h
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#ifndef APPENDER_H_
#define APPENDER_H_

#include	"Information.h"
#include	<string>
using namespace std;


class Appender {
public:
	static const int	DEBUG	= 0;
	static const int	INFO	= 1;
	static const int	WARN 	= 2;
	static const int	ERROR	= 3;
	static const int	FATAL	= 4;



public:
	Appender(int niveau);
	virtual ~Appender();

	virtual	void	debug(string message, const Information& info) 	= 0;
	virtual	void	info(string message, const Information& info) 	= 0;
	virtual	void	warn(string message, const Information& info) 	= 0;
	virtual	void	error(string message, const Information& info) 	= 0;
	virtual	void	fatal(string message, const Information& info) 	= 0;

	int			getNiveau()	const;
	void		setNiveau(int niveau);

	string		formater(string message, int niveauMessage, const Information& info);

private:
	int		niveau;
};

#endif /* APPENDER_H_ */
