package idee2.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import idee2.rdv.Creneau;
import idee2.rdv.GroupeTravail;
import idee2.rdv.Participant;
import idee2.rdv.Personne;
import idee2.rdv.Reunion;
import idee2.rdv.ReunionException;

import org.junit.Test;

public class TestGroupeTravail {

	@Test
	public void testAjoutUnParticipantNominal() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		
		try {
			grp.add(moi);
			assertEquals(1,grp.getNombrePersonnes());
			assertEquals(moi,grp.get(0));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testAjoutPlusieursParticipantsNominal() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		Participant		lui = new Personne("Robin", "Informatique");

		
		try {
			grp.add(moi);
			grp.add(toi);
			grp.add(lui);
			assertEquals(3,grp.getNombrePersonnes());
			assertTrue(grp.getMembres().contains(moi));
			assertTrue(grp.getMembres().contains(toi));
			assertTrue(grp.getMembres().contains(lui));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testAjoutParticipantNull() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		
		grp.add(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAjoutParticipantDejaPrésent() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		
		grp.add(moi);
		grp.add(moi);
	}
	
	@Test
	public void testSupprimerParticipantPrésent() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		
		grp.add(moi);
		grp.remove(moi);
		assertEquals(0,grp.getNombrePersonnes());
	}
	
	@Test
	public void testSupprimerParticipantInexistant() {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		
		grp.add(moi);
		grp.remove(toi);
		assertEquals(1,grp.getNombrePersonnes());
	}	
	
	@Test
	public void testAjouterRéunionTousDisponibles() throws ParseException {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		Participant		lui = new Personne("Robin", "Informatique");
		
		grp.add(moi);
		grp.add(toi);
		grp.add(lui);
		assertEquals(3,grp.getNombrePersonnes());
		assertTrue(grp.getMembres().contains(moi));
		assertTrue(grp.getMembres().contains(toi));
		assertTrue(grp.getMembres().contains(lui));

		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);		
		try {
			assertTrue(moi.estDisponible(creneau1));
			assertTrue(toi.estDisponible(creneau1));
			assertTrue(lui.estDisponible(creneau1));

			grp.ajouter(reunion1);
			
			assertFalse(moi.estDisponible(creneau1));
			assertFalse(toi.estDisponible(creneau1));
			assertFalse(lui.estDisponible(creneau1));
			
			
		} catch (ReunionException e) {
			e.printStackTrace();
			fail();
		}
		
	}	
	
	@Test
	public void testAjouterRéunionTousPasDisponibles() throws ParseException {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		Participant		lui = new Personne("Robin", "Informatique");
		
		grp.add(moi);
		grp.add(toi);
		grp.add(lui);
		assertEquals(3,grp.getNombrePersonnes());
		assertTrue(grp.getMembres().contains(moi));
		assertTrue(grp.getMembres().contains(toi));
		assertTrue(grp.getMembres().contains(lui));

		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);	
		Creneau	creneau2 = new Creneau("23/11/2015", "9:00", "12:00");
		Reunion	reunion2 = new Reunion("Commission", creneau2);		

		try {
			assertTrue(moi.estDisponible(creneau1));
			assertTrue(toi.estDisponible(creneau1));
			assertTrue(lui.estDisponible(creneau1));

			toi.ajouter(reunion2);
			grp.ajouter(reunion1);
			
		} catch (ReunionException e) {
			assertFalse(moi.estDisponible(creneau1));
			assertFalse(lui.estDisponible(creneau1));
			assertFalse(toi.estDisponible(creneau2));
			
			assertEquals(1, e.getParticipantsIndisponibles().size());
			assertTrue(e.getParticipantsIndisponibles().contains(toi));
		}
		
	}	
	@Test
	public void testAjouterRéunionAucunDisponible() throws ParseException {
		GroupeTravail	grp = new GroupeTravail("Conseil d'administration");
		Participant		moi = new Personne("Yann", "Informatique");
		Participant		toi = new Personne("Isabelle", "Informatique");
		Participant		lui = new Personne("Robin", "Informatique");
		
		grp.add(moi);
		grp.add(toi);
		grp.add(lui);
		assertEquals(3,grp.getNombrePersonnes());
		assertTrue(grp.getMembres().contains(moi));
		assertTrue(grp.getMembres().contains(toi));
		assertTrue(grp.getMembres().contains(lui));

		Creneau	creneau1 = new Creneau("23/11/2015", "08:00", "10:00");
		Reunion	reunion1 = new Reunion("Réunion d'information", creneau1);	
		Creneau	creneau2 = new Creneau("23/11/2015", "9:00", "12:00");
		Reunion	reunion2 = new Reunion("Commission", creneau2);		

		try {
			assertTrue(moi.estDisponible(creneau1));
			assertTrue(toi.estDisponible(creneau1));
			assertTrue(lui.estDisponible(creneau1));

			moi.ajouter(reunion2);
			toi.ajouter(reunion2);
			lui.ajouter(reunion2);
			grp.ajouter(reunion1);
			
		} catch (ReunionException e) {
			assertFalse(moi.estDisponible(creneau2));
			assertFalse(lui.estDisponible(creneau2));
			assertFalse(toi.estDisponible(creneau2));
			
			assertEquals(3, e.getParticipantsIndisponibles().size());
			assertTrue(e.getParticipantsIndisponibles().contains(moi));
			assertTrue(e.getParticipantsIndisponibles().contains(toi));
			assertTrue(e.getParticipantsIndisponibles().contains(lui));
		}
		
	}
	
	

}
