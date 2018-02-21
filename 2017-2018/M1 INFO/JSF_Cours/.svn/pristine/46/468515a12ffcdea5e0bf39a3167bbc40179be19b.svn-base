package dao.jpa;

import java.util.List;

import core.Interprete;
import dao.DAOInterprete;

public class DAOInterpreteJPA extends DAOJPA implements DAOInterprete {
	
	@Override
	public List<Interprete> loadAll() {
		return DAOJPA.getManager().createQuery("SELECT g FROM Interprete g",Interprete.class).getResultList();
	}


	static private	DAOInterpreteJPA	instance = null;
	
	public DAOInterpreteJPA() { // public pour JSF
	}
	
	static public	DAOInterpreteJPA getInstance() {
		if (instance == null)
			instance = new DAOInterpreteJPA();
		
		return instance;
	}

	
	@Override
	public Interprete get(int code) {
		Interprete interprete = DAOJPA.getManager().find(Interprete.class, code);
		return interprete;
	}

	@Override
	public void save(Interprete interprete) {
		DAOJPA.getManager().persist(interprete);
		DAOJPA.commit();
	}

	@Override
	public Interprete get(String nom) {
		List<Interprete> listInterprete = DAOJPA.getManager().createQuery("SELECT i FROM Interprete i WHERE i.nom LIKE ?1",Interprete.class).setParameter(1, "%"+nom+"%").getResultList();
		if (listInterprete.size()==0)
			return null;
		else
			return listInterprete.get(0);
	}
}
