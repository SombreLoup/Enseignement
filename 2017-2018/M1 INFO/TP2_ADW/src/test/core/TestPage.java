package test.core;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import core.Article;
import core.Auteur;
import core.Page;

public class TestPage {

	@Test
	public void testPageSeule() {
		Page	page = new Page();
		
		assertEquals(-1, page.getCode());
		
		assertTrue(page.isEmpty());
		assertEquals(0, page.getTarif(), 0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPageMauvaisTheme() {
		new Page("",null,false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPageMauvaiseDate() {
		new Page("Sport",null,false);
	}
	

	@Test
	public void testPageAvecArticles() {
		Page	page = new Page("Sport", Calendar.getInstance().getTime(), true);
		Auteur	auteur = new Auteur("Hugo", 3, Calendar.getInstance().getTime());
		
		Article	a1 = new Article("OM-PSG", 150, 200, auteur);
		Article	a2 = new Article("FEDERER", 180, 300, auteur);
		Article	a3 = new Article("MANAUDOU", 250, 400, auteur);
		
		page.add(a1);
		page.add(a2);
		page.add(a3);
		
		assertEquals(3, page.size());
		assertEquals(a1.getTarif()+a2.getTarif()+a3.getTarif(), page.getTarif(), 0.001);
		assertTrue(page.getListeArticles().contains(a1));
		assertTrue(page.getListeArticles().contains(a2));
		assertTrue(page.getListeArticles().contains(a3));
	}

}
