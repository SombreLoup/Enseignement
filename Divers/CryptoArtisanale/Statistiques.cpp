#include "Statistiques.h"

#include	"Logger.h"
static	Logger	*logger = Logger::getLogger();


Statistiques::Statistiques(void)
{
}

Statistiques::~Statistiques(void)
{
}

int	Statistiques::compterOccurrences(std::string chaine, std::string motif)
{
	int	nb  = 0;
	size_t	pos = chaine.find(motif,0);
	while (pos != -1)
	{
		nb++;
		pos = chaine.find(motif,pos+1);
	}

	return nb;
}

map<string,int>	Statistiques::compterFrequenceLettres(std::string chaine)
{
	map<string,int>	tab;
	for (char c='A'; c<='Z'; c++)
	{
		string lettre;
		lettre += c;

		int	n = Statistiques::compterOccurrences(chaine, lettre);
		tab[lettre] = n;
	}

	return tab;
}

map<string,int>	Statistiques::compterFrequenceDigrammes(std::string chaine)
{
	map<string,int>	tab;
	for (unsigned int pos=0; pos<chaine.length()-1; pos++)
	{
		string	digramme = chaine.substr(pos,2);
		int		n = 0;
		
		if (pos <= chaine.find(digramme))
		{
			n = Statistiques::compterOccurrences(chaine, digramme);
			tab[digramme] = n;
		}
	}

	return tab;
}


map<string,int>	Statistiques::compterFrequenceTrigrammes(std::string chaine)
{
	map<string,int>	tab;
	for (unsigned int pos=0; pos<chaine.length()-2; pos++)
	{
		string	trigramme = chaine.substr(pos,3);
		int		n = 0;
		
		if (pos <= chaine.find(trigramme))
		{
			n = Statistiques::compterOccurrences(chaine, trigramme);
			tab[trigramme] = n;
		}
	}

	return tab;
}


double	Statistiques::indiceCoincidence(std::string chaine)
{
	map<string, int>	tabLettres = compterFrequenceLettres(chaine);

	double	indice	= 0;
	int		n		= 0;

	for (char c='A'; c<='Z'; c++)
	{
		string	lettre;
		lettre += c;
	
		int	frequence = tabLettres[lettre];
		indice += frequence*(frequence-1);
		n += frequence;
	}

	logger->DEBUG("n="+n+"  length="+(int)(chaine.length()));

	indice = indice / (n*(n-1));

	return indice;
}


double	Statistiques::indiceCoincidence(std::string chaine, int m)
{
	double	n = chaine.length();
	return (n-m)/(m*(n-1))*0.0778+(n*(m-1))/((n-1)*m)*0.03846;
}

double	Statistiques::indiceCoincidence(string chaine, int periode, int positionDepart)
{
	string	souschaine;

	unsigned int	i = positionDepart;
	while (i<chaine.length())
	{
		souschaine += chaine[i];
		i += periode;
	}

	return indiceCoincidence(souschaine);
}
