/*
 * Parser.cpp
 *
 *  Created on: 13 juil. 2012
 *      Author: yann
 */
#include <string.h>
#include 	"Parser.h"
#include	"../commun/Exception.h"
#include	"../graphe/Arc.h"
#include	"../graphe/Noeud.h"
#include	"../AG/Affectation.h"
#include	"../AG/AlgoGenetique.h"
#include	"../AG/AlgoGenetiqueRelache.h"
#include	"../AG/Population.h"
#include	"../AG/Traceur/TraceurCSV.h"
#include	"../AG/Traceur/TraceurGeneration.h"


char*	Parser::itoa(int n)
{
	char	s[1024];
	sprintf(s,"%d",n);
	return strdup(s);
}

Parser::Parser() {
	// TODO Auto-generated constructor stub

}

Parser::~Parser() {
	// TODO Auto-generated destructor stub
}


void	Parser::parserFichierContresMesures(const char* text, vector<ContreMesure>	&M)  throw(Exception)
{
    char nomFichier[1024];
    strcpy(nomFichier, "instances/");
    strcat(nomFichier, text);

	TiXmlDocument doc(nomFichier);
	bool loadOkay = doc.LoadFile();

	if ( !loadOkay ) {
    	char message[1024];
    	sprintf(message, "Le fichier %s n'a pas été trouvé", nomFichier);
        throw Exception(message);
	}

	TiXmlNode* LesMesures = doc.FirstChild( "MESURES" );
	if (!LesMesures)
		throw Exception( "Format de fichier incorrect : balise MESURES non trouvée\n");

	TiXmlNode* UneMesure = LesMesures->ToElement()->FirstChild("MESURE");
	while (UneMesure) {
		const char*	nomMesure = UneMesure->ToElement()->Attribute("nom");
		if (!nomMesure)
			throw Exception( "Format de fichier incorrect : attribut 'nom' non trouvé\n");

		const char*	coutMesure = UneMesure->ToElement()->Attribute("cout");
		if (!coutMesure)
			throw Exception( "Format de fichier incorrect : attribut 'cout' non trouvé\n");

		M.push_back(ContreMesure(nomMesure, atof(coutMesure)));

		UneMesure = UneMesure->NextSibling("MESURE");
	}
}


bool Parser::parserGraphe(const char *nomGraphe, Graphe& g) throw(Exception)
{
    char nomFichierGraphe[1024];
    strcpy(nomFichierGraphe, "instances/");
    strcat(nomFichierGraphe, nomGraphe);
    strcat(nomFichierGraphe, ".dot");


	ifstream	f(nomFichierGraphe);
	if (f.is_open()) {
		f >> g;
		f.close();
		return true;
	}
	else
		return false;
}

bool Parser::parserNouveauGraphe(const char *nomFichier, Graphe& g) {

	TiXmlDocument doc(nomFichier);
	bool loadOkay = doc.LoadFile();

	if ( !loadOkay ) {
	    char message[1024];
	    sprintf(message, "Le fichier n'existe pas : %s",nomFichier);
		throw Exception(message);
	}
	else {
		cout << "Création d'un nouveau graphe" << endl;
	}

	TiXmlNode*	nGraphe = lireRacine(doc,"GRAPHE");
	TiXmlNode*	nNomGraphe = lireNoeud(nGraphe,"NOM");

	const char* 	nomGraphe = nNomGraphe->ToElement()->GetText();

	int nbNoeuds = atoi(lireNoeud(nGraphe, "NOMBRE_NOEUDS")->ToElement()->GetText());
	double densite = atof(lireNoeud(nGraphe, "DENSITE")->ToElement()->GetText());
	double	poidsMin = atof(lireNoeud(nGraphe, "POIDS_MIN")->ToElement()->GetText());
	double poidsMax = atof(lireNoeud(nGraphe, "POIDS_MAX")->ToElement()->GetText());
	int nbNiveaux = atoi(lireNoeud(nGraphe, "NOMBRE_NIVEAUX")->ToElement()->GetText());

	cout << "I nombre de niveaux du graphe : " << nbNiveaux << endl;
	cout << "I Nombre de noeuds : " << nbNoeuds << endl;
	cout << "I Densité : " << densite << endl;
	cout << "I Poids min : " << poidsMin << endl;
	cout << "I Poids max : " << poidsMax << endl;


	Graphe::genererInstance(g,nbNoeuds,nbNiveaux,densite,poidsMin,poidsMax);

	char	nomFichierDot[1024];
	sprintf(nomFichierDot,"instances/%s.dot",nomGraphe);

	ofstream	f(nomFichierDot);
	f << g << endl;
	f.close();

	return loadOkay;
}


TiXmlNode* Parser::lireNoeud(TiXmlNode *parent, const char* nom) throw (Exception)
{
    TiXmlNode *fils = parent->ToElement()->FirstChild(nom);
    if(!fils) {
    	char message[1024];
    	sprintf(message, "Format de fichier incorrect : balise %s non trouvée", nom);
        throw Exception(message);
    }

    return fils;
}


const char* Parser::lireAttribut(TiXmlNode *parent, const char* nom) throw(Exception)
{
   	const char* attribut = parent->ToElement()->Attribute(nom);

   	if(!attribut) {
    	char message[1024];
    	sprintf(message, "Format de fichier incorrect : attribut %s non trouvée", nom);
        throw Exception(message);
    }

    return attribut;
}


TiXmlNode* Parser::lireRacine(TiXmlDocument &doc, const char* nom) throw(Exception)
{
    TiXmlNode *fils = doc.FirstChild(nom);
    if(!fils) {
    	char message[1024];
    	sprintf(message, "Format de fichier incorrect : balise %s non trouvée", nom);
        throw Exception(message);
    }

    return fils;
}

void Parser::parserFichierParametres(const char* nomFichier, AlgoGenetique** ag) throw(Exception)
{

	Graphe graphe;
	vector<ContreMesure> M;

	TiXmlDocument doc(nomFichier);
	bool loadOkay = doc.LoadFile();

	if ( !loadOkay ) {
	    char message[1024];
	    sprintf(message, "Le fichier n'existe pas : %s",nomFichier);
		throw Exception(message);
	}
	else {
		cout << "I"; cout.fill('-'); cout.width(100); cout << "I" << endl;
		cout << "I Ouverture du fichier : " << nomFichier << endl;
		cout << "I"; cout.fill('-'); cout.width(100); cout << "I" << endl;
	}

	/**
	 * Balise INSTANCE
	 */
	TiXmlNode*	nInstance = lireRacine(doc,"INSTANCE");

	const char*	nomInstance = lireAttribut(nInstance,"nom");

	/**
	 * Balise GRAPHE
	 */
	TiXmlNode*	nGraphe = lireNoeud(nInstance,"GRAPHE");
	TiXmlNode *nNomGraphe = lireNoeud(nGraphe,"NOM");
	int nbNiveaux = atoi(lireNoeud(nGraphe, "NOMBRE_NIVEAUX")->ToElement()->GetText());

	cout << "I nombre de niveaux du graphe : " << nbNiveaux << endl;

	const char* nomGraphe = nNomGraphe->ToElement()->GetText();
	bool		grapheParse = parserGraphe(nomGraphe, graphe);


	if (grapheParse) {
		cout << "I Graphe chargé : " << nomGraphe << endl;
		cout << "I Plus court chemin : " << graphe.PlusCourtChemin(Noeud(string("0")), Noeud(string(itoa(nbNiveaux-1)))) << endl;
		cout << "I Longueur du plus court chemin : " << graphe.PlusCourtChemin(Noeud(string("0")), Noeud(string(itoa(nbNiveaux-1)))).getLongueur(graphe) << endl;
	}

	cout << "I"; cout.fill('-'); cout.width(100); cout << "I" << endl;


	/**
	 * Balise MESURES
	 */
	TiXmlNode*	nMesures = lireNoeud(nInstance,"MESURES");
	TiXmlNode*	nCatalogue = lireNoeud(nMesures,"CATALOGUE");

	Parser::parserFichierContresMesures(nCatalogue->ToElement()->GetText(), M);

	TiXmlNode* nPertinences = lireNoeud(nMesures,"PERTINENCES");

	const char*	nomPertinences = nPertinences->ToElement()->GetText();
	bool	pertinenceParse =  parserFichierPertinences(nomPertinences, M);

	if (!pertinenceParse || !grapheParse) {
		ContreMesure::genererContreMesures(graphe, M);
		Parser::enregistrerFichierPertinences(nomInstance, M);
	}

	/**
	 * Balise ALGORITHME_GENETIQUE
	 */
	TiXmlNode*	nAlgo = lireNoeud(nInstance,"ALGORITHME_GENETIQUE");
	TiXmlNode*	nType = lireNoeud(nAlgo,"TYPE");
	TiXmlNode*	nBudget = lireNoeud(nAlgo,"BUDGET");
	TiXmlNode*	nGenerations = lireNoeud(nAlgo,"NB_GENERATIONS");
	TiXmlNode*	nIndividus = lireNoeud(nAlgo,"NB_INDIVIDUS");
	TiXmlNode*	nMutation = lireNoeud(nAlgo,"TAUX_MUTATION");


	if (!strcmp(nType->ToElement()->GetText(),"RELACHE")) {
		*ag = new AlgoGenetiqueRelache();
		TiXmlNode*	nPonderation = lireNoeud(nAlgo,"CRITERE_PONDERATION");
		double ponderationBudget = atof(nPonderation->ToElement()->GetText());
		((AlgoGenetiqueRelache*)(*ag))->setPonderationBudget(ponderationBudget);
		((AlgoGenetiqueRelache*)(*ag))->setPonderationLongueur(1-ponderationBudget);
		cout << "I Algorithme génétique relaché" << endl;
		cout << "I Pondération p=" << atof(nPonderation->ToElement()->GetText()) << endl;
	}
	else if (!strcmp(nType->ToElement()->GetText(),"NORMAL")) {
		*ag = new AlgoGenetique();
		cout << "I Algorithme génétique normal" << endl;

	}
	else
		throw Exception("Type inconnu (les valeurs possibles sont RELACHE ou NORMAL)");

	(*ag)->instancier(graphe,M,Noeud(string("0")), Noeud(string(itoa(nbNiveaux-1))));

	(*ag)->setBudget(atof(nBudget->ToElement()->GetText()));
	cout << "I Budget : " << atof(nBudget->ToElement()->GetText()) << endl;

	(*ag)->setNombreGenerations((int)atof(nGenerations->ToElement()->GetText()));
	cout << "I Nombre de générations : " << atof(nGenerations->ToElement()->GetText()) << endl;

	(*ag)->setNombreIndividus((int)atof(nIndividus->ToElement()->GetText()));
	cout << "I Nombre d'individus par génération : " << atof(nIndividus->ToElement()->GetText()) << endl;

	(*ag)->setTauxMutation(atof(nMutation->ToElement()->GetText()));
	cout << "I Taux de mutation : " << atof(nMutation->ToElement()->GetText()) << endl;

	TiXmlNode*	nTraceurCSV = nAlgo->ToElement()->FirstChild("TRACEUR_CSV");
	if (nTraceurCSV) {
		const char* nomCSV = nTraceurCSV->ToElement()->GetText();
		char nomFichier[1024];
		sprintf(nomFichier,"instances/%s.csv",nomCSV);
		cout << "I Enregistrement des données : " << nomFichier << endl;
		(*ag)->setTraceur(new TraceurCSV(nomFichier,(*ag)->getTraceur()));
	}

	TiXmlNode*	nTraceurGeneration = nAlgo->ToElement()->FirstChild("TRACEUR_GENERATION");
	if (nTraceurGeneration)
		(*ag)->setTraceur(new TraceurGeneration((*ag)->getTraceur()));



	cout << "I"; cout.fill('-'); cout.width(100); cout << "I" << endl << endl;;
}

ContreMesure* RechercheMesure(vector<ContreMesure>& M, const char* desc) {

	bool	trouve = false;
	unsigned int		i = 0;
	while (!trouve && (i<M.size())) {
		if (!strcmp(desc,M[i].getDescription().c_str()))
			trouve = true;
		else
			i++;
	}

	return &(M[i]);
}

bool Parser::parserFichierPertinences(const char* nomInstance, vector<ContreMesure> &M)  throw(Exception)
{
    char nomFichier[1024];
    strcpy(nomFichier, "instances/");
    strcat(nomFichier, nomInstance);
    strcat(nomFichier, ".pertinences.xml");



	TiXmlDocument doc(nomFichier);
	bool loadOkay = doc.LoadFile();

	if ( !loadOkay )
		return false;

	/**
	 * Balise PERTINENCES
	 */
	TiXmlNode*	nPertinences = lireRacine(doc,"PERTINENCES");
	TiXmlNode*	nMesure = nPertinences->FirstChild();

	while (nMesure) {
		const char*	description = nMesure->ToElement()->Value();
		ContreMesure* mesure = RechercheMesure(M, description);
		if (!mesure) {
		    char message[1024];
		    sprintf(message, "Parser::parserFichierPertinences : la contremesure %s n'existe pas",description);
			throw Exception(message);
		}

		TiXmlNode*	nArc = nMesure->FirstChild();
		while (nArc) {
			string sOrigine(nArc->ToElement()->Attribute("origine"));
			string sExtremite(nArc->ToElement()->Attribute("extremite"));
			string sImpact(nArc->ToElement()->Attribute("impact"));
			Noeud	o(sOrigine);
			Noeud	e(sExtremite);
			Arc	arc(o,e);
			arc.setPoids(atof(sImpact.c_str()));
			mesure->addArc(arc);
			nArc = nArc->NextSibling();
		}

		nMesure = nMesure->NextSibling();
	}

	return true;
}

void Parser::enregistrerFichierPertinences(const char* nomInstance, const vector<ContreMesure> &M)  throw(Exception)
{
    char nomFichier[1024];
    strcpy(nomFichier, "instances/");
    strcat(nomFichier, nomInstance);
    strcat(nomFichier, ".pertinences.xml");

    TiXmlDocument doc;
    TiXmlDeclaration * decl = new TiXmlDeclaration( "1.0", "UTF-8", "" );
    TiXmlElement * nPertinences = new TiXmlElement("PERTINENCES");

    doc.LinkEndChild( decl );
	doc.LinkEndChild( nPertinences );

    for (unsigned int cm=0; cm<M.size(); cm++) {
        TiXmlElement * nPertinence = new TiXmlElement(M[cm].getDescription());
    	nPertinences->LinkEndChild(nPertinence);
    	for (unsigned int i=0; i<M[cm].getNombreArcs(); i++) {
            TiXmlElement * nArc = new TiXmlElement("ARC");
            nArc->SetAttribute("origine",M[cm].getArc(i).getOrigine().getLabel());
            nArc->SetAttribute("extremite",M[cm].getArc(i).getExtremite().getLabel());
            nArc->SetDoubleAttribute("impact",M[cm].getArc(i).getPoids());

        	nPertinence->LinkEndChild(nArc);

    	}

    }

	doc.SaveFile( nomFichier );
}
