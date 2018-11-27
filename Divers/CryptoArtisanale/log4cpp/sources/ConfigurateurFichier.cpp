/*
 * ConfigurateurFichier.cpp
 *
 *  Created on: 22 nov. 2008
 *      Author: Yann
 */

#include	"ConfigurateurFichier.h"
#include	"Logger.h"
#include	"ConsoleAppender.h"
#include	"FileAppender.h"

#include	<list>
#include	<map>
using namespace std;


class InfoAppender
{
private:
	string				nom;
	string				type;
	int					niveau;
	string				fichier;

public:
	InfoAppender(string n) : nom(n), type(), niveau(Appender::DEBUG), fichier() {}

	string	getNom()			const	{ return nom;	}
	void	setNom(string n)			{ this->nom = n;	}

	string	getType()			const	{ return type;	}
	void	setType(string t)			{ this->type = t;}

	string	getFichier()		const	{ return fichier;	}
	void	setFichier(string t)		{ this->fichier = t;}

	int		getNiveau()		const		{ return niveau;	}
	void	setNiveau(int n)			{ this->niveau = n;}

	bool	operator==(const InfoAppender& ia)	const { return nom == ia.nom;	}
	friend ostream&	operator<<(ostream& out, const InfoAppender& ia);
};


ostream&	operator<<(ostream& out, const InfoAppender& ia)
{
	out << ia.nom << " " << ia.type << " " << ia.niveau << " " << ia.fichier;
	return out;
}


list<InfoAppender>	merge(list<InfoAppender> l1, list<InfoAppender> l2)	{
	list<InfoAppender>	l = l1;
	list<InfoAppender>::const_iterator it = l2.begin();

	while (it!=l2.end())
	{
		l.push_back(*it);
		it++;
	}

	return l;
}




class InfoLogger
{
private:
	string				nom;
	list<InfoAppender>	listAppenders;

public:
	InfoLogger(string n, list<InfoAppender> l) : nom(n), listAppenders(l) {}
	InfoLogger(const InfoLogger& il) : nom(il.nom), listAppenders(il.listAppenders) {}

	string	getNom()			const	{ return nom;	}
	void	setNom(string n)			{ this->nom = n;	}

	void				set(list<InfoAppender> lia)		{ 	listAppenders=lia;	}
	list<InfoAppender>	get()			const		{ return listAppenders;						}

	const InfoLogger&	operator=(const InfoLogger& il)	{	nom = il.nom;	listAppenders = il.listAppenders; return *this;	}

};

InfoLogger	getLogger(string nl, list<InfoLogger> lil)
{
	bool 	trouve=false;
	list<InfoLogger>::const_iterator it = lil.begin();
	while (!trouve && (it!=lil.end()))
	{
		if ((*it).getNom() == nl)
			trouve = true;
		else
			it++;
	}

	if (trouve)
		return (*it);
	else
	{
		cerr << "Lle logger " << nl << " n'existe pas" << endl;
		exit(-1);
	}
}


InfoAppender	getAppender(string na, list<InfoAppender> lia)
{
	bool 	trouve=false;
	list<InfoAppender>::const_iterator it = lia.begin();
	while (!trouve && (it!=lia.end()))
	{
		if ((*it).getNom() == na)
			trouve = true;
		else
			it++;
	}

	if (trouve)
		return (*it);
	else
	{
		return InfoAppender(na);

		cerr << "L'appender " << na << " n'existe pas" << endl;
	}
}



bool	estCommandeLog4cpp(string chaine)
{
	return chaine.find("log4cpp",0) == 0;
}

string	getDescriptionPropriete(string chaine)
{
	int	posDebut	=	chaine.find("=",0);
	if (posDebut==-1)
		return "";
	return chaine.substr(0,posDebut);
}

string	getValeur(string chaine)
{
	int	posDebut	=	chaine.find("=",0)+1;
	if (posDebut==-1)
		return "";
	return chaine.substr(posDebut,chaine.length()-posDebut);
}

string	getTypeCommande(string chaine)
{
	int	posDebut	=	chaine.find(".",0)+1;
	if (posDebut==-1)
		return "";
	int	posFin		= 	chaine.find(".", posDebut);
	if (posFin==-1)
		return "";

	return chaine.substr(posDebut,posFin-posDebut);
}


string	getNom(string chaine)
{
	int	posDebut	=	chaine.find(".",0)+1;
	posDebut = chaine.find(".", posDebut)+1;
	if (posDebut==0)
		return "";
	int	posFin		= 	chaine.find(".", posDebut);
	if (posFin==-1)
		posFin		= 	chaine.length();

	return chaine.substr(posDebut,posFin-posDebut);
}

string	getPropriete(string chaine)
{
	int	posProp		= 	chaine.find_last_of(".")+1;
	if (posProp==-1)
		return "";
	return chaine.substr(posProp,chaine.length()-posProp);
}


list<InfoAppender> configureListeAppenders(string chaine)
{
	list<InfoAppender>	l;

	int	posDebut = 0;
	int	posFin = chaine.find(",", posDebut);
	posFin = (posFin==-1 ? chaine.length() : posFin);
	while (posDebut<(int)chaine.length())
	{
		string nom = chaine.substr(posDebut, posFin);

		l.push_back(InfoAppender(nom));

		posDebut = posFin+1;
		posFin = chaine.find(",", posDebut);
		posFin = (posFin==-1 ? chaine.length() : posFin);
	}

	return l;
}


void	configurerLogger(list<InfoLogger> lil, list<InfoAppender> lia)
{
	list<InfoLogger>::const_iterator it = lil.begin();

	for (unsigned int k=0; k<lil.size(); k++, it++)
	{
		InfoLogger	il = (*it);
		list<InfoAppender>	listeApp = il.get();

		list<InfoAppender>::const_iterator	itia = listeApp.begin();
		Logger	*logger = Logger::getLogger(il.getNom());

		for (unsigned int i=0; i<listeApp.size(); i++, itia++)
		{
			string			nomIA 	= (*itia).getNom();
			InfoAppender	ia 		= getAppender(nomIA,lia);
			Appender		*appender;

			if (ia.getType()=="fichier")
				appender = new FileAppender(ia.getFichier(), ia.getNiveau());
			else
				appender = new ConsoleAppender(ia.getNiveau());

			logger->add(appender);
		}
	}
}


/**
 * Commandes trait�es :
 * log4cpp.logger.'nom_logger'='nom_appender_1', 'nom_appender_2', ...
 * log4cpp.appender.'nom_appender'.type={console,fichier}
 * log4cpp.appender.'nom_appender'.niveau={DEBUG,INFO,WARN,ERROR,FATAL}
 * log4cpp.appender.'nom_appender'.fichier='nom_fichier'
 */
void	ConfigurateurFichier::configure(string nomFichier){
	list<InfoAppender>	listeAppenders;
	list<InfoLogger>	listeLoggers;


	ifstream	in(nomFichier.c_str());

	string	ligne;
	in >> ligne;
	while (in.good())
	{
		if (estCommandeLog4cpp(ligne))
		{
			string	descriptionPropriete 	= getDescriptionPropriete(ligne);
			string	typeCommande			= getTypeCommande(descriptionPropriete);
			string	nom						= getNom(descriptionPropriete);
			string	propriete				= getPropriete(descriptionPropriete);
			string	valeur					= getValeur(ligne);

			if ((nom == "") || (typeCommande == "") || (valeur == ""))
			{
				cerr << "Commande log4cpp incorrecte : " << ligne << endl;
				exit(-1);
			}


			if (typeCommande=="logger")
			{
				list<InfoAppender>	lia =  configureListeAppenders(valeur);
				InfoLogger	il(nom,lia);
				listeLoggers.push_back(il);
				listeAppenders = merge(listeAppenders,lia);
			}
			else if (typeCommande=="appender")
			{
				InfoAppender	ia = getAppender(nom, listeAppenders);
				listeAppenders.remove(ia);

				if (propriete=="type")
					ia.setType(valeur);
				else if (propriete=="niveau")
				{
					if (valeur=="DEBUG")
						ia.setNiveau(Appender::DEBUG);
					else if (valeur=="INFO")
						ia.setNiveau(Appender::INFO);
					else if (valeur=="WARN")
						ia.setNiveau(Appender::WARN);
					else if (valeur=="ERROR")
						ia.setNiveau(Appender::ERROR);
					else if (valeur=="FATAL")
						ia.setNiveau(Appender::FATAL);
					else
					{
						cerr << "Niveau inconnue : " << ligne << endl;
						exit(-1);
					}
				}
				else if (propriete=="fichier")
					ia.setFichier(valeur);
				else
				{
					cerr << "Propriete inconnue : " << ligne << endl;
					exit(-1);
				}

				listeAppenders.push_back(ia);
			}
			else
			{
				cerr << "Commande inconnue : " << ligne << endl;
				exit(-1);
			}
		}
		in >> ligne;
	}

	configurerLogger(listeLoggers, listeAppenders);

	in.close();
}




