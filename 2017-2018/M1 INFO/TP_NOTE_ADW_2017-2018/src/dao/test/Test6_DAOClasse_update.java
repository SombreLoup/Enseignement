package dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Classe;
import dao.DAOClasse;
import dao.mock.DAOClasseMOCK;
import dao.mock.DAOMatiereMOCK;

public class Test6_DAOClasse_update {

	private	DAOClasse	daoClasse = DAOClasseMOCK.getInstance(); // Remplacez DAO....Mock par DAO....JPA
	
	@Test
	public void test() {
		assertEquals(2, daoClasse.getToutesLesClasses().size());		// précondition : il y a 2 classes dans la base
		
		Classe classeOriginale = daoClasse.get(1);	// On récupère la classe 1 (la 1ère S 4)
		assertEquals("1ère S 4", classeOriginale.getNom());										// On vérifie tout de même si son nom est correct
		assertEquals(3,classeOriginale.getListeMatieres().size());								// et si les matières sont bien là
		assertEquals("Mathématiques", classeOriginale.getListeMatieres().get(0).getNom()); 	
		
		Classe classeModifiee = classeOriginale.clone();	// On clone la classe originale pour éviter des effets "d'indirection"
		assertEquals(classeOriginale,classeModifiee);	// clonage ok ?
		
		classeModifiee.ajouterMatiere(DAOMatiereMOCK.getInstance().get(5));	// On ajoute une nouvelle matière "Allemand LV1"
		assertNotEquals(classeOriginale, classeModifiee);						// Les deux classes sont-elles différentes maintenant ?
		
		// Tout ça, ce ne sont finalement que les préconditions avant de faire vraiment le test de modification dans la base
		daoClasse.update(classeModifiee);
		assertEquals(2, daoClasse.getToutesLesClasses().size());		// il doit toujours y avoir deux classes dans la base
		
		Classe laClasse = daoClasse.get(classeOriginale.getIdentifiant());	// laClasse est l'objet correspondant précisément à ce qu'il y a dans la base
		assertEquals("1ère S 4", laClasse.getNom());										// On vérifie tout de même si son nom est correct
		assertEquals(4,laClasse.getListeMatieres().size());								// et s'il y a une matière de plus
		assertEquals("Allemand LV1", laClasse.getListeMatieres().get(3).getNom()); 		// Est-ce que c'est bien "Allemand LV1" ?	
	}

}
