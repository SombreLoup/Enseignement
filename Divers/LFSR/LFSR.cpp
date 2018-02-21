//============================================================================
// Name        : LFSR.cpp
// Author      : 
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <string>
#include <fstream>
using namespace std;

#include	"Word.h"

#define	TAILLE_REGISTRE		19
#define	TRegistre			Word<TAILLE_REGISTRE>

#define	TRegistre19			Word<19>
#define	TRegistre22			Word<22>
#define	TRegistre23			Word<23>

#define	LONGUEUR_CHAINE		10000
#define	NOMBRE_CHAINE		100

unsigned int	retroaction(const TRegistre& registre) {
	unsigned int	r = 0;

	r = registre[5]^registre[16]; //

	return r;
}

unsigned int	cycle(TRegistre &registre) {
	unsigned int	s = registre[TAILLE_REGISTRE-1]; // Le bit de sortie, c'est le dernier

	unsigned int	r = retroaction(registre); // On calcule le bit de r�troaction

	registre = registre >> 1;	// On d�cale le registre (ici vers la droite)
	registre.setBit(0,r);		// On place le bit de r�troaction (ici, c'est donc � gauche)

	return s;
}


void flux(TRegistre &registre, ostream& f, unsigned int longueur) {
	for (unsigned int i=0; i<longueur; i++) {
		f << cycle(registre);
	}
	f << endl;
}

/******* A5/1 ******************/

unsigned int	retroaction1(const TRegistre19 & registre) {
	unsigned int r = 0;

	return r;
}

unsigned int	retroaction2(const TRegistre22 & registre) {
	unsigned int r = 0;

	return r;
}

unsigned int	retroaction3(const TRegistre23 & registre) {
	unsigned int r = 0;

	return r;
}

unsigned int A5_1(TRegistre19 &R1, TRegistre22 &R2, TRegistre23 &R3) {
	unsigned int s = 0;


	return s;
}



void fluxA5_1(TRegistre19 &R1, TRegistre22 &R2, TRegistre23 &R3, ostream& f, unsigned int longueur) {
	for (unsigned int i=0; i<longueur; i++) {
		f << A5_1(R1,R2,R3);
	}
	f << endl;
}


int main() {
						//	 0123456789012345678901234567890
	TRegistre	registre(	"1010110011100001011");
	//TRegistre19 R1(			"1001010010111010100");
	//TRegistre22 R2(			"0100011101011110111011");
	//TRegistre23 R3(			"11011101001010110001010");

	ofstream	fichier("../STS/MesDonnees.txt");
	//ofstream	fichierA5_1("../STS/MesDonneesA51.txt");

	for (unsigned int i=0; i<NOMBRE_CHAINE; i++) {
		flux(registre,fichier,LONGUEUR_CHAINE);
		//fluxA5_1(R1,R2,R3,fichierA5_1,LONGUEUR_CHAINE);
	}

	fichier.close();
	//fichierA5_1.close();

	cout << "Fichiers générés" << endl;
	cout << "Nombre de chaines générées : " << NOMBRE_CHAINE << endl;
	cout << "Longueur des chaines : " << LONGUEUR_CHAINE << endl;

	return 0;
}
