package dao.jpa;

import cb.Commande;
import dao.DAOCommande;

public class DAOCommandeJPA implements DAOCommande {

	private	static DAOCommandeJPA	instance = null;
	
	@Override
	public Commande get(int numero) {
		return (Commande)DAOJPA.getManager().find(Commande.class, numero);
	}

	public static DAOCommandeJPA getInstance() {
		if (instance==null)
			instance = new DAOCommandeJPA();
		return instance;
	}

}
