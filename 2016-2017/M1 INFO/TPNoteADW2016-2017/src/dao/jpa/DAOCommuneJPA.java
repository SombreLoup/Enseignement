package dao.jpa;



import javax.persistence.NoResultException;

import core.Commune;
import dao.DAOCommune;

public class DAOCommuneJPA extends DAOJPA implements DAOCommune {

	private	 DAOCommuneJPA() {
	}
	
	private static DAOCommune instance = null;
	
	public static DAOCommune getInstance() {
		if (instance==null)
			instance = new DAOCommuneJPA();
		return instance;
	}


	@Override
	public void save(Commune commune) throws IllegalArgumentException {
		// TODO DAOCommuneJPA
	}

	@Override
	public Commune get(String boutDeNom, int codePostal) {
		String requeteJPQL = "SELECT c FROM Commune c WHERE c.nom LIKE ?1 AND c.codePostal=?2";
		// Attention, si la requête (et c'est bien une requête SQL) ne sélectionne rien du tout, 
		// JPA renvoie une NoResultException. Vous devez la gérer dans un try/catch
		Commune commune = null;
		
		// TODO DAOCommuneJPA.get
		
		return commune;
	}


	@Override
	public void update(Commune commune) throws IllegalArgumentException {
		// TODO DAOCommuneJPA.update
	}

}
