package test;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.Genre;
import dao.DAOGenre;
import dao.jpa.DAOGenreJPA;
import dao.jpa.DAOJPA;

public class TestDAOGenre {
	@Before
	public void init() {
		DAOJPA.viderBase();
	}

	@Test
	public void test() {
		DAOGenre dao = DAOGenreJPA.getInstance();
		
		Genre	rock = new Genre("Rock");
		Genre	jazz = new Genre("Jazz");
		Genre	pop = new Genre("Pop");
		
		
		List<Genre>	listeGenre = dao.loadAll();
		int n = listeGenre.size();
		assertEquals(0, n);
		
		assertEquals(-1, rock.getCode());
		assertEquals("Rock", rock.getLibelle());
		
		dao.save(rock);
		
		listeGenre = dao.loadAll();
		assertEquals(1, listeGenre.size());
		assertTrue(rock.getCode()!=-1);
		
		Genre rock2 = dao.get(1);
		assertNotNull(rock2);
		assertEquals("Rock", rock2.getLibelle());
		
		Genre rock3 = dao.get("ck");
		assertNotNull(rock3);
		System.out.println(rock3);
		
		dao.save(jazz);
		dao.save(pop);
		assertEquals(3,dao.loadAll().size());
		
		Genre bidon = dao.get("Bidon");
		assertNull(bidon);
		
		System.out.println("Fin du test Genre");
	}

}
