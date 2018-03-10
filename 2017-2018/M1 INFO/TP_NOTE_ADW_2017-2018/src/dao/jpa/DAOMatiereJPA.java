package dao.jpa;

import java.util.List;

import javax.persistence.Query;

import core.Matiere;
import dao.DAOMatiere;

public class DAOMatiereJPA implements DAOMatiere {

	private static DAOMatiere instance = null;

	public static DAOMatiere getInstance() {
		if (instance ==null) {
			instance = new DAOMatiereJPA();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Matiere> getToutesLesMatieres() {
		Query query = DAOJPA.getManager().createQuery("select m from Matiere m");

		return query.getResultList();
	}

	public Matiere get(int num) {
		return DAOJPA.getManager().find(Matiere.class, num);
	}

}
