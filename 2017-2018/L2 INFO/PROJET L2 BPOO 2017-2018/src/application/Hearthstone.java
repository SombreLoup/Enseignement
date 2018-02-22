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
		// out.println(""+joueur1);
		
		Joueur	joueur2 = new Joueur("Ali Baba", Heros.getHeros("Rex"));
		joueur2.setDeck(getDeckNeutre(joueur2));
		// out.println(""+joueur2);
		
		IPlateau board = Plateau.getInstance();
		board.ajouterJoueur(joueur1);
		board.ajouterJoueur(joueur2);
		board.demarrerPartie();
		
		while (board.estDemarree()) {
			System.out.println(""+board);
			out.println("\n");
			out.println("Que veux-tu faire ?");
			out.println("1. Finir le tour");
			out.println("2. Jouer une carte de ta main");
			out.println("3. Utiliser une carte en jeu");
			out.println("4. Utiliser le pouvoir du héros");
			out.print("\n-->");
			int choix = out.readInt();
			ICarte carte;
			
			try {
				switch(choix) {
					case 1:
						board.getJoueurCourant().finirTour();
						break;

					
					case 2:
						out.println("Laquelle ? (donne un bout de son nom);");
						out.print("-->");
						String nomCarteMain = out.readLine();
						carte = board.getJoueurCourant().getCarteEnMain(nomCarteMain);
						if (carte==null) {
							throw new HearthstoneException("Cette carte n'est pas dans ta main...");
						}
						else {
							board.getJoueurCourant().jouerCarte(carte);
							break;
						}
					case 3:
						out.println("Laquelle ? (donne un bout de son nom);");
						out.print("-->");
						String nomCarteJeu = out.readLine();
						carte = board.getJoueurCourant().getCarteEnJeu(nomCarteJeu);
						if (carte==null) {
							throw new HearthstoneException("Cette carte n'est pas en jeu...");
						}
						else {
							out.println("Qu'est-ce que tu vise ?");
							out.println("1. Le héros");
							out.println("2. Une autre carte");
							out.print("-->");
							int choixCible = out.readInt();
							if (choixCible==1) {
								board.getJoueurCourant().utiliserCarte(carte, board.getAdversaire(board.getJoueurCourant()));
							}
							else {
								out.println("Quelle carte vises-tu ? (donne un bout de son nom)");
								out.print("-->");
								String nomCarteCible = out.readLine();
								ICarte carteCible = board.getAdversaire(board.getJoueurCourant()).getCarteEnJeu(nomCarteCible);
								if (carteCible==null) {
									throw new HearthstoneException("Cette carte n'est pas en jeu...");
								}
								else {
									board.getJoueurCourant().utiliserCarte(carte, carteCible);
								}
							}
						}
						break;
						
					case 4:
						if (board.getJoueurCourant().getHeros().getNom().equals("Rexxar")) {
							board.getJoueurCourant().utiliserPouvoir(board.getAdversaire(board.getJoueurCourant()));
							break;
						}
						
						out.println("Qu'est-ce que tu vises avec ton pouvoir ?");
						out.println("1. Le héros");
						out.println("2. Une autre carte");
						out.print("-->");
						int choixCible = out.readInt();
						if (choixCible==1) {
							board.getJoueurCourant().utiliserPouvoir(board.getAdversaire(board.getJoueurCourant()));
						}
						else {
							out.println("Quelle carte vises-tu ? (donne un bout de son nom)");
							out.print("-->");
							String nomCarteCible = out.readLine();
							ICarte carteCible = board.getAdversaire(board.getJoueurCourant()).getCarteEnJeu(nomCarteCible);
							if (carteCible==null) {
								throw new HearthstoneException("Cette carte n'est pas en jeu...");
							}
							else {
								board.getJoueurCourant().utiliserPouvoir(carteCible);
							}
						}
						break;
						
				}
			} catch (HearthstoneException e) {
				System.err.println(e.getMessage());
			}		
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
