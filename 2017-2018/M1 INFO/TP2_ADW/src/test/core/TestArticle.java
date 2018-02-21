package test.core;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import core.Article;
import core.Auteur;

public class TestArticle {

	@Test
	public void testArticleSeul() {
		Article	article = new Article();	
		
		assertEquals(-1, article.getCode());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testArticleMauvaisTitre() {
		Auteur	auteur = new Auteur("Hugo", 3, Date.valueOf("24/12/2012"));
		new Article("",0,0, auteur);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArticleMauvaisNombreMotsMin() {
		Auteur	auteur = new Auteur("Hugo", 3, Date.valueOf("24/12/2012"));
		new Article("Titre",0,0, auteur);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArticleMauvaisNombreMotsMax() {
		Auteur	auteur = new Auteur("Hugo", 3, Date.valueOf("24/12/2012"));
		new Article("Titre",10000,0, auteur);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArticleMauvaisTarifMin() {
		Auteur	auteur = new Auteur("Hugo", 3, Date.valueOf("24/12/2012"));
		new Article("",200,1, auteur);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testArticleMauvaisTarifMax() {
		Auteur	auteur = new Auteur("Hugo", 3, Date.valueOf("24/12/2012"));
		new Article("",200,10000, auteur);
	}

}
