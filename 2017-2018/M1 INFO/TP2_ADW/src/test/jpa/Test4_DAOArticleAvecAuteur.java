package test.jpa;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import core.Article;
import core.Auteur;
import dao.DAOArticle;
import dao.DAOAuteur;
import dao.jpa.DAOArticleJPA;
import dao.jpa.DAOAuteurJPA;
import dao.jpa.DAOJPA;

public class Test4_DAOArticleAvecAuteur {

	private Auteur auteur;
	private Article article1;
	private DAOArticle dao;

	@Before
	public void init() {
		auteur = new Auteur("Victor HUGO", 3, Calendar.getInstance().getTime());
		article1 = new Article("OM-PSG", 123, 344, auteur);
		dao = DAOArticleJPA.getInstance();
		DAOJPA.viderBase();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArticle() {
		
		dao.save(article1); // L'auteur n'est pas enregistré...
	}
	
	@Test
	public void testAuteurAvecArticle() {
		DAOAuteur daoAuteur = DAOAuteurJPA.getInstance();
		
		daoAuteur.save(auteur);
		assertEquals(1,  daoAuteur.getNombreAuteurs());
		assertEquals(1,  dao.getNombreArticles());
		
		Auteur auteur2 = daoAuteur.get(auteur.getCode());
		assertEquals(auteur, auteur2);
		assertEquals(1, auteur2.size());
		assertTrue(auteur2.contains(article1));
	}
	
	@Test
	public void testRemoveArticle() {
		DAOAuteur daoAuteur = DAOAuteurJPA.getInstance();
		
		daoAuteur.save(auteur);
		
		dao.remove(article1);
		assertEquals(-1, article1.getCode());
		assertEquals(0, auteur.size());
		assertEquals(0, dao.getNombreArticles());
	}
	
}
