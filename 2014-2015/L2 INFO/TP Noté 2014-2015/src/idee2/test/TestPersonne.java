package idee2.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import idee2.rdv.Creneau;
import idee2.rdv.Personne;
import idee2.rdv.Reunion;
import idee2.rdv.ReunionException;

import org.junit.Test;

public class TestPersonne {
	
	@Test
	public void testPremièreReunion() throws ReunionException, ParseException {
		Personne personne = new Personne("Yann", "Informatique");
		assertEquals(0,personne.getAgenda().size());
		
		Creneau	creneau = new Creneau("23/11/2015", "08:00", "10:00");
		assertTrue(personne.estDisponible(creneau));
		
		Reunion	reunion = new Reunion("Réunion d'information", creneau);
		try {
			reunion.inviter(personne);
			assertEquals(1, personne.getAgenda().size());
			assertFalse(personne.estDisponible(creneau));
			assertEquals(1,reunion.getParticipants().size());
		} catch (ReunionException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPlusieursReunions()  throws ReunionException, ParseException {
		Personne personne = new Personne("Yann", "Informatique");
		assertEquals(0,personne.getAgenda().size());
		
		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		assertTrue(personne.estDisponible(creneau1));
		
		Creneau	creneau2 = new Creneau("23/11/2015", "10:00", "12:00");
		assertTrue(personne.estDisponible(creneau2));
		
		Creneau	creneau3 = new Creneau("23/11/2015", "12:00", "14:00");
		assertTrue(personne.estDisponible(creneau3));
		
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);		
		Reunion	reunion2 = new Reunion("Commission", creneau2);		
		Reunion	reunion3 = new Reunion("Bouffe", creneau3);	

		assertTrue(personne.estDisponible(creneau1));
		assertTrue(personne.estDisponible(creneau2));
		assertTrue(personne.estDisponible(creneau3));
		assertEquals(0, personne.getAgenda().size());

		
		try {
			reunion1.inviter(personne);
			assertEquals(1, personne.getAgenda().size());
			assertFalse(personne.estDisponible(creneau1));
			assertTrue(personne.estDisponible(creneau2));
			assertTrue(personne.estDisponible(creneau3));
			
			reunion3.inviter(personne);
			assertEquals(2, personne.getAgenda().size());
			assertFalse(personne.estDisponible(creneau1));
			assertTrue(personne.estDisponible(creneau2));
			assertFalse(personne.estDisponible(creneau3));
			
			reunion2.inviter(personne);
			assertFalse(personne.estDisponible(creneau1));
			assertFalse(personne.estDisponible(creneau2));
			assertFalse(personne.estDisponible(creneau3));
		} catch (ReunionException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test(expected=ReunionException.class)
	public void testIndisponible()  throws ReunionException, ParseException {
		Personne personne = new Personne("Yann", "Informatique");
		assertEquals(0,personne.getAgenda().size());
		
		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		assertTrue(personne.estDisponible(creneau1));
		
		Creneau	creneau2 = new Creneau("23/11/2015", "10:00", "12:00");
		assertTrue(personne.estDisponible(creneau2));
		
		Creneau	creneau3 = new Creneau("23/11/2015", "11:00", "14:00");
		assertTrue(personne.estDisponible(creneau3));
		
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);		
		Reunion	reunion2 = new Reunion("Commission", creneau2);		
		Reunion	reunion3 = new Reunion("Bouffe", creneau3);
		
		try {
			reunion1.inviter(personne);	
			reunion2.inviter(personne);
		} catch (ReunionException e) {
			e.printStackTrace();
			fail();
		}

		reunion3.inviter(personne);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testAjouterAvecNull() throws ParseException, ReunionException {
		Personne personne = new Personne("Yann", "Informatique");

		personne.ajouter(null);
	}
	
	@Test
	public void testAjouterAvecException() throws ParseException {
		Personne personne = new Personne("Yann", "Informatique");
		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);		

		try {
			reunion1.inviter(personne);
		} catch (ReunionException e) {
			e.printStackTrace();
			fail();
		}
		
		Reunion	reunion2 = new Reunion("Conseil", creneau1);		
		
		try {
			reunion2.inviter(personne);
		} catch (ReunionException e) {
			assertEquals(1, e.getParticipantsIndisponibles().size());
			assertEquals(personne, e.getParticipantsIndisponibles().get(0));
		}
		
	}

}
