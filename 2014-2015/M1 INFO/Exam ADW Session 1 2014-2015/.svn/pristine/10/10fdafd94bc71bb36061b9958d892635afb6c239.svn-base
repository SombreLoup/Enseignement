package test.core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Celibataire;
import core.Rencontre;

public class TestCore {

	@Test
	public void testAjoutParticipant() {
		Celibataire	albert	= new Celibataire("Albert", null, "57000", true);
		Celibataire	marie	= new Celibataire("Marie", null, "54000", false);
		
		Rencontre	rancart = new Rencontre("Premier RDV", null);

		assertEquals(0,rancart.getParticipants().size());
		assertEquals(0,albert.getRencontres().size());
		assertEquals(0,marie.getRencontres().size());

		rancart.add(marie);
		rancart.add(albert);
		
		assertEquals(2,rancart.getParticipants().size());
		assertEquals(1,albert.getRencontres().size());
		assertEquals(1,marie.getRencontres().size());
		assertTrue(rancart.getParticipants().contains(marie));
		assertTrue(rancart.getParticipants().contains(albert));
	}	
	
	
	@Test
	public void testAjoutRencontre() {
		Celibataire	albert	= new Celibataire("Albert", null, "57000", true);
		Celibataire	marie	= new Celibataire("Marie", null, "54000", false);
		
		Rencontre	rancart = new Rencontre("Premier RDV", null);

		assertEquals(0,rancart.getParticipants().size());
		assertEquals(0,albert.getRencontres().size());
		assertEquals(0,marie.getRencontres().size());

		albert.add(rancart);
		marie.add(rancart);
		
		assertEquals(2,rancart.getParticipants().size());
		assertEquals(1,albert.getRencontres().size());
		assertEquals(1,marie.getRencontres().size());
		assertTrue(rancart.getParticipants().contains(marie));
		assertTrue(rancart.getParticipants().contains(albert));
	}

}
