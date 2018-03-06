package dao.mock;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Matiere;
import dao.DAOMatiere;


public class DAOMatiereMOCK implements DAOMatiere, Serializable {
	
	private static DAOMatiere instance = null;
	private List<Matiere> listeMatiere;

	public static DAOMatiere getInstance() {
		if (instance ==null) {
			instance = new DAOMatiereMOCK();
		}
		return instance;
	}
	
	/**
	 * Initialise la liste des matières avec 8 matières. Elles correspondent exactement aux
	 * matières se trouvant dans la base
	 */
	public DAOMatiereMOCK() {
		listeMatiere = new ArrayList<Matiere>();
		listeMatiere.add(new Matiere(1,"Mathématiques",true));
		listeMatiere.add(new Matiere(2,"Français",true));
		listeMatiere.add(new Matiere(3,"Physique",false));
		listeMatiere.add(new Matiere(4,"Philosophie",false));
		listeMatiere.add(new Matiere(5,"Allemand LV1",true));
		listeMatiere.add(new Matiere(6,"Allemand LV2",false));
		listeMatiere.add(new Matiere(7,"Anglais LV1",true));
		listeMatiere.add(new Matiere(8,"Anglais LV2",false));
	}

	
	
	/**
	 * Renvoie toutes les matières se trouvant dans la table de la base de données (ici, elle est simulée
	 * par la liste des matières.
	 */
	public List<Matiere> getToutesLesMatieres() {
		return listeMatiere;
	}

	
	/**
	 * La fonction get renvoie l'objet Matière si le numéro donné en paramètre
	 * correspond bien à un enregistrement. Sinon, la fonction renvoie null
	 */
	public Matiere get(int num) {
		for (Matiere m : listeMatiere) {
			if (m.getNumero()==num) {
				return m;
			}
		}	
		return null;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6349342966350107572L;

}
