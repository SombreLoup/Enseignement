package application;

import java.util.ArrayList;

import jeu.HearthstoneException;
import jeu.Heros;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;
import tools.Console;

public class Hearthstone {
	public static void main(String[] args) throws HearthstoneException {
		Console	out = new Console();
				
		//initJoueurAvecConsole(out);
		
		Joueur	joueur1 = new Joueur("WorgenGeant", Heros.getHeros("Jaina"));
		joueur1.setDeck(getDeckNeutre(joueur1));
		out.println(""+joueur1);
		
		Joueur	joueur2 = new Joueur("Ali Baba", Heros.getHeros("Andu"));
		joueur2.setDeck(getDeckNeutre(joueur2));
		out.println(""+joueur2);
		
		IPlateau board = Plateau.getInstance();
		board.ajouterJoueur(joueur1);
		board.ajouterJoueur(joueur2);
		board.demarrerPartie();
		
		while (board.estDemarree()) {
			System.out.println(""+board);
			out.readLine();
			board.finTour(joueur1);
			board.finTour(joueur2);
		}
	}
	
	private static ArrayList<ICarte> getDeckNeutre(IJoueur joueur) {
		ArrayList<ICarte>	deck = new ArrayList<ICarte>();
		
		ICarte c;
		c = new Serviteur(joueur, "Archere Elfe", 1, 1, 1);	deck.add(c);
		c = new Serviteur(joueur, "Archimage", 6, 4, 7);	deck.add(c);
		c = new Serviteur(joueur, "Busard Affamé", 5, 3, 2);	deck.add(c);
		c = new Serviteur(joueur, "Champion de Hurlevent", 7, 6, 6);	deck.add(c);
		c = new Serviteur(joueur, "Chasse-marée murloc", 2, 2, 1);	deck.add(c);
		c = new Serviteur(joueur, "Chasseuse de tranchebauge", 3, 2, 3);	deck.add(c);
		c = new Serviteur(joueur, "Chef de guerre loup-de-givre", 5, 4, 4);	deck.add(c);
		c = new Serviteur(joueur, "Chef de raid", 3, 2, 2);	deck.add(c);
		c = new Serviteur(joueur, "Chevalier de Hurlevent", 4, 2, 5);	deck.add(c);
		c = new Serviteur(joueur, "Chevaucheur de loup", 3, 3, 1);	deck.add(c);
		
		return deck;
		
		
	}

	@SuppressWarnings("unused")
	private static void initJoueurAvecConsole(Console out) throws HearthstoneException {
		out.println("Salut joueur 1, quel est ton pseudo ?");
		String pseudo = out.readLine();
		
		String nom;
		out.println("Voici la liste des héros :");
		out.println("--------------------------");
		Heros.afficherListe();
		out.println("");
		out.println("Lequel veux-tu (un bout du nom suffira) ?");
		nom = out.readLine();
		out.println("Tu as choisi : "+Heros.getHeros(nom));
		out.println("");
	}
}
