package dao.jpa.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import core.Commune;
import dao.DAOCommune;
import dao.jpa.DAOCommuneJPA;
import dao.jpa.DAOJPA;

/**
 * Dans ce test, la commune ne possède pas encore de bureaux de vote.
 * @author yann
 *
 */
public class Test3_DAOCommuneJPA {

	private DAOCommune dao;
	private Commune moulins;

	@Before
	public void init() {
		DAOJPA.viderBase();
		dao = DAOCommuneJPA.getInstance();
		moulins = new Commune("Moulins-lès-Metz", 57160);
	}
	
	@Test
	public void testSimple() {
		dao.save(moulins);
		
		// Si on cherche Moulins, on le trouve
		Commune c1 = dao.get("Moulins", 57160);
		assertEquals(moulins, c1);
		
		// Si on cherche autre chose, on trouve null
		Commune c2 = dao.get("Rozérieule", 57160);
		assertNull(c2);
		Commune c3 = dao.get("Moulins", 57990);
		assertNull(c3);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDoubleSauvegarde() {
		try {
			// La première fois, tout baigne
			dao.save(moulins);			
		} catch (Exception e) {
			// si l'on a une exception alors ce n'est pas normal-->echec du test
			fail();
		}
		
		// En principe, maintenant ça doit planter (et c'est normal)
		dao.save(moulins);

	}

}
