/*
 * AlgoGenetique.cpp
 *
 *  Created on: 17 nov. 2011
 *      Author: yann
 */
#include	<time.h>
#include	<stdlib.h>
#include	<fstream>
using namespace std;

#include 	"AlgoGenetiqueRelache.h"
#include	"../graphe/Chemin.h"

AlgoGenetiqueRelache::AlgoGenetiqueRelache()
: valeurOptimale(0), ponderationBudget(0), ponderationLongueur(0)
{}

AlgoGenetiqueRelache::AlgoGenetiqueRelache(
		const Graphe& g, const vector<ContreMesure>& M,
		double b,
		double vO,
		const Noeud&	E0,
		const Noeud&	Ef,
		unsigned int nbIndividus,
		unsigned int nbGenerations,
		double tauxMutation)
: AlgoGenetique(g,M,E0,Ef,b,nbIndividus,nbGenerations,tauxMutation), valeurOptimale(vO), ponderationBudget(0), ponderationLongueur(0)
{
}

Population	AlgoGenetiqueRelache::creerPopulation()
{
	Population	population;

	while (population.getNombreIndividus() < nombreIndividus)
			population.add(creerIndividu());

	return	population;
}


static unsigned int	getIndiceAleatoire(unsigned int borneSup)
{
	return rand() % borneSup;
}

static double	getReelNormeAleatoire()
{
	return (rand() % MAX_INPUT)/(double)MAX_INPUT;
}


Affectation	AlgoGenetiqueRelache::creerIndividu()
{
	Affectation	affectation(tousLesArcs.size());

	vector<int>	tabIndices;

	for (unsigned int i=0; i<affectation.getNombreMesure(); i++)
	{
		int				indiceMesure;

		indiceMesure = getIndiceAleatoire(ensembleMesure.size());

		tabIndices.push_back(indiceMesure);

		ContreMesure	m = ensembleMesure[indiceMesure];

		affectation.affecterMesure(i,m);
	}

	affectation.mettreAJour(graphe, E0, Ef);

	return affectation;
}

void	AlgoGenetiqueRelache::croiser(const Affectation& pere, const Affectation& mere, Affectation& fils, Affectation& fille)
{
	if (pere.getNombreMesure() != mere.getNombreMesure())
		throw Exception("AlgoGenetique::croiser --> les parents n'ont pas la m�me longueur");

	unsigned int position = 1+getIndiceAleatoire(pere.getNombreMesure()-1);

	fils = pere;
	for (unsigned int i=position; i<pere.getNombreMesure(); i++)
		fils.affecterMesure(i,mere.getMesure(i));

	fils.mettreAJour(graphe, E0, Ef);

	fille = mere;
	for (unsigned int i=position; i<mere.getNombreMesure(); i++)
		fille.affecterMesure(i,pere.getMesure(i));

	fille.mettreAJour(graphe, E0, Ef);
}

void	AlgoGenetiqueRelache::muter(const Affectation& original, Affectation& mutant)
{
	mutant = original;

	cout << "Mutation!" << endl;

	unsigned int position = getIndiceAleatoire(original.getNombreMesure());
	Arc	a = tousLesArcs[position];
	unsigned int indiceContreMesure = getIndiceAleatoire(ensembleMesure.size());

	while (!ensembleMesure[indiceContreMesure].peutAmeliorer(a))
		indiceContreMesure = getIndiceAleatoire(ensembleMesure.size());


	mutant.affecterMesure(position, ensembleMesure[indiceContreMesure]);
	mutant.mettreAJour(graphe, E0,Ef);
}

double	AlgoGenetiqueRelache::evaluer(Affectation& affectation)
{
	double longueur = affectation.getLongueur();

	double score = ponderationBudget*(budget/affectation.getBudget())+ponderationLongueur*(longueur/valeurOptimale);

	return score;
}


double AlgoGenetiqueRelache::getValeurTotale(Population & population)
{
    double somme = 0;
    for(unsigned int i = 0;i < population.getNombreIndividus();i++)
        somme += evaluer(population.getIndividu(i));

    return somme;
}

double	AlgoGenetiqueRelache::moyenne(Population& population)
{
	return getValeurTotale(population)/population.getNombreIndividus();
}


void	AlgoGenetiqueRelache::selectionner()
{
	if (nouvellePopulation.getNombreIndividus() < nombreIndividus)
		throw Exception("AlgoGenetique::selectionner --> La nouvelle population ne contient pas assez d'individu");

	double	valeurTotale = getValeurTotale(nouvellePopulation);

	Affectation meilleure = meilleureAffectation(populationCourante);
	populationCourante = Population();
	populationCourante.add(meilleure);

	for (unsigned int i=1; i<nombreIndividus; i++)
	{
		double	valeurAleatoire = getReelNormeAleatoire()*valeurTotale;

		double			valeurCourante = 0;
		bool			trouve = false;
		unsigned int 	indiceIndividuExamine = 0;
		while (!trouve)
		{
			double	valeurIndividuExamine = evaluer(nouvellePopulation.getIndividu(indiceIndividuExamine));
			valeurCourante += valeurIndividuExamine;

			if ( valeurCourante >= valeurAleatoire)
			{
				trouve = true;
				populationCourante.add(nouvellePopulation.getIndividu(indiceIndividuExamine));
				nouvellePopulation.remove(indiceIndividuExamine);
				valeurTotale -= valeurIndividuExamine;
			}
			else
			{
				indiceIndividuExamine++;
			}
		}
	}
}


Affectation&	AlgoGenetiqueRelache::meilleureAffectation(Population& population)
{
	double			meilleurScore = evaluer(population.getIndividu(0));
	unsigned int	indiceMeilleur = 0;

	for (unsigned int i = 1; i < population.getNombreIndividus(); ++i)
	{
		double	score = evaluer(population.getIndividu(i));
		if (score > meilleurScore)
		{
			meilleurScore = score;
			indiceMeilleur = i;
		}
	}

	return population.getIndividu(indiceMeilleur);
}


void AlgoGenetiqueRelache::reproduire()
{
	nouvellePopulation = populationCourante;
	while (nouvellePopulation.getNombreIndividus()<2*populationCourante.getNombreIndividus())
	{
		unsigned int	indicePere, indiceMere;

		indicePere = getIndiceAleatoire(populationCourante.getNombreIndividus());
		indiceMere = getIndiceAleatoire(populationCourante.getNombreIndividus());

		Affectation	pere = populationCourante.getIndividu(indicePere);
		Affectation	mere = populationCourante.getIndividu(indiceMere);
		Affectation fils = pere;
		Affectation fille = mere;

		croiser(pere,mere,fils,fille);

		nouvellePopulation.add(fils);
		nouvellePopulation.add(fille);
	}
}


void AlgoGenetiqueRelache::muter()
{
	if (getReelNormeAleatoire()<tauxMutation)
	{
		unsigned indiceOriginal = getIndiceAleatoire(populationCourante.getNombreIndividus());

		Affectation original = populationCourante.getIndividu(indiceOriginal);

		Affectation	mutant;
		muter(original,mutant);

		populationCourante.remove(indiceOriginal);
		populationCourante.add(mutant);
	}
}

void AlgoGenetiqueRelache::evoluer()
{
	populationCourante = creerPopulation();
	valeurOptimale = graphe.PlusCourtChemin(E0,Ef).getLongueur(graphe);

	traceur->tracer("Generation", this);

	valeurOptimale = graphe.PlusCourtChemin(E0,Ef).getLongueur(graphe);

	numeroGeneration = 1;

	while (numeroGeneration<nombreGeneration)
	{
		reproduire();
		selectionner();
		muter();

		traceur->tracer("Generation", this);

		numeroGeneration++;
	}
}

void AlgoGenetiqueRelache::setValeurOptimale(double v)
{
	valeurOptimale = v;
}

void AlgoGenetiqueRelache::setPonderationBudget(double v)
{
	ponderationBudget = v;
}

void AlgoGenetiqueRelache::setPonderationLongueur(double v)
{
	ponderationLongueur = v;
}
