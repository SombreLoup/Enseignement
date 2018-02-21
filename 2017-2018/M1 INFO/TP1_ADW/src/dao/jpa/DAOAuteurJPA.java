package dao.jpa;


import java.util.List;

import javax.persistence.Query;

import core.Auteur;
import dao.DAOAuteur;

public class DAOAuteurJPA implements DAOAuteur {
	
	static private	DAOAuteurJPA	instance = null;
	
	public DAOAuteurJPA() {
	}
	
	static public	DAOAuteur getInstance() {
		if (instance == null)
			instance = new DAOAuteurJPA();
		
		return instance;
	}

	@Override
	public Auteur get(int code) {
		Auteur auteur = DAOJPA.getManager().find(Auteur.class, code);
		return auteur;
	}

	@Override
	public void save(Auteur auteur) {
		testerAuteur(auteur);
		
		if (estPersistant(auteur))
			throw new IllegalArgumentException("L'auteur est déjà enregistré");
			
		DAOJPA.getManager().persist(auteur);
		DAOJPA.commit();
	}

	@Override
	public void remove(Auteur auteur) {
		testerAuteur(auteur);

		if (!estPersistant(auteur))
			throw new IllegalArgumentException("L'auteur n'est pas encore enregistré");
		
		Auteur auteurToRemove = DAOJPA.getManager().merge(auteur);
		DAOJPA.getManager().remove(auteurToRemove);
		DAOJPA.commit();
		
		auteur.setCode(-1);
	}

	@Override
	public int getNombreAuteurs() {
		Query query = DAOJPA.getManager().createQuery("select count(auteur.code) from Auteur auteur");
		Long count = (Long) query.getSingleResult();
		return count.intValue();
	}

	private boolean estPersistant(Auteur auteur) {
		return auteur.getCode()!=-1;
	}

	private void testerAuteur(Auteur auteur) {
		if (auteur==null)
			throw new IllegalArgumentException("Auteur null");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auteur> loadAll() {
		Query query = DAOJPA.getManager().createQuery("select * from Auteur auteur where 1");

		return query.getResultList();
	}

}
