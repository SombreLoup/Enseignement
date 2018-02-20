package dao.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.DAOMatiere;
import dao.mock.DAOMatiereMOCK;

public class Test2_DAOMatiere_get {

	private	DAOMatiere	daoMatiere = DAOMatiereMOCK.getInstance(); // Remplacez DAO....Mock par DAO....JPA
	
	@Test
	public void test() {
		assertEquals("Philosophie",daoMatiere.get(4).getNom());	// On recherche une matière qui existe
		assertNull(daoMatiere.get(16));							// On recherche une matière qui n'existe pas (donc on s'attend à null)
	}

}
