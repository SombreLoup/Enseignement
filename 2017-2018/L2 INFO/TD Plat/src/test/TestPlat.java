package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import plat.CategoriePlat;
import plat.Plat;

class TestPlat {

	@Test
	void testConstruction() {
		// C'est le cas le plus simple
		
		CategoriePlat dessert = new CategoriePlat("Dessert");
		
		Plat baba = new Plat("Baba au rhum", dessert);
		
		assertEquals(dessert, baba.getCategorie());
		assertTrue(dessert.contains(baba));
	}
	
	@Test
	void testChangementDeCategorie() {
		// Beaucoup plus subtil

		CategoriePlat dessert = new CategoriePlat("Dessert");
		
		Plat baba = new Plat("Baba au rhum", dessert);
		
		CategoriePlat entremet = new CategoriePlat("Entremets");
		
		entremet.add(baba);
		
		assertEquals(entremet, baba.getCategorie());
		assertTrue(entremet.contains(baba));
		assertFalse(dessert.contains(baba));
		
		
	}
	
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testSuppressionDeLaCategorieDunPlat() {
		// Ca doit planter. La fonction remove de CategoriePlat dans lancer une exception
		// si on fait n'importe quoi
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("blabla");
		
		CategoriePlat dessert = new CategoriePlat("Dessert");
		
		Plat baba = new Plat("Baba au rhum", dessert);
		
		dessert.remove(baba);		
	}

}
