package dao.jpa.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import core.BureauVote;
import core.Commune;
import dao.DAOCommune;
import dao.jpa.DAOCommuneJPA;
import dao.jpa.DAOJPA;

/**
 * Cette fois, on teste avec des bureaux de vote. Il faudra juste enlever les Transient et mapper correctement la relation 
 * @author yann
 *
 */
public class Test4_DAOCommuneJPA {

	private DAOCommune dao;
	private Commune moulins;
	private	BureauVote	mairie;
	private	BureauVote	groupeScolaire;
	private	BureauVote	ferry;

	@Before
	public void init() {
		DAOJPA.viderBase();
		dao = DAOCommuneJPA.getInstance();
		moulins = new Commune("Moulins-lès-Metz", 57160);
		mairie = new BureauVote("Maire-Ecole");
		groupeScolaire = new BureauVote("Groupe scolaire");
		ferry = new BureauVote("Ecole Jules FERRY");
	}
	
	@Test
	public void test() {
		moulins.ajouterBureau(mairie);
		moulins.ajouterBureau(groupeScolaire);
		moulins.ajouterBureau(ferry);
		
		assertEquals(3, moulins.getNombreBureaux());
		
		dao.save(moulins);
		
		// Si on vérifie que le get remonte bien la commune et ses bureaux de vote
		Commune c1 = dao.get("Moulins", 57160);
		assertEquals(moulins, c1);
		
		assertEquals(3, c1.getNombreBureaux());
	}
}
