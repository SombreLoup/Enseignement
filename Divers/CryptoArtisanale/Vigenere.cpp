#include "Vigenere.h"

#include	"Logger.h"
static Logger	*logger = Logger::getLogger();

char	Vigenere::chiffrer(char lettre, int indice, string cle)
{
	if ((lettre<'A') || (lettre>'Z'))
		logger->FATAL("La lettre à chiffrer n'est pas filtrée...");

	int		position = indice % cle.length();
	char	alphabet = cle[position];
	int		decalage = alphabet-'A';
	int		positionLettreChiffree = (((lettre-'A')+decalage) % 26); 
	char	c = (char)('A'+positionLettreChiffree);
	
	return c;
}

string Vigenere::chiffrer(string message, string cle)
{
	string	s;

	for (unsigned int i=0; i<message.length(); i++) {
		s += chiffrer(message[i], i, cle);
	}

	return s;
}


Vigenere::Vigenere(void)
{
}

Vigenere::~Vigenere(void)
{
}
