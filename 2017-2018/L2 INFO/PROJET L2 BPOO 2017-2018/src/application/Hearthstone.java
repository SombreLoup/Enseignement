package application;

import java.util.ArrayList;

import jeu.HearthstoneException;
import jeu.Heros;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.capacites.CapaciteAttaqueCible;
import jeu.capacites.CapaciteAttaqueHeros;
import jeu.capacites.CapaciteAttaqueTousLesServiteurs;
import jeu.capacites.CapaciteCharge;
import jeu.capacites.CapaciteEffetPermanent;
import jeu.capacites.CapaciteImageMirroir;
import jeu.capacites.CapaciteInvocationChiens;
import jeu.capacites.CapaciteInvocationServiteur;
import jeu.capacites.CapaciteMarqueChasseur;
import jeu.capacites.CapacitePioche;
import jeu.capacites.CapaciteProvocation;
import jeu.cartes.Serviteur;
import jeu.cartes.Sort;
import jeu.cor.InterfaceCarteEnJeu;
import jeu.cor.InterfaceCarteEnMain;
import jeu.cor.InterfaceConsole;
import jeu.cor.InterfaceJaina;
import jeu.cor.InterfaceRexxar;
import jeu.cor.InterfaceServiteur;
import jeu.cor.InterfaceSortAttaqueCible;
import jeu.cor.InterfaceSortAttaqueHeros;
import jeu.cor.InterfaceSortCharge;
import tools.Console;

public class Hearthstone {

	public static Console	out = new Console();
	
	public static InterfaceConsole ihm = new InterfaceConsole();

	public static void main(String[] args) throws HearthstoneException {
		initInterface();
				
		//initJoueurAvecConsole(out);
		
		Joueur	joueur1 = new Joueur("WorgenGeant", Heros.getHeros("Jaina"));
		ArrayList<ICarte> deckJaina = getDeckNeutre(joueur1);
		deckJaina.addAll(getDeckJaina(joueur1));
		joueur1.setDeck(deckJaina);
		
		
		Joueur	joueur2 = new Joueur("Ali Baba", Heros.getHeros("Rex"));
		ArrayList<ICarte> deckRexxar = getDeckNeutre(joueur2);
		deckRexxar.addAll(getDeckRexxar(joueur2));
		joueur2.setDeck(deckRexxar);
		
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
		ihm = new InterfaceSortAttaqueHeros(ihm);
		ihm = new InterfaceSortAttaqueCible(ihm);
		
	}



	private static ArrayList<ICarte> getDeckNeutre(IJoueur joueur) {
		ArrayList<ICarte>	deck = new ArrayList<ICarte>();
		
		ICarte c;
		
		
		c = new Serviteur(joueur, "Chasse-marée murloc", 2, 2, 1, new CapaciteInvocationServiteur("Cri de guerre",	 "Invoque un serviteur 1/1", new Serviteur(joueur, "Serviteur de murloc", 0, 1, 1, null)));	
		deck.add(c);
		c = new Sort(joueur, "Charge", 1, new CapaciteCharge());
		deck.add(c);
		c = new Sort(joueur, "Attaque mentale", 2, new CapaciteAttaqueHeros("Attaque", "Inflige 5 points d'attaque au héros", 5));
		deck.add(c);
		c = new Serviteur(joueur, "Champion de Hurlevent", 7, 6, 6, new CapaciteEffetPermanent("Bonus de Hurlevent", "Vos autres serviteurs ont +1/+1", 1, 1)); 
		deck.add(c);
		c = new Serviteur(joueur, "Chef de raid", 3,2,2, new CapaciteEffetPermanent("Bonus du chef de raid", "Vos autres serviteurs ont +1 d'attaque", 1, 0));
		deck.add(c);
		c = new Serviteur(joueur, "Garde de Baie-du-butin", 5,5,4, new CapaciteProvocation());
		deck.add(c);
		c = new Serviteur(joueur, "La missilière téméraire", 6,5,2, new CapaciteCharge());
		deck.add(c);
		c = new Serviteur(joueur, "L'ogre-magi", 4,4,4, new CapaciteProvocation());
		deck.add(c);
		c = new Serviteur(joueur, "Archimage", 6,4,7, new CapaciteProvocation());
		deck.add(c);
		c = new Serviteur(joueur, "Gnôme lépreux", 1,1,1, new CapaciteAttaqueHeros("Attaque du lépreux", "Inflige 2 pts de dégat au héros",2));
		deck.add(c);
		c = new Serviteur(joueur, "Golem des moissons", 3,2,3, new CapaciteInvocationServiteur("Golémisation", "Invoque un Golem Endomagé 2/1",new Serviteur(joueur, "Golem endomagé", 0, 2, 1, null)));
		deck.add(c);
		
		return deck;
		
		
	}

/*	
	Rexxar
	------
	1. Serviteur Busard affamé (pioche une carte)
	2. Sort 'Marque du chasseur' (baisse à 1 pt de vie le serviteur ciblé)
	3. Sort 'tir des arcanes' inflige 2pts de dégats à qui tu veux
	4. Sort 'Lachez les chiens' (les chiens 1/1 autant que de serviteur adverse et ils ont charge)
	5. Sort 'ordre de tuer' (inflige 3 pts de dégats)
*/	
	
	private static ArrayList<ICarte> getDeckRexxar(IJoueur joueur) {
		ArrayList<ICarte>	deck = new ArrayList<ICarte>();
		
		ICarte c;
	
		c = new Serviteur(joueur, "Busard affamé", 5, 3, 2, new CapacitePioche(1));
		deck.add(c);
		c = new Sort (joueur, "Marque du chasseur", 1, new CapaciteMarqueChasseur("Marque du chasseur", "Abaisse à 1 les points de vie du serviteur ciblé"));	
		deck.add(c);
		c = new Sort(joueur, "Tir des arcanes", 1, new CapaciteAttaqueCible("Tir des arcanes", "Inflige 2 points de dégâts au personnage", 2)); 
		deck.add(c);
		c = new Sort(joueur, "Lachez les chiens", 3, new CapaciteInvocationChiens()); 
		deck.add(c);
		c = new Sort (joueur, "Ordre de tuer", 3, new CapaciteAttaqueCible("Ordre de tuer", "Inflige 3 points de dégâts au personnage", 10));	
		deck.add(c);
		
		return deck;
		
		
	}

	
	
	
	private static ArrayList<ICarte> getDeckJaina(IJoueur joueur) {
		ArrayList<ICarte>	deck = new ArrayList<ICarte>();
		
		ICarte c;
	/*	
		1. Sort 'Choc de flamme' : inflige 4 pts de dégats à tous les serviteurs adverses
		2. Sort 'Eclair de givre' : inflige 3 pts de dégats au héros ou à serviteur (+gele)
		3. Sort 'intelligence des arcanes' : pioche 2 cartes
		4. Sort 'image mirroir' : invoke 2 serviteurs 0/2 avec provocation
		5. Sort 'Explosion pyrothechnique' : inflige 10 pts de dégats au héros ou à un serviteur
*/
		c = new Sort(joueur, "Choc de flamme", 7, new CapaciteAttaqueTousLesServiteurs("Attaque massive", "Inglige 4 points de dégâts à tous les serviteurs adverses", 4));	
		deck.add(c);
		c = new Sort (joueur, "Eclair de givre", 2, new CapaciteAttaqueCible("Attaque du givre", "Inflige 3 points de dégâts au personnage", 3));	
		deck.add(c);
		c = new Sort(joueur, "Intelligence des arcanes", 2, new CapacitePioche(2)); 
		deck.add(c);
		c = new Sort(joueur, "Image mirroir", 1, new CapaciteImageMirroir()); 
		deck.add(c);
		c = new Sort (joueur, "Explosion pyrotechnique", 10, new CapaciteAttaqueCible("Explosion pyrotechnique", "Inflige 10 points de dégâts au personnage", 10));	
		deck.add(c);
		
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
