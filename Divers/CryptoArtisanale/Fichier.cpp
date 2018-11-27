#include "Fichier.h"

#include	"Logger.h"
static	Logger	*logger = Logger::getLogger();

Fichier::Fichier(void)
{
}

Fichier::~Fichier(void)
{
}

int	getTailleFichier(ifstream& fichier)
{
	fichier.seekg(0,ios_base::end);
	int taille = fichier.tellg();
	fichier.seekg(0,ios_base::beg);

	return taille;
}

string	Fichier::chargerFichierTexte(string nomFichier)
{
	logger->DEBUG("Nom du fichier : "+nomFichier);

	ifstream	fichier(nomFichier.c_str());

	int			length = getTailleFichier(fichier);
	
	ostringstream	s; s << length;
	logger->DEBUG("Longueur du fichier : "+s.str());

	char*		donnees = (char*)malloc(length+1);
	fichier.read(donnees,length);
	donnees[length] = '\0';

	logger->DEBUG(string()+"Contenu du fichier : "+donnees);

	fichier.close();

	return donnees;
}


void	Fichier::sauverFichierTexte(string nomFichier, string chaine)
{
	ofstream	fichier(nomFichier.c_str());

	unsigned int			length = chaine.length();

	fichier.write(chaine.c_str(), length);

	fichier.close();
}

