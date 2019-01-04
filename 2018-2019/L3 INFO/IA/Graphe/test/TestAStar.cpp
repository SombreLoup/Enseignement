/*
 * TestAStar.cpp
 *
 *  Created on: 29 déc. 2018
 *      Author: yann
 */

#include	<fstream>
using namespace std;

#include	"Point2D.h"
#include	"AStar.h"
#include	"Graphe.h"
#include	"InfoAstar.h"
#include	"HeuristiquesAStar.h"

char **grilleCar;
int hGrilleCar, lGrilleCar;

InfoAStar**	initGrilleDeSommets(ifstream& fichier, int& hauteur, int& largeur) {
	InfoAStar	**grille;

	string ligne;
	getline(fichier,ligne);
	assert(ligne=="type octile");

	getline(fichier,ligne);
	sscanf(ligne.c_str(),"height %d", &hauteur);

	getline(fichier,ligne);
	sscanf(ligne.c_str(),"width %d", &largeur);

	getline(fichier,ligne);
	assert(ligne=="map");

	grille = new InfoAStar*[hauteur];
	grilleCar = new char*[hauteur]; hGrilleCar = hauteur;
	for (int l = 0; l < hauteur; ++l) {
		grille[l] = new InfoAStar[largeur];
		grilleCar[l] = new char[largeur]; lGrilleCar = largeur;

		getline(fichier,ligne);
		assert(ligne.length()==largeur);
		for (int c=0; c<largeur; c++) {
			grille[l][c] = InfoAStar(string(1,ligne[c]), Point2D(c,l));
			grilleCar[l][c] = ligne[c];
		}
	}

	return grille;
}

Graphe<InfoAStar,double>* initGraphe(InfoAStar	**grille, int hauteur, int largeur) {
	Graphe<InfoAStar,double>	*carte = new Graphe<InfoAStar,double>();
	Sommet<InfoAStar> ***sommets;

	sommets = new Sommet<InfoAStar>**[hauteur];
	for (int l=0; l<hauteur; l++) {
		sommets[l] = new Sommet<InfoAStar>*[largeur];
	}


	for (int l=0; l<hauteur; l++) {
		for (int c=0; c<largeur; c++) {
			sommets[l][c] = carte->ajouterSommet(grille[l][c]);

			if (grille[l][c].getNomVille()==".") {
				if (l>0 && grille[l-1][c].getNomVille()==".")
					carte->ajouterArete(sommets[l][c], sommets[l-1][c], 0);
				if (c>0 && grille[l][c-1].getNomVille()==".")
					carte->ajouterArete(sommets[l][c], sommets[l][c-1], 0);
			}
		}
	}

	return carte;
}



Graphe<InfoAStar,double>* lireCarte(string nomFichierCarte) {
	Graphe<InfoAStar,double>	*carte = NULL;
	InfoAStar	**grilleInfo;
	int	hauteur, largeur;

	ifstream fichier(nomFichierCarte, ios::in);  // on ouvre le fichier en lecture

	if(fichier)
    {
		grilleInfo = initGrilleDeSommets(fichier, hauteur, largeur);
    	fichier.close();
    	cout << "La carte " << nomFichierCarte << " lue" << endl;
    	carte = initGraphe(grilleInfo, hauteur, largeur);
	}
	else
		cerr << "Impossible d'ouvrir le fichier !" << endl;

	return carte;
}

Sommet<InfoAStar>* rechercherSommet(Graphe<InfoAStar,double>* carte, const Point2D& p) {
	bool	trouve = false;
	Sommet<InfoAStar>* sommet = NULL;
	Liste<Sommet<InfoAStar>* > l = carte->getListeSommets();
	Iterateur<Sommet<InfoAStar>* > it = l.getIterateur();

	while (!trouve && it.hasNext()) {
		sommet = it.next();
		if (sommet->getInformation().getPosition()==p)
			trouve = true;
	}

	return sommet;
}


void ecrireCarteAvecChemin(string nomFichier, Liste<Sommet<InfoAStar>* > chemin) {
	Iterateur<Sommet<InfoAStar>* > it = chemin.getIterateur();
	while (it.hasNext()) {
		Sommet<InfoAStar>* sommet = it.next();
		Point2D position = sommet->getInformation().getPosition();
		grilleCar[(int)position.getY()][(int)position.getX()] = 'O';
	}

	ofstream fichier(nomFichier, ios::out);  // on ouvre le fichier en lecture

	if(fichier)
    {
		for (int l=0; l<hGrilleCar; l++) {
			for (int c=0; c<lGrilleCar; c++) {
				fichier.write(&grilleCar[l][c], 1);
			}
			fichier.write("\n",1);
		}
		fichier.close();
		cout << "La carte avec le chemin est écrite dans " << nomFichier << endl;
	}
	else
		cerr << "Impossible d'ouvrir le fichier !" << endl;

}

typedef Graphe<InfoAStar,double>	GrapheAStar;
typedef Sommet<InfoAStar>			SommetAStar;


int main(int argc, char **argv) {

	GrapheAStar	*carte = lireCarte("map/arena.map");
	cout << "Nombre de sommets : " << carte->getNombreSommets() << endl;
	cout << "Nombre d'arêtes : " << carte->getNombreAretes() << endl;

	SommetAStar	*depart = rechercherSommet(carte, Point2D(32,4));
	SommetAStar	*arrivee = rechercherSommet(carte, Point2D(19,47));

	Liste<SommetAStar* > chemin = AStar<GrapheAStar, SommetAStar>::executer(carte, depart, arrivee, calculerDistanceEuclidienne);

	ecrireCarteAvecChemin("map/arena_res.map",chemin);
}


