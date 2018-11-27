/*
 * ConsoleAppender.h
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#ifndef CONSOLEAPPENDER_H_
#define CONSOLEAPPENDER_H_

#include	"Appender.h"

class ConsoleAppender : public Appender{
public:
	ConsoleAppender(int niveau);
	~ConsoleAppender();

	void	debug(string message, const Information& info);
	void	info(string message, const Information& info);
	void	warn(string message, const Information& info);
	void	error(string message, const Information& info);
	void	fatal(string message, const Information& info);

};

#endif /* CONSOLEAPPENDER_H_ */
