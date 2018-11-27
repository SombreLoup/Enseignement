#pragma once

#include	<string>
#include	<map>
using namespace	std;



class Statistiques
{
private:
	Statistiques(void);
	~Statistiques(void);

public:
	static	int				compterOccurrences(string chaine, string motif);
	static	map<string,int>	compterFrequenceLettres(string chaine);
	static	map<string,int>	compterFrequenceDigrammes(string chaine);
	static	map<string,int>	compterFrequenceTrigrammes(string chaine);
	static	double			indiceCoincidence(string chaine);
	static	double			indiceCoincidence(string chaine, int longueurCle);
	static	double			indiceCoincidence(string chaine, int periode, int positionDepart);
};
