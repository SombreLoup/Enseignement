package test.jpa;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import core.Article;
import core.Auteur;
import dao.DAOArticle;
import dao.jpa.DAOArticleJPA;
import dao.jpa.DAOJPA;

public class Test3_DAOArticle {

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
	
	@Test
	public void testArticle() {
		
		assertEquals(0, dao.getNombreArticles());
		dao.save(article1);
		assertEquals(1, dao.getNombreArticles());
		assertNotEquals(-1, article1.getCode());
		
		Article article2 = dao.get(article1.getCode());
		assertEquals(article1, article2);
		
		dao.remove(article2);
		assertEquals(0, dao.getNombreArticles());
		assertEquals(-1, article2.getCode());
	}
	
	
	@Test
	public void testSaveException() {
		
		dao.save(article1);
		assertEquals(1,dao.getNombreArticles());

		// Ajouter le même auteur ne change pas la base
		dao.save(article1);
		assertEquals(1,dao.getNombreArticles());
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveException() {
		
		dao.save(article1);
		assertEquals(1,dao.getNombreArticles());

		// Supprimer 2 fois le même auteur lance une exception
		dao.remove(article1);
		assertEquals(0,dao.getNombreArticles());
		
		dao.remove(article1);
	}

}
