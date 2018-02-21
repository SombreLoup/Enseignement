/*
 * Main.cpp
 *
 *  Created on: 1 oct. 2015
 *      Author: yann
 */


#include	"Poignee.h"
#include 	"Meuble.h"
#include 	"Materiau.h"

int main(int argc, char **argv) {
	Poignee	poignee("Poignee bidule",10);
	cout << poignee << endl;

	Materiau	mat("Chne", 20);
	cout << mat << endl;

	Meuble	meuble("Element cuisine 1", 49);
	cout << meuble << endl;
	cout << "Prix du meuble sans options : " << meuble.getPrix() << endl;

	meuble+=&poignee;
	meuble+=&mat;
	cout << meuble << endl;
	cout << "Prix du meuble avec options : " << meuble.getPrix() << endl;
	cout << "Nombre d'options : " << meuble.getNombreOptions() << endl;
	cout << "Option[0]=" << *(meuble[0]) << endl;

	meuble.supprimer(0);
	cout << meuble << endl;
	cout << "Prix du meuble avec options : " << meuble.getPrix() << endl;
	cout << "Nombre d'options : " << meuble.getNombreOptions() << endl;


	return 0;
}
