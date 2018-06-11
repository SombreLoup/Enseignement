package app;

import sport.Championat;
import sport.Equipe;
import sport.Participation;
import sport.SportException;
import sport.Sportif;

public class ApplicationSport {
	public static void main(String[] args) {
		Championat	mondial1998 = new Championat("Coupe du monde de foot", false);
		Championat	euro2000 = new Championat("Coupe d'Europe de foot", false);
		Sportif		zidane = new Sportif("Zidane", "23/06/1972");
		Sportif		deschamps = new Sportif("Deschamps", "15/10/1968");
		Equipe		equipeDeFrance = new Equipe("Equipe de France");
		equipeDeFrance.ajouterMembre(zidane);
		equipeDeFrance.ajouterMembre(deschamps);
			
		
		try {
			System.out.println(equipeDeFrance);
			
			Participation	participationMondial1998 = new Participation(mondial1998, equipeDeFrance, 1, 1998);
			Participation	participationEuro2000 = new Participation(euro2000, equipeDeFrance, 1, 2000);
			
			System.out.println(equipeDeFrance.getPalmaresString());
			
		} catch (SportException e) {
			e.printStackTrace();
		}
		
	}
}
