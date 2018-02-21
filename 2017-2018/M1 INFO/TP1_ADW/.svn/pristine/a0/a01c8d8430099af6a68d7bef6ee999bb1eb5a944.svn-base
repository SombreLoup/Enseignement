package dao.jpa;

import javax.persistence.Query;

import core.Article;
import dao.DAOArticle;

public class DAOArticleJPA implements DAOArticle {
	static private	DAOArticleJPA	instance = null;
	
	public DAOArticleJPA() {
	}
	
	static public	DAOArticle getInstance() {
		if (instance == null)
			instance = new DAOArticleJPA();
		
		return instance;
	}

	@Override
	public Article get(int code) {
		Article article = DAOJPA.getManager().find(Article.class, code);
		return article;
	}

	@Override
	public void save(Article article) {
		testerArticle(article);
		testerAuteur(article); // Pas pour Test3, mais pour Test4
		
		if (estPersistant(article))
			return;
			
		DAOJPA.getManager().persist(article);
		DAOJPA.commit();	
	}

	@Override
	public void remove(Article article) {
		testerArticle(article);

		if (!estPersistant(article))
			throw new IllegalArgumentException("L'article n'est pas encore enregistré");
		
		Article articleToRemove = DAOJPA.getManager().merge(article);
		DAOJPA.getManager().remove(articleToRemove);
		DAOJPA.commit();
		
		article.setCode(-1);
		article.getAuteur().remove(article);
	}

	@Override
	public int getNombreArticles() {
		Query query = DAOJPA.getManager().createQuery("select count(article.code) from Article article");
		Long count = (Long) query.getSingleResult();
		return count.intValue();
	}
	
	private void testerArticle(Article article) {
		if (article==null)
			throw new IllegalArgumentException("Article null");
	}

	private boolean estPersistant(Article article) {
		return article.getCode()!=-1;
	}

	private void testerAuteur(Article article) {
		if (article.getAuteur()==null) 
			throw new IllegalArgumentException("L'auteur de l'article est null");
		if (article.getAuteur().getCode()==-1)
			throw new IllegalArgumentException("L'auteur de l'article n'est pas enregistré");
	}



}
