package bean;

import java.io.Serializable;
import java.util.List;

import core.Classe;
import core.Matiere;
import dao.DAOClasse;
import dao.DAOMatiere;
import dao.mock.DAOClasseMOCK;
import dao.mock.DAOMatiereMOCK;

public class BeanClasse implements Serializable {
	
	private	Classe	classe;
	private	int		classeSelectionnee;
	
	public int getClasseSelectionnee() {
		return classeSelectionnee;
	}

	public void setClasseSelectionnee(int classeSelectionnee) {
		this.classeSelectionnee = classeSelectionnee;
	}

	private	Matiere	matiereAAjouter = null;
	
	private	DAOClasse daoClasse = DAOClasseMOCK.getInstance();
	private	DAOMatiere daoMatiere = DAOMatiereMOCK.getInstance();
	
	public BeanClasse() {
		classe = daoClasse.get(1);
	}
	
	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	public List<Classe>	listeClasses;
	
	public List<Classe> getListeClasses() {
		return daoClasse.getToutesLesClasses();
	}
	
	public boolean estDansClasse(int numeroMatiere) {
		matiereAAjouter = daoMatiere.get(numeroMatiere);
		return classe.getListeMatieres().contains(matiereAAjouter);
		
	}

	public String ajouterMatiere(Integer num) {
		matiereAAjouter = daoMatiere.get(num);
		if (matiereAAjouter !=null) {
			classe.ajouterMatiere(matiereAAjouter);
			matiereAAjouter = null;
		}
		
		return "CreationClasse.xhtml";
	}
	
	public String creerClasse() {
		classe = daoClasse.get(classeSelectionnee);

		return "CreationClasse.xhtml";
	}
	
	public String mettreAJour() {
		daoClasse.update(classe);
				
		return "DetailClasse.xhtml";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891822862316204476L;

}
