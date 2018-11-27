#include "Nombre.h"

#include	"Logger.h"
static	Logger	*logger = Logger::getLogger();

#include	<math.h>

int	Nombre::pgcd(std::vector<int> tab)
{
	if (tab.size() < 2)
		logger->FATAL("Impossible de calculer le PGCD s'il y a moins de 2 éléments dans le tableau");

	int	p = tab[0];
	for (unsigned int i=1; i<tab.size(); i++)
		p = pgcd(p, tab[i]);

	return p;
}

int Nombre::pgcd(int a, int b)
{
	if (b==0)
		return a;
	else
		return pgcd(b, a % b);
}

vector<int> Nombre::getFacteursPremiers(int n)
{
	vector<int>	tab;
	vector<int> premiers = getNombresPremiers(n);

	logger->DEBUG("Nombre de premiers : "+(int)premiers.size());

	for(int k=0; k<premiers.size(); k++)
	{
		logger->DEBUG("premiers : "+premiers[k]);
		if (n % premiers[k] == 0)
			tab.push_back(premiers[k]);
	}

	return tab;
}

vector<int>	Nombre::getNombresPremiers(int n)
{
	logger->DEBUG("n="+n);

	vector<int>	tab;
	int limite = (int)(sqrt((double)n));

	logger->DEBUG("limite des diviseurs="+limite);

	tab.push_back(0);
	tab.push_back(1);

	for (int i=2; i<=n; i++)
		tab.push_back(i);

	logger->DEBUG("Début du cribble d'Eratosthene");

	for (int k=2; k<=limite; k++)
	{
		logger->DEBUG("k="+k);
		int	t = 2;
		while (k*t<=n)
		{
			logger->DEBUG("Avant tab["+(k*t)+"]="+tab[k*t]);
			tab[k*t] = 0;
			logger->DEBUG("Apres tab["+(k*t)+"]="+tab[k*t]);
			t++;
		}
	}

	logger->DEBUG("Reconstruction du tableau des nombres premiers");

	vector<int>	premiers;
	for (int i=1; i<n; i++)
		if (tab[i]!=0)
			premiers.push_back(i);	

	logger->DEBUG("Nombre de premiers : "+(int)premiers.size());

	return premiers;
}


Nombre::Nombre(void)
{
}

Nombre::~Nombre(void)
{
}
