#pragma once

#include	<string>
using namespace std;

class Filtre
{
private:
	Filtre(void);
	~Filtre(void);

public:
	static	string filtrer(string chaine);
};
