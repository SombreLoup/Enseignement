package application;

import java.text.ParseException;

import Activite.ActiviteException;
import Activite.Promenade;
import Activite.Randonnee;
import Activite.TempsLibre;
import planning.Journee;

public class Application {

	public static void main(String[] args) throws ParseException, ActiviteException {
		Journee lundi = new Journee("11/04/2018");
		
		lundi.ajouterActivite(new TempsLibre(2));
		lundi.ajouterActivite(new Promenade(7, Randonnee.FACILE, "Promenade dans Central Park"));
		lundi.ajouterActivite(new TempsLibre(2));
		lundi.ajouterActivite(new Randonnee(4, Randonnee.MOYENNE));
		System.out.println(lundi);
		System.out.println("Validite : "+lundi.estValide());
		System.out.println("Reste à planifier : "+lundi.dureeInactivite());
	}

}
