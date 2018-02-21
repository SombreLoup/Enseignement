package dao.jpa;

import java.util.List;
import core.Celibataire;
import dao.DAOCelibataire;

public class DAOCelibataireJPA extends DAOJPA implements DAOCelibataire {

	static private	DAOCelibataireJPA	instance = null;
	
	private  DAOCelibataireJPA() {
	}
	
	static public DAOCelibataire getInstance() {
		if (instance==null)
			instance = new DAOCelibataireJPA();
		return instance;
	}

	
	@Override
	public Celibataire get(int code) {
		Celibataire celibataire = DAOJPA.getManager().find(Celibataire.class, code);
		return celibataire;
	}

	@Override
	public void saveOrUpdate(Celibataire celibataire) {
		DAOJPA.getManager().persist(celibataire);
		DAOJPA.commit();
	}

	@Override
	public void remove(Celibataire celibataire) {
		Celibataire celibataireToRemove = DAOJPA.getManager().merge(celibataire);
		DAOJPA.getManager().remove(celibataireToRemove);
		DAOJPA.commit();
		celibataire.setCode(-1);
	}

	@Override
	public List<Celibataire> load(String string) {
		return DAOJPA.getManager().createQuery("SELECT a FROM Celibataire a WHERE a.nom LIKE ?1",Celibataire.class)
				.setParameter(1, "%"+string+"%")
				.getResultList();
	}

}
