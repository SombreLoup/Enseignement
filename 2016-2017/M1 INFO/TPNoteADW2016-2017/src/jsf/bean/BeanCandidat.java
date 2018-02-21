package jsf.bean;

import java.io.Serializable;

import core.BureauVote;
import core.Commune;
import dao.jpa.DAOCommuneJPA;

/**
 * Compléter ce bean. J'y ai déjà mis des champs qui pourraient vous être utile,
 * ainsi qu'une fonction qui affiche leur valeur dans la console.
 * Le constructeur par défaut est également déjà fait. Il initialise en dur la commune de Moulins-lès-Metz.
 * On pourrait imagine qu'en réalité, ce soit un formulaire qui permette cette initialisation.
 * Mais on ne traite pas cet aspect dans ce TP. D'où l'initialisation en dur.
 * @author yann
 *
 */
public class BeanCandidat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3044796379020911713L;
	
	private	Commune		commune;
	private	BureauVote	bureau;
	private	int 		codeCandidat;
	private	int			nombreVoix;
	
	private void debugChamps() {
		System.out.println("Nombre de voix : "+nombreVoix);
		System.out.println("Code du candidat : "+codeCandidat);
		if (commune==null)
			System.out.println("Pas de commune");
		else
			System.out.println("Commune : "+commune);
		
		if (bureau==null)
			System.out.println("Pas de bureau");
		else
			System.out.println("Bureau : "+bureau);
	}

	public BeanCandidat() {
		System.out.println("********************* BeanCandidat() *************");
		commune = DAOCommuneJPA.getInstance().get("Moulins", 57160);
		bureau = commune.get("Mairie");
		nombreVoix = 0;
		codeCandidat = -1;
		
		debugChamps();
	}

	
}
