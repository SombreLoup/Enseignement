package dao.jpa;


import javax.persistence.Query;

import core.Article;
import core.Auteur;
import core.Page;
import dao.DAOAuteur;
import dao.DAOPage;

public class DAOPageJPA implements DAOPage {
	
	static private	DAOPageJPA	instance = null;
	
	public DAOPageJPA() {
	}
	
	static public	DAOPage getInstance() {
		if (instance == null)
			instance = new DAOPageJPA();
		
		return instance;
	}

	@Override
	public Page get(int code) {
		Page page = DAOJPA.getManager().find(Page.class, code);
		return page;
	}

	@Override
	public void save(Page page) {
		testerPage(page);
		
		if (estPersistant(page))
			return;
			
		DAOJPA.getManager().persist(page);
		DAOJPA.commit();		}

	@Override
	public void remove(Page page) {
		testerPage(page);

		if (!estPersistant(page))
			throw new IllegalArgumentException("La page n'est pas encore enregistrée");
		
		Page pageToRemove = DAOJPA.getManager().merge(page);
		DAOJPA.getManager().remove(pageToRemove);
		DAOJPA.commit();
		
		page.setCode(-1);
	}
	
	
	@Override
	public int getNombrePages() {
		Query query = DAOJPA.getManager().createQuery("select count(page.code) from Page page");
		Long count = (Long) query.getSingleResult();
		return count.intValue();
	}

	private void testerPage(Page page) {
		if (page==null)
			throw new IllegalArgumentException("Page null");
	}

	private boolean estPersistant(Page page) {
		return page.getCode()!=-1;
	}

}
