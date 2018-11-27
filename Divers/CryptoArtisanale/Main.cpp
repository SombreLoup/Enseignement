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
		cout << "t[" << i << "] : " << t[i] << endl;
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

	string s = "Je suis tres emue de vous dire que j'ai bien compris l'autre soir que vous aviez toujours une envie folle de me faire danser. Je garde le souvenir de votre baiser et je voudrais bien que ce soit la une preuve que je puisse etre aimee par vous. Je suis prete a vous montrer mon affection toute desinteressee et sans calcul, et si vous voulez me voir aussi vous devoiler sans artifice mon ame toute nue, venez me faire une visite. Nous causerons en amis, franchement. Je vous prouverai que je suis la femme sincere, capable de vous offrir l'affection la plus profonde comme la plus etroite en amitie, en un mot la meilleure preuve dont vous puissiez rever, puisque votre ame est libre. Pensez que la solitude où j'habite est bien longue, bien dure et souvent difficile. Ainsi en y songeant j'ai l'ame grosse. Accourrez donc vite et venez me la faire oublier par l'amour ou je veux me mettre.";
	string s_filtre = "JESUISTRESEMUEDEVOUSDIREQUEJAIBIENCOMPRISLAUTRESOIRQUEVOUSAVIEZTOUJOURSUNEENVIEFOLLEDEMEFAIREDANSERJEGARDELESOUVENIRDEVOTREBAISERETJEVOUDRAISBIENQUECESOITLAUNEPREUVEQUEJEPUISSEETREAIMEEPARVOUSJESUISPRETEAVOUSMONTRERMONAFFECTIONTOUTEDESINTERESSEEETSANSCALCULETSIVOUSVOULEZMEVOIRAUSSIVOUSDEVOILERSANSARTIFICEMONAMETOUTENUEVENEZMEFAIREUNEVISITENOUSCAUSERONSENAMISFRANCHEMENTJEVOUSPROUVERAIQUEJESUISLAFEMMESINCERECAPABLEDEVOUSOFFRIRLAFFECTIONLAPLUSPROFONDECOMMELAPLUSETROITEENAMITIEENUNMOTLAMEILLEUREPREUVEDONTVOUSPUISSIEZREVERPUISQUEVOTREAMEESTLIBREPENSEZQUELASOLITUDEOJHABITEESTBIENLONGUEBIENDUREETSOUVENTDIFFICILEAINSIENYSONGEANTJAILAMEGROSSEACCOURREZDONCVITEETVENEZMELAFAIREOUBLIERPARLAMOUROUJEVEUXMEMETTRE";

	//string s_filtre = "SONDESTINEXCEPTIONNELMARQUALEMONDEROMAIN";
	string alphabet_chiffre = "EHDRUSPITNZVXFAWOCJBQMYGKL";
	string s_chiffree = "";

	for (int i=0; i<s_filtre.length();i++) {
		s_chiffree+=alphabet_chiffre[s_filtre[i]-'A'];

	}
	cout << s_chiffree << endl;

	// Fichier::sauverFichierTexte("permutation.txt",s_chiffree);

	ofstream	 fichier("permutation.txt");
	fichier.write(s_chiffree.c_str(), s_chiffree.length());
	fichier.write("\n",1);

	map<string,int>	tabLettres = Statistiques::compterFrequenceLettres(s_chiffree);
	map<string,int>	tabDigrammes = Statistiques::compterFrequenceDigrammes(s_chiffree);
	map<string,int>	tabTrigrammes = Statistiques::compterFrequenceTrigrammes(s_chiffree);

	for( map<string,int>::const_iterator it = tabLettres.begin(); it != tabLettres.end(); ++it )
	    {
	      string key = it->first;
	      int value = it->second;

	      string str = key+";"+to_string(value);
	      fichier.write(str.c_str(), str.length());
	      fichier.write("\n",1);
	    }

	fichier.close();


	return 0;
}


int main2()
{
	logger->setNiveau(Appender::INFO);

	string	s = Filtre::filtrer("bonjourbonjour");

	cout << "s filtrée : " << s << endl;

	map<string,int>	tabLettres = Statistiques::compterFrequenceLettres(s);
	map<string,int>	tabDigrammes = Statistiques::compterFrequenceDigrammes(s);
	map<string,int>	tabTrigrammes = Statistiques::compterFrequenceTrigrammes(s);


	cout << "Fr�quence des lettres : \n" << tabLettres << endl;
	cout << "Fr�quence des digrammes : \n" << tabDigrammes << endl;
	cout << "Fr�quence des trigrammes : \n" << tabTrigrammes << endl;

	string message = Fichier::chargerFichierTexte("message.txt");
//	cout << "Message = " << message << endl;

	string messageFiltre = Filtre::filtrer(message);
//	cout << messageFiltre << endl;

/*	Fichier::sauverFichierTexte("messageFiltre.txt",messageFiltre);
	cout << "IC(messageFiltr�) = " << Statistiques::indiceCoincidence(messageFiltre) << endl;

	string messageAleatoire = creerChaineAleatoire(500);
	cout << "IC(messageAleatoire) = " << Statistiques::indiceCoincidence(messageAleatoire) << endl;
*/

	string	messageClairVigenere = Fichier::chargerFichierTexte("MessageCesar.txt");
	logger->INFO("Message en clair : "+messageClairVigenere);

	messageClairVigenere = Filtre::filtrer(messageClairVigenere);
	logger->INFO("Message en clair filtr� : "+messageClairVigenere);

	string	messageChiffreVigenere = Vigenere::chiffrer(messageClairVigenere,"PROU");
	logger->INFO("Message en chiffr� : "+messageChiffreVigenere);

	Fichier::sauverFichierTexte("MessageCesarChiffreVigenere.txt", messageChiffreVigenere);
	cout << "Fr�quence des lettres : " << endl << Statistiques::compterFrequenceLettres(messageChiffreVigenere) << endl;

	string	messageChiffreTemoin = "HCEWSHHZGSMQVIHXCEGSAARKEJOCXADBUXFDARBBTHCAWHHFBFTIEBJTFJXZASRFPXHZXIMSKUFXZCTBIWCLOEDLROHIIESRCLKOCHIXTDFDTHTIIXHSSDTUDULXDDIILCCOJVSCGZHBECCBHXELXGIFRMSVSVMHPQKBQXSEAOQWCXWAFVICJGJTZTGWKCCHZXFTGIHAPWEXGYIJJIPIIAWCSKTZDQVTBPHCTBIWHNSTBTHBFIVKOCHCTUPICXDJWJNHXZZLOHSJESVWFGGECLKGTAGTFTFUNDDIMHWG";
	if (messageChiffreVigenere==messageChiffreTemoin)
		logger->INFO("Les deux messages chiffr�s sont identiques");


	vector<int>	ecarts = Kasiski::calculerEcarts(messageChiffreVigenere, Statistiques::compterFrequenceTrigrammes(messageChiffreVigenere));

	for (unsigned int i=0; i<ecarts.size(); i++)
	{
		cout << "Facteurs premiers de " << ecarts[i] << " : ";
		vector<int> facteurs = Nombre::getFacteursPremiers(ecarts[i]);
		for (unsigned int k=0; k<facteurs.size();k++)
			cout << facteurs[k] << " ";
		cout << endl;
	}

	double ICreel = Statistiques::indiceCoincidence(messageChiffreVigenere);
	cout << "IC reel : " << ICreel << endl;
	cout << "Indice de co�ncidence pour m=1 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,1) << endl;
	cout << "Ecart pour m=1 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,1)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=2 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,2) << endl;
	cout << "Ecart pour m=2 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,2)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=3 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,3) << endl;
	cout << "Ecart pour m=3 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,3)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=4 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,4) << endl;
	cout << "Ecart pour m=4 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,4)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=5 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,5) << endl;
	cout << "Ecart pour m=5 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,5)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=6 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,6) << endl;
	cout << "Ecart pour m=6 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,6)-ICreel) << endl;
	cout << "Indice de co�ncidence pour m=7 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,7) << endl;
	cout << "Ecart pour m=7 : " << fabs(Statistiques::indiceCoincidence(messageChiffreVigenere,7)-ICreel) << endl;

	unsigned int	periode = 5;
	cout << "Indice coincide alphabet 1 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,periode,0) << endl;
	cout << "Indice coincide alphabet 2 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,periode,1) << endl;
	cout << "Indice coincide alphabet 3 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,periode,2) << endl;
	cout << "Indice coincide alphabet 4 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,periode,3) << endl;
	cout << "Indice coincide alphabet 5 : " << Statistiques::indiceCoincidence(messageChiffreVigenere,periode,4) << endl;

	return 0;
}
	
