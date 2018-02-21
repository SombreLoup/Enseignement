package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Chanson;
import core.Genre;
import core.Interprete;
import dao.DAOGenre;
import dao.DAOInterprete;
import dao.jpa.DAOChansonJPA;
import dao.jpa.DAOGenreJPA;
import dao.jpa.DAOInterpreteJPA;
import dao.jpa.DAOJPA;

public class TestDAOChanson {

	@Before
	public void init() {
		DAOJPA.viderBase();
		DAOGenre	daoGenre = DAOGenreJPA.getInstance();
		daoGenre.save(new Genre("Rock"));
		daoGenre.save(new Genre("Jazz"));
		daoGenre.save(new Genre("Pop"));
		
		DAOInterprete daoInterprete = DAOInterpreteJPA.getInstance();
		daoInterprete.save(new Interprete("Renaud"));
		daoInterprete.save(new Interprete("Nougaro"));
		daoInterprete.save(new Interprete("Phil Collins"));
	}
	
	@Test
	public void test() {
		Genre	rock = DAOGenreJPA.getInstance().get("Rock");
		Interprete collins = DAOInterpreteJPA.getInstance().get("Collins");
		
		assertEquals(0, DAOChansonJPA.getInstance().loadAll().size());
		assertEquals(3, DAOGenreJPA.getInstance().loadAll().size());
		assertEquals(3, DAOInterpreteJPA.getInstance().loadAll().size());
		
		Chanson mama = new Chanson("Mama", rock, collins );	
		assertEquals(-1, mama.getCode());
		assertEquals("Mama", mama.getTitre());
		assertEquals(collins, mama.getInterprete());
		assertEquals(1, collins.getChansons().size());

		DAOChansonJPA.getInstance().save(mama);
		
		assertEquals(1, mama.getCode());
		assertEquals(3, DAOGenreJPA.getInstance().loadAll().size());// Pas de changement sur les genres
		assertEquals(3, DAOInterpreteJPA.getInstance().loadAll().size()); // Pas de changement sur les interpretes
		assertEquals(1, DAOChansonJPA.getInstance().loadAll().size()); // On a une chanson
		
		Chanson mama2 = DAOChansonJPA.getInstance().get(1);
		
		assertEquals(mama, mama2);
		assertEquals(collins, mama2.getInterprete()); // A partir d'une chanson, on récupère l'interprete
		
		Interprete	collins3 = DAOInterpreteJPA.getInstance().get(3);
		
		
		assertEquals(1, collins3.getChansons().size());
		assertTrue(collins3.getChansons().contains(mama)); // A partir de l'interprete, on récupere les chansons
		
		System.out.println(DAOChansonJPA.getInstance().loadAll());
	}

}
