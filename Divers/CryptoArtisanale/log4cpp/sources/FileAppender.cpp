/*
 * FileAppender.cpp
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */
#include	<iostream>
using namespace std;

#include "FileAppender.h"

FileAppender::FileAppender(string f, int niveau) : Appender(niveau), nomFichier(f)
{
	out.open(nomFichier.c_str());
}

FileAppender::~FileAppender() {
}

void	FileAppender::debug(string message, const Information& info)
{
	if (getNiveau()<=DEBUG)
		out << formater(message, DEBUG, info) << endl;
}

void	FileAppender::info(string message, const Information& info)
{
	if (getNiveau()<=INFO)
		out << formater(message, INFO, info) << endl;
}

void	FileAppender::warn(string message, const Information& info)
{
	if (getNiveau()<=WARN)
		out << formater(message, WARN, info) << endl;
}

void	FileAppender::error(string message, const Information& info)
{
	if (getNiveau()<=ERROR)
		out << formater(message, ERROR, info) << endl;
}

void	FileAppender::fatal(string message, const Information& info)
{
	if (getNiveau()<=FATAL)
		out << formater(message, FATAL, info) << endl;
}
