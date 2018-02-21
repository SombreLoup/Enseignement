/*
 * Main.cpp
 *
 *  Created on: 30 sept. 2015
 *      Author: yann
 */




#include	"Ecran.h"
#include 	"DisqueDur.h"
#include 	"Ordinateur.h"

int main(int argc, char **argv) {
	DisqueDur	dd("Disque SATA RH15000A",1,15000);
	cout << "Description de dd : " << dd.getDescription() << endl;
	cout << "Capacité de stockage de dd : " << dd.getCapacite() << endl;
	cout << "Vitesse de dd : " << dd.getVitesse() << endl;
	cout << "Prix de dd : " << dd.getPrix() << endl;

	Ecran	*ptr_ecr = new Ecran("Ecran TFT", Ecran::QSXGA, 27, 0.21);
	cout << *ptr_ecr << " - Prix : " << ptr_ecr->getPrix() << " €" << endl;

	cout << endl;

	Ordinateur	ordi("Configuration bureautique");
	ordi += &dd;
	ordi += ptr_ecr;

	cout << ordi << endl;

	ordi.supprimer(0);

	cout << ordi << endl;
}
