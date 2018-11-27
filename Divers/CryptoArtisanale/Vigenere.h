#pragma once

#include	<string>
using namespace std;

class Vigenere
{
public:
	static	string	chiffrer(string message, string cle);

private:
	static	char	chiffrer(char lettre, int indice, string cle);

private:
	Vigenere(void);
	~Vigenere(void);
};
