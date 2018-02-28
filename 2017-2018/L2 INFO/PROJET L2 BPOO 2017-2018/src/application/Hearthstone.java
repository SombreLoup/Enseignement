package application;

import java.util.ArrayList;

import jeu.HearthstoneException;
import jeu.Heros;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.capacites.CapaciteAttaqueHeros;
import jeu.capacites.CapaciteCharge;
import jeu.capacites.CapaciteInvocationServiteur;
import jeu.cartes.Serviteur;
import jeu.cartes.Sort;
import jeu.cor.InterfaceCarteEnJeu;
import jeu.cor.InterfaceCarteEnMain;
import jeu.cor.InterfaceConsole;
import jeu.cor.InterfaceJaina;
import jeu.cor.InterfaceRexxar;
import jeu.cor.InterfaceServiteur;
import jeu.cor.InterfaceSortAttaque;
import jeu.cor.InterfaceSortCharge;
import tools.Console;

public class Hearthstone {

	public static Console	out = new Console();
	
	public static InterfaceConsole ihm = new InterfaceConsole();

	public static void main(String[] args) throws HearthstoneException {
		initInterface();
				
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
						ihm.interagir("Jouer une carte en main");
						break;
					case 3:
						carte = (ICarte) ihm.interagir("Jouer une carte en jeu");
						ihm.interagir(carte);
						break;
						
					case 4:
						ihm.interagir(board.getJoueurCourant().getHeros());
						break;
						
				}
			} catch (HearthstoneException e) {
				System.err.println(e.getMessage());
			}		
		}
	}


	
	private static void initInterface() {
		ihm = new InterfaceRexxar(ihm);
		ihm = new InterfaceJaina(ihm);
		ihm = new InterfaceServiteur(ihm);
		ihm = new InterfaceCarteEnJeu(ihm);
		ihm = new InterfaceCarteEnMain(ihm);
		ihm = new InterfaceSortCharge(ihm);
		ihm = new InterfaceSortAttaque(ihm);
		
	}



	private static ArrayList<ICarte> getDeckNeutre(IJoueur joueur) {
		ArrayList<ICarte>	deck = new ArrayList<ICarte>();
		
		ICarte c;
		
/*
 * 1. 
2. Champion de Hurlevent (effet permanent +1/+1 aux autres serviteurs)
3. Chef de raid (effet permanent +1 d'attaque aux autres serviteurs)
4. Le clerc du soleil brisé (cri de guerre =  +1/+1 à un serviteur allié choisi)
5. Le garde de Baie-du-buton (provocation)
6. Missilière téméraire (Charge)
7. L'orgre magi (provocation)
8. Archimage (provocation)
9. Gnome lépreux (râle d'agonie --> 2 pts dégats au héros adverse)
10. Golem des moissons (râle d'agonie --> invoque un 'golem endomagé' 2/1)
11. Sort Charge (pour tout le monde)
12. Sort d'attaque 2 pts dégats		
 */
		
		
		c = new Serviteur(joueur, "Chasse-marée murloc", 2, 2, 1, new CapaciteInvocationServiteur("Cri de guerre",	 "Invoque un serviteur 1/1", new Serviteur(joueur, "Serviteur de murloc", 0, 1, 1, null)));	deck.add(c);
		c = new Sort(joueur, "Charge", 1, new CapaciteCharge());	deck.add(c);
		c = new Sort(joueur, "Attaque mentale", 2, new CapaciteAttaqueHeros("Attaque", "Inflige 5 points d'attaque au héros", 5)); deck.add(c);
		
		
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
