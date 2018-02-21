package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Interprete;
import dao.DAOInterprete;
import dao.jpa.DAOInterpreteJPA;
import dao.jpa.DAOJPA;

public class TestDAOInterprete {

	@Before
	public void init() {
		DAOJPA.viderBase();
	}
	
	@Test
	public void testSansChanson() {
		Interprete	renaud = new Interprete("Renaud");
		Interprete	nougaro = new Interprete("Nougaro");
		Interprete	collins = new Interprete("Phil Collins");
	
		
		DAOInterprete dao = DAOInterpreteJPA.getInstance();
		assertEquals(0, dao.loadAll().size());
		
		assertEquals(-1, renaud.getCode());
		dao.save(renaud);
		assertEquals(1, renaud.getCode());
		assertEquals(0, renaud.getChansons().size());
		assertEquals(1, dao.loadAll().size());
		
		Interprete renaud2 = dao.get(1);
		assertEquals(renaud, renaud2);

		dao.save(collins);
		dao.save(nougaro);

		assertEquals(3, dao.loadAll().size());
		
		Interprete collins2 = DAOInterpreteJPA.getInstance().get("ol");
		assertEquals(collins, collins2);
		
		System.out.println(renaud);
		System.out.println("Fin du test des Interpretes");
				
	}

}
