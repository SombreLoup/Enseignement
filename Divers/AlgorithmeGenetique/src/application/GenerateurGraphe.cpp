#include	<iostream>
#include	<fstream>
using namespace std;

#include	<time.h>

#include	"../graphe/Graphe.h"
#include	"../graphe/Chemin.h"


#include	"../xml/Parser.h"





int main(int argc, char **argv) {

	Graphe	graphe;

	try {
		Parser::parserNouveauGraphe("instances/Graphe1_30.parametres.xml",graphe);
	}
	catch (Exception &e) {
		cerr << e.getMessage() << endl;
	}

}

