#include "Filtre.h"

#include <iostream>
using namespace std;

Filtre::Filtre(void)
{
}

Filtre::~Filtre(void)
{
}

string Filtre::filtrer(string chaine)
{
	string  accents = "àâäaAéèêëEîïîiIôöooOùûüuU";
	string	alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	string	chaineFiltree;

	for (int i=0; i<chaine.length(); i++)
	{
		int	posAccents = accents.find(chaine[i]);


		cout << "Chaine[" << i << "]=" << chaine[i] << " ";


		string lettre;
		lettre += toupper(chaine[i]);
		

		cout << "Lettre=" << lettre << " ";

		int	posAlphabet= alphabet.find(lettre);

		cout << "PosAccent=" << posAccents << " ";
		cout << "PosAlphabet=" << posAlphabet << " ";

		if (posAlphabet == -1)
			lettre = string();

		cout << "Lettre finale=" << lettre << endl;

		chaineFiltree += lettre;
	}

	return chaineFiltree;
}
