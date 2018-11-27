/*
 * Information.cpp
 *
 *  Created on: 16 nov. 2008
 *      Author: Yan
 */

#include "Information.h"

Information::Information(string f, string fct, unsigned int l, string d)
: fichier(f), fonction(fct), ligne(l), date(d)
{
}

Information::~Information() {
}
