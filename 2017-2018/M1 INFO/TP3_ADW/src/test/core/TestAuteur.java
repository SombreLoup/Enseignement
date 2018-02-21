package test.core;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import core.Article;
import core.Auteur;

public class TestAuteur {

	@Test
	public void testAuteurSeul() {
		Auteur	auteur = new Auteur("Auteur", 3, Calendar.getInstance().getTime());
		
		assertEquals(-1, auteur.getCode());
		assertTrue(auteur.isEmpty());
		
		Article article = new Article("OM-PSG", 200, 600, auteur);
		assertEquals(1,auteur.size());
		assertTrue(auteur.contains(article));		
	}

	@Test
	public void testChangementAuteur() {
		Auteur	auteur1 = new Auteur("Auteur1", 3, Calendar.getInstance().getTime());
		Article article = new Article("OM-PSG", 200, 600, auteur1);
		
		Auteur	auteur2 = new Auteur("Auteur2",2,Calendar.getInstance().getTime());
		article.setAuteur(auteur2);
		
		assertTrue(auteur1.isEmpty());
		assertEquals(1,auteur2.size());
		assertTrue(auteur2.contains(article));
	}
}
