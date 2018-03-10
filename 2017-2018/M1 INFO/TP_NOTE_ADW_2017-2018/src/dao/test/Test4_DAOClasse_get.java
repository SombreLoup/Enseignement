package dao.test;

import static org.junit.Assert.*;


import org.junit.Test;

import core.Classe;
import dao.DAOClasse;
import dao.jpa.DAOClasseJPA;
import dao.mock.DAOClasseMOCK;

public class Test4_DAOClasse_get {

	private	DAOClasse	daoClasse = DAOClasseJPA.getInstance(); // Remplacez DAO....Mock par DAO....JPA
	
	@Test
	public void test() {
		Classe classe = daoClasse.get(1); 	// On recherche une classe qui existe
		assertEquals("1ère S 4", classe.getNom());			// On vérifie si son nom est correct
		assertEquals(3,classe.getListeMatieres().size());								// et si les matières sont bien là
		assertEquals("Mathématiques", classe.getListeMatieres().get(0).getNom()); 	
		
		assertNull(daoClasse.get(16));		// On recherche une classe qui n'existe pas (donc on s'attend à null)
	}

}
