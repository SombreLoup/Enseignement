package dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import core.Matiere;
import dao.DAOMatiere;
import dao.mock.DAOMatiereMOCK;

public class Test3_DAOMatiere_getToutesLesMatieres {

	private	DAOMatiere	daoMatiere = DAOMatiereMOCK.getInstance(); // Remplacez DAO....Mock par DAO....JPA
	
	@Test
	public void test() {
		List<Matiere>	listeMatiere = daoMatiere.getToutesLesMatieres();
		
		assertEquals(8, listeMatiere.size());				// Vérifie que l'on a bien 8 matières dans la base
		assertEquals(1, listeMatiere.get(0).getNumero());				// Vérifie que la première matière a bien 1 comme numéro 
		assertEquals("Mathématiques", listeMatiere.get(0).getNom());	// Vérifie que la première matière s'appelle "Mathématiques" 
	}

}
