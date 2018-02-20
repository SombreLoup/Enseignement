package application;

import jeu.ExceptionHearthstone;
import jeu.Heros;
import jeu.Joueur;
import tools.Console;

public class Hearthstone {
	public static void main(String[] args) throws ExceptionHearthstone {
		Console	out = new Console();
				
		//initJoueurAvecConsole(out);
		
		Joueur	joueur1 = new Joueur("WorgenGeant", Heros.getHeros("Jaina"));
		out.println(""+joueur1);
	}

	@SuppressWarnings("unused")
	private static void initJoueurAvecConsole(Console out) throws ExceptionHearthstone {
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
