#include "Kasiski.h"

#include	<sstream>
#include	<iostream>
using namespace std;

#include	"Statistiques.h"
#include	"Nombre.h"
#include	"Logger.h"
static Logger	*logger = Logger::getLogger();

vector<int>	Kasiski::calculerEcarts(string chaine, std::map<string,int> trigrammes)
{
	vector<int>	tableauEcarts;

	map<string,int>::const_iterator it = trigrammes.begin();
	for (unsigned int i=0; i<trigrammes.size(); i++)
	{
		string	trigramme = it->first;
		int		frequence = it->second;
		
		ostringstream s; s << "trigramme:" << trigramme << "  frequence:" << frequence;
		logger->DEBUG(s.str());

		if (frequence > 1)
		{
			int	pos1 = chaine.find(trigramme);
			int pos2 = chaine.find(trigramme, pos1+1);
			tableauEcarts.push_back(pos2-pos1);
		}

		it++;
	}

	for (unsigned int i=0; i<tableauEcarts.size(); i++)
	{
		ostringstream	s;
		s << tableauEcarts[i];
		logger->DEBUG(s.str());
	}

	return tableauEcarts;
}


Kasiski::Kasiski(void)
{
}

Kasiski::~Kasiski(void)
{
}


