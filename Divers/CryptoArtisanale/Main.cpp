#include	<string>
#include	<iostream>
#include	<fstream>
using namespace std;

#include	"Statistiques.h"
#include	"Filtre.h"
#include	"Fichier.h"
#include	"Nombre.h"
#include	"Kasiski.h"
#include	"Vigenere.h"
#include	<time.h>
#include	<math.h>

#include	<Logger.h>
static	Logger *logger = Logger::getLogger("root",Appender::DEBUG);


ostream&	operator<<(ostream& out, const map<string,int>& t)
{
	map<string,int>::const_iterator it = t.begin();
	for (unsigned int i=0; i<t.size(); i++)
	{
		cout << (*it).first << " : " << (*it).second << endl;
		it++;
	}

	return out;
}


ostream&	operator<<(ostream& out, const vector<int>& t)
{
	for (unsigned int i=0; i<t.size(); i++)
	{
		cout << "t[" << i << "] : " << t[i];
	}

	return out;
}


string creerChaineAleatoire(int longueur)
{
	string	chaine;
	srand(time(NULL));

	for (int i=0; i<longueur; i++)
	{
		int nombre = (int)((double)rand() / ((double)RAND_MAX + 1) * 26); 
		chaine += 'A'+nombre;
	}

	return chaine;
}


int main() {
	logger->setNiveau(Appender::INFO);

	string s = Fichier::chargerFichierTexte("helmut.txt");
	string s_filtre = Filtre::filtrer(s);

	cout << s_filtre << endl;


	string s_chiffree = "";
	s_chiffree = Vigenere::chiffrer(s_filtre,"HELMUT");

	cout << s_chiffree << endl;
	cout << "Longueur : " << s_chiffree.length() << endl;

	map<string,int>	tabLettres = Statistiques::compterFrequenceLettres(s_chiffree);
	map<string,int>	tabDigrammes = Statistiques::compterFrequenceDigrammes(s_chiffree);
	map<string,int>	tabTrigrammes = Statistiques::compterFrequenceTrigrammes(s_chiffree);

	string s1 = "";
	int i=2;
	while (i<s_chiffree.length()) {
		s1 = s1+s_chiffree[i];
		i += 6;
	}

	map<string,int>	tabLettresS1 = Statistiques::compterFrequenceLettres(s1);
	cout << "Longueur de S1 : " << s1.length() << endl;
	cout << tabLettresS1 << endl;


	return 0;
}


