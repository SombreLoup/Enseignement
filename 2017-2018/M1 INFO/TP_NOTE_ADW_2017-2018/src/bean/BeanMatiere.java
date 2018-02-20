package bean;

import java.io.Serializable;
import java.util.List;

import core.Matiere;
import dao.DAOMatiere;
import dao.mock.DAOMatiereMOCK;

public class BeanMatiere implements Serializable {
	
	private	List<Matiere>	listeMatieres;
	
	private	DAOMatiere daoMatiere = DAOMatiereMOCK.getInstance();

	
	public BeanMatiere() {
		listeMatieres = daoMatiere.getToutesLesMatieres();
	}
	
	public List<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -2232822555928517369L;

}
