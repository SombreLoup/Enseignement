/*
 * ConsoleAppender.h
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#ifndef FILEAPPENDER_H_
#define FILEAPPENDER_H_

#include	"Appender.h"
#include	<fstream>
using namespace std;

class FileAppender : public Appender{

private:
	string		nomFichier;
	ofstream	out;

public:
	FileAppender(string nomFichier, int niveau);
	~FileAppender();

	void	debug(string message, const Information& info);
	void	info(string message, const Information& info);
	void	warn(string message, const Information& info);
	void	error(string message, const Information& info);
	void	fatal(string message, const Information& info);

};

#endif /* FILEAPPENDER_H_ */
