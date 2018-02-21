package test.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import core.Celibataire;
import core.Rencontre;
import dao.DAOCelibataire;
import dao.DAORencontre;
import dao.jpa.DAOCelibataireJPA;
import dao.jpa.DAOJPA;
import dao.jpa.DAORencontreJPA;

public class TestJPA {

	@Before
	public void setUp() {
		DAOJPA.viderBase();
	}

	@Test
	public void testSansDAO() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("Exam ADW Session 1 2014-2015");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tr = em.getTransaction();
		tr.begin();

		try {
			Celibataire andre = new Celibataire("andre", null, "57000", true);
			Celibataire marie = new Celibataire("marie", null, "58000", false);
			Rencontre rdv = new Rencontre("RDV", null);

			rdv.add(marie);

			em.persist(rdv);
			tr.commit();
			tr.begin();

			rdv.add(andre);

			rdv.setDescription("Bidule");

			em.persist(rdv);

			tr.commit();
		} catch (RuntimeException re) {
			if (tr.isActive())
				tr.rollback(); // Exception donc on annule tout
			throw re;
		}
	}

	@Test
	public void testAvecDAO() {
		Celibataire andre = new Celibataire("andre", null, "57000", true);
		Celibataire marie = new Celibataire("marie", null, "58000", false);
		
		DAOCelibataire dao = DAOCelibataireJPA.getInstance();
		dao.saveOrUpdate(marie);
		dao.saveOrUpdate(andre);
		
		
		List<Celibataire> listeCelibataires = dao.load("");
		assertEquals(2, listeCelibataires.size());
		assertTrue(listeCelibataires.contains(marie));
		assertTrue(listeCelibataires.contains(andre));
		
		dao.remove(marie);
		
		listeCelibataires = dao.load("");
		assertEquals(1, listeCelibataires.size());
		assertFalse(listeCelibataires.contains(marie));
		assertTrue(listeCelibataires.contains(andre));
		
		dao.saveOrUpdate(marie);
		
		Rencontre rdv = new Rencontre("RDV", null);

		rdv.add(marie);
		rdv.add(andre);
		
		DAORencontre daoRen = DAORencontreJPA.getInstance();
		daoRen.saveOrUpdate(rdv);
		
		Rencontre renc = daoRen.get(rdv.getCode());
		assertEquals(2,renc.getParticipants().size());
		assertTrue(renc.getParticipants().contains(marie));
		assertTrue(renc.getParticipants().contains(andre));
		
		rdv.setDescription("Bidule");
	}

}
