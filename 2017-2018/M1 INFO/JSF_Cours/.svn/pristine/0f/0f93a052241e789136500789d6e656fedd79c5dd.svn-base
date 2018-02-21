package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.Album;
import core.Chanson;
import core.Genre;
import core.Interprete;
import dao.DAOAlbum;
import dao.DAOGenre;
import dao.DAOInterprete;
import dao.jpa.DAOAlbumJPA;
import dao.jpa.DAOChansonJPA;
import dao.jpa.DAOGenreJPA;
import dao.jpa.DAOInterpreteJPA;
import dao.jpa.DAOJPA;


public class TestDAOAlbum {
	private Chanson morgane;
	private Chanson mistral;
	private Chanson hexagone;

	@Before
	public void init() {
		DAOJPA.viderBase();
		DAOGenre	daoGenre = DAOGenreJPA.getInstance();
		Genre	rock = new Genre("Rock");
		Genre	jazz = new Genre("Jazz");
		Genre	pop = new Genre("Pop");
		daoGenre.save(rock);
		daoGenre.save(jazz);
		daoGenre.save(pop);
		
		DAOInterprete daoInterprete = DAOInterpreteJPA.getInstance();
		Interprete renaud = new Interprete("Renaud");
		Interprete nougaro = new Interprete("Nougaro");
		Interprete phil = new Interprete("Phil Collins");
		daoInterprete.save(renaud);
		daoInterprete.save(nougaro);
		daoInterprete.save(phil);
		
		// 3 chansons non persistantes
		morgane = new Chanson("Morgane de toi", pop, renaud);
		mistral = new Chanson("Mistral gagnant", pop, renaud);
		hexagone = new Chanson("Hexagone", pop, renaud);
	}		
	
	@Test
	public void test() {
		// Quelques préconditions pour s'assurer que la base est dans l'état qu'on imagine après l'initialisation
		assertEquals(3, DAOGenreJPA.getInstance().loadAll().size());		// 3 genres
		assertEquals(3, DAOInterpreteJPA.getInstance().loadAll().size());	// et 3 interpretes

		
		Album	album = new Album("Album Renaud", 1989);
		album.add(morgane);
		album.add(mistral);
		album.add(hexagone);
		
		assertEquals(3, album.getChansons().size());
		
		DAOAlbum	dao = DAOAlbumJPA.getInstance();

		
		assertEquals(0, dao.loadAll().size());							// Pas d'albums pour l'instant
		assertEquals(0, DAOChansonJPA.getInstance().loadAll().size());	// ni de chansons...

		
		dao.save(album);

		assertEquals(1, dao.loadAll().size());							// Maintenant, il y en a un
		assertEquals(1, album.getCode());
		assertNotEquals(-1, morgane.getCode());							// On vérifie que le codea bien été attribué par JPA (mais on ne sait pas quelle est sa valeur
		assertNotEquals(-1, mistral.getCode());	
		assertNotEquals(-1, hexagone.getCode());	
		
		List<Chanson>	listeChanson = DAOChansonJPA.getInstance().loadAll();
		assertEquals(3, listeChanson.size());	// et les 3 chansons aussi...
		assertTrue(listeChanson.contains(morgane));
		assertTrue(listeChanson.contains(mistral));
		assertTrue(listeChanson.contains(hexagone));
		
		assertEquals(3, DAOGenreJPA.getInstance().loadAll().size());		// Pas de changement sur les genres
		assertEquals(3, DAOInterpreteJPA.getInstance().loadAll().size());	// Ni sur les interpretes
		
		dao.remove(album);
		
		assertEquals(0, dao.loadAll().size());							// Plus d'albums 
		assertEquals(0, DAOChansonJPA.getInstance().loadAll().size());	// ni de chansons...
	
		System.out.println(album);
	}

}
