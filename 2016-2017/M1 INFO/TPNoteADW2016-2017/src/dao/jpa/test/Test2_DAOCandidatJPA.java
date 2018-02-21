package dao.jpa.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import core.Candidat;
import dao.DAOCandidat;
import dao.jpa.DAOCandidatJPA;
import dao.jpa.DAOJPA;

public class Test2_DAOCandidatJPA {

	private DAOCandidat dao;
	private Candidat joly;

	@Before
	public void init() {
		DAOJPA.viderBase();
		dao = DAOCandidatJPA.getInstance();
		joly = new Candidat("Mme Eva JOLY");
	}
	
	@Test
	public void testSimple() {
		// précondition : la table est vide
		assertEquals(0, dao.getTous().size());
		
		dao.save(joly);
		
		// postcondition : la table contient un enregistrement, qui correspond bien à l'objet
		List<Candidat> tous = dao.getTous();
		assertEquals(1, tous.size());
		assertTrue(tous.contains(joly));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDoubleSauvegarde() {
		try {
			// La première fois, tout baigne
			dao.save(joly);			
		} catch (Exception e) {
			// si l'on a une exception alors ce n'est pas normal-->echec du test
			fail();
		}
		
		// En principe, maintenant ça doit planter (et c'est normal)
		dao.save(joly);
	}

}
