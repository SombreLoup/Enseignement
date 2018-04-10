package idee2.test;

import java.text.ParseException;

import idee2.rdv.Creneau;

public class MainExempleCreneau {
	public static void main(String[] args) {
		try {
			// creation d'un creneau avec uniquement une date
			Creneau c1 = new Creneau("23/12/2014");
			System.out.println("c1="+c1);
			
			// modificqtion des horaires
			c1.setHeureDebut("8:30");
			c1.setHeureFin("10:00");
			System.out.println("c1="+c1);
			
			// creation d'un creneau avec toutes les informations
			Creneau c2 = new Creneau("23/12/2014", "9:00", "10:30");
			System.out.println("c2="+c2);

			System.out.println("Est que c1 chevauche c2 (normalement oui) ? "+c1.chevauche(c2));
			System.out.println("Est que c2 chevauche c1 (normalement oui aussi) ? "+c2.chevauche(c1));

			
			// creation d'un creneau qui inclus dans c2
			Creneau c3 = new Creneau("23/12/2014");
			c3.setHeureDebut("07:00");
			c3.setHeureFin("13:00");
			System.out.println("c3="+c3);

			System.out.println("Est que c2 chevauche c3 (normalement oui) ? "+c2.chevauche(c3));
			System.out.println("Est que c3 chevauche c2 (normalement oui aussi) ? "+c3.chevauche(c2));

	
			// creation d'un creneau disjoint de c1 (pas la meme date)
			Creneau c4 = new Creneau("22/12/2014", "8:00", "10:00");
			System.out.println("c4="+c4);
			
			System.out.println("Est que c4 chevauche c1 (normalement non) ? "+c4.chevauche(c1));
			System.out.println("Est que c1 chevauche c4 (normalement non aussi) ? "+c1.chevauche(c4));

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
