package dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import core.Classe;
import dao.DAOClasse;
import dao.mock.DAOClasseMOCK;

public class Test5_DAOMatiere_getToutesLesClasses {

	private	DAOClasse	daoClasse = DAOClasseMOCK.getInstance(); // Remplacez DAO....Mock par DAO....JPA
	
	@Test
	public void test() {
		List<Classe>	listeClasses = daoClasse.getToutesLesClasses();
		
		assertEquals(2, listeClasses.size());				// Vérifie que l'on a bien 2 classes dans la base
		assertEquals(1, listeClasses.get(0).getIdentifiant());				// Vérifie que la première classe a bien 1 comme identifiant 
		assertEquals("1ère S 4", listeClasses.get(0).getNom());				// Vérifie que la première classe s'appelle "1ère S 4" 
	}

}
