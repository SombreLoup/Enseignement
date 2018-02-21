package test.jpa;


import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import core.Auteur;
import dao.DAOAuteur;
import dao.jpa.DAOAuteurJPA;
import dao.jpa.DAOJPA;

public class Test2_DAOAuteur {

	private DAOAuteur dao;
	private Auteur auteur1;

	@Before
	public void init() {
		auteur1 = new Auteur("Victor HUGO", 3, Calendar.getInstance().getTime());
		dao = DAOAuteurJPA.getInstance();
		DAOJPA.viderBase();
	}
	
	@Test
	public void testAuteur() {
		// Préconditions
		assertEquals(0,dao.getNombreAuteurs());	// rien dans la table
		assertEquals(0, auteur1.size()); 		// Pas d'article pour cet auteur

		dao.save(auteur1);
		
		// Postconditions
		assertEquals(1,dao.getNombreAuteurs());	// Un article est dans la table
		assertNotEquals(-1, auteur1.getCode());	// Le code de l'auteur contient la clé générée par la BD
		
		// On vérifie que l'auteur de la table est bien celui du test
		Auteur	auteur2 = dao.get(auteur1.getCode());	
		assertEquals(0, auteur2.size()); 		// Toujours pas d'article pour cet auteur
		assertEquals(auteur1, auteur2);			// C'est bien le même code
		
		// On supprime l'objet que l'on vient de consulter
		dao.remove(auteur2);				
		
		assertEquals(0,dao.getNombreAuteurs());	// La table est bien vide
		
		// Attention, on ne peut rien faire pour auteur1. Il parait toujours managé...
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSaveException() {

		dao.save(auteur1);
		assertEquals(1,dao.getNombreAuteurs());

		// Ajouter le même auteur lance une exception
		dao.save(auteur1);
		assertEquals(1,dao.getNombreAuteurs());
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveException() {
	
		dao.save(auteur1);
		assertEquals(1,dao.getNombreAuteurs());

		// Supprimer 2 fois le même auteur lance une exception 
		dao.remove(auteur1);
		assertEquals(0,dao.getNombreAuteurs());
		
		dao.remove(auteur1);
	}

}
