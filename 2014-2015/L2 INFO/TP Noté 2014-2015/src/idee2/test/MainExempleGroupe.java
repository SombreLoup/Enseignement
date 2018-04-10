package idee2.test;

import java.text.ParseException;

import idee2.rdv.Creneau;
import idee2.rdv.GroupeTravail;
import idee2.rdv.Participant;
import idee2.rdv.Personne;
import idee2.rdv.Reunion;
import idee2.rdv.ReunionException;

public class MainExempleGroupe {

	public static void main(String[] args) throws ParseException, ReunionException {
		GroupeTravail	conseil = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		Participant		lui = new Personne("Robin", "Informatique");
		
		conseil.add(moi);
		conseil.add(toi);
		conseil.add(lui);

		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);
		
		reunion1.inviter(lui);
		reunion1.inviter(moi);
		
		
		Creneau	creneau2 = new Creneau("23/11/2015", "9:00", "12:00");
		Reunion	reunion2 = new Reunion("Conseil exceptionnel", creneau2);			
		
		try {
			reunion2.inviter(conseil);
			System.out.println("Réunion confirmée");
		} catch (ReunionException e) {
			if (e.getParticipantsIndisponibles().size()>=2)
				System.out.println("Conseil annulé");
			else
				System.out.println("Réunion confirmée");
		}
		
	}
}
