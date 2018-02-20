package dao.mock;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Classe;
import dao.DAOClasse;

public class DAOClasseMOCK implements DAOClasse, Serializable {


	private static DAOClasse instance;
	private	ArrayList<Classe> listeClasses = new ArrayList<Classe>();
	
	public static DAOClasse getInstance() {
		if (instance==null)
			instance = new DAOClasseMOCK();
		return instance;
	}

	public DAOClasseMOCK() {
		Classe classe = new Classe(1,"1ère S 4",null,null);
		classe.ajouterMatiere(DAOMatiereMOCK.getInstance().get(1));
		classe.ajouterMatiere(DAOMatiereMOCK.getInstance().get(3));
		classe.ajouterMatiere(DAOMatiereMOCK.getInstance().get(7));
		
		
		listeClasses.add(classe);
		listeClasses.add(new Classe(2,"1ère S 12", null,null));
	}
	
	@Override
	public Classe get(int i) {
		for (Classe c : listeClasses) {
			if (c.getIdentifiant()==i) {
				return c;
			}
		}	
		return null;	}

	@Override
	public List<Classe> getToutesLesClasses() {
		return listeClasses;
	}
	
	@Override
	public void update(Classe classe) {
		for (Classe c : listeClasses) {
			if (c.getIdentifiant()==classe.getIdentifiant()) {
				listeClasses.remove(c);
				listeClasses.add(classe);
				return;
			}
		}
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -986503017425423474L;

}
