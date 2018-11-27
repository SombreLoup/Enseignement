#pragma once

#include	<iostream>
#include	<fstream>
using namespace std;

class Fichier
{
private:
	Fichier(void);
	~Fichier(void);

public:
	static	string	chargerFichierTexte(string nomFichier); 
	static	void	sauverFichierTexte(string nomFichier, string chaine); 
};
