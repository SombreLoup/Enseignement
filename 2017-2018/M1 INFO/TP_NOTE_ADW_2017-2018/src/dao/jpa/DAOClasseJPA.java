package dao.jpa;

import java.util.List;

import core.Classe;
import dao.DAOClasse;

public class DAOClasseJPA implements DAOClasse {

	private static DAOClasse instance;

	public static DAOClasse getInstance() {
		if (instance==null)
			instance = new DAOClasseJPA();
		return instance;
	}

	public Classe get(int i) {
		return DAOJPA.getManager().find(Classe.class, i);
	}

	public List<Classe> getToutesLesClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Classe classe) {
		// TODO Auto-generated method stub
		
	}

}
