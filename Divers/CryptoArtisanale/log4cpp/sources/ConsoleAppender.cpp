/*
 * ConsoleAppender.cpp
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */
#include	<iostream>
using namespace std;

#include "ConsoleAppender.h"

ConsoleAppender::ConsoleAppender(int niveau) : Appender(niveau)
{
}

ConsoleAppender::~ConsoleAppender() {
}

void	ConsoleAppender::debug(string message, const Information& info)
{
	if (getNiveau()<=DEBUG)
		cout << formater(message, DEBUG, info) << endl;
}

void	ConsoleAppender::info(string message, const Information& info)
{
	if (getNiveau()<=INFO)
		cout << formater(message, INFO, info) << endl;
}

void	ConsoleAppender::warn(string message, const Information& info)
{
	if (getNiveau()<=WARN)
		cout << formater(message, WARN, info) << endl;
}

void	ConsoleAppender::error(string message, const Information& info)
{
	if (getNiveau()<=ERROR)
		cout << formater(message, ERROR, info) << endl;
}

void	ConsoleAppender::fatal(string message, const Information& info)
{
	if (getNiveau()<=FATAL)
		cout << formater(message, FATAL, info) << endl;
}
