package application;

import jeu.ExceptionHearthstone;
import jeu.Heros;
import tools.Console;

public class Hearthstone {
	public static void main(String[] args) throws ExceptionHearthstone {
		Console	out = new Console();
		
		out.println("Voici la liste des héros :");
		out.println("--------------------------");
		Heros.afficherListe();
		out.println("");
		out.println("Lequel voulez-vous (un bout du nom suffira) ?");
		String	nom = out.readLine();
		out.println("Vous avez choisi : "+Heros.getHeros(nom));
	}
}
