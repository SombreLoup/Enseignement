package test.jpa;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import core.Article;
import core.Auteur;
import core.Page;
import dao.DAOArticle;
import dao.DAOAuteur;
import dao.DAOPage;
import dao.jpa.DAOArticleJPA;
import dao.jpa.DAOAuteurJPA;
import dao.jpa.DAOJPA;
import dao.jpa.DAOPageJPA;

public class Test5_DAOPage {

	private	Auteur	auteur1;
	private Article article1;
	private Article article2;
	private DAOPage daoPage;
	private DAOArticle daoArticle;
	
	
	@Before
	public void init() {
		daoPage = DAOPageJPA.getInstance();
		daoArticle = DAOArticleJPA.getInstance();
		
		DAOJPA.viderBase();

		auteur1 = new Auteur("Victor HUGO", 3, Calendar.getInstance().getTime());
		article1 = new Article("OM-PSG", 234, 245, auteur1);
		article2 = new Article("FCM-OL", 123, 301, auteur1);
		
		DAOAuteurJPA.getInstance().save(auteur1);
	}
	

	@Test
	public void testSaveSimple() {
		Page page1 = new Page("SPORT", Calendar.getInstance().getTime(), false);
		
		page1.add(article1);
		page1.add(article2);
		
		daoPage.save(page1);
		
		assertEquals(1, daoPage.getNombrePages());
		assertEquals(2, daoArticle.getNombreArticles()); // toujours autant d'articles
		
		
		Page page2 = daoPage.get(page1.getCode());
		
		assertEquals(2, page2.size());
		assertTrue(page2.contains(article1));
		assertTrue(page2.contains(article2));
		
		daoPage.remove(page1);

		assertEquals(0, daoPage.getNombrePages());			
		assertEquals(2, daoArticle.getNombreArticles());
	}

}
