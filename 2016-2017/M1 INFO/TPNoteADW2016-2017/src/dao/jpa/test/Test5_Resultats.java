package dao.jpa.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.BureauVote;
import core.Candidat;
import core.Commune;
import core.ResultatPartiel;
import dao.DAOCommune;
import dao.jpa.DAOCandidatJPA;
import dao.jpa.DAOCommuneJPA;
import dao.jpa.DAOJPA;

public class Test5_Resultats {
	private DAOCommune dao;
	private Commune moulins;
	private	BureauVote	mairie;
	private	BureauVote	groupeScolaire;
	private	BureauVote	ferry;
	private Candidat joly;


	@Before
	public void init() {
		DAOJPA.viderBase();
		dao = DAOCommuneJPA.getInstance();
		moulins = new Commune("Moulins-lès-Metz", 57160);
		mairie = new BureauVote("Mairie-Ecole");
		groupeScolaire = new BureauVote("Groupe scolaire");
		ferry = new BureauVote("Ecole Jules FERRY");
		
		moulins.ajouterBureau(mairie);
		moulins.ajouterBureau(groupeScolaire);
		moulins.ajouterBureau(ferry);
		dao.save(moulins);
		
		joly = new Candidat("Mme Eva JOLY");
		DAOCandidatJPA.getInstance().save(joly);
	}
	
	@Test
	public void test() {
		// La fonction init a enregistré la commune avec ses 3 bureaux de vote mais sans résultats partiels
		// Pour le test, on repart des infos que l'on trouve dans la base de données et on ajoute des résultats partiels pour deux bureaux de vote
		Commune	maCommune = dao.get("Moulins", 57160);
		
		// Aucune voix pour l'instant
		assertEquals(0,maCommune.getResultat(joly));	
		
		// On récupère les bureaux de vote
		mairie = maCommune.get("Mairie");
		if (mairie==null) fail();
		ferry = maCommune.get("FERRY");
		if (ferry==null) fail();
		
		ferry.add(new ResultatPartiel(joly, ferry, 16));
		mairie.add(new ResultatPartiel(joly, mairie, 34));
		
		assertEquals(50, maCommune.getResultat(joly));
		
		dao.update(maCommune);
		// Maintenant la base de données contient aussi le nombre de voix. On va le vérifier en recherchant à nouveau la commune dans la base
		
		
		assertEquals(50, dao.get("Metz", 57160).getResultat(joly)); // On recherche avec un autre bout du nom...		
		
		// Et si on ajoute le 3e bureau de vote et qu'on remet à jour ?
		groupeScolaire = maCommune.get("Groupe");
		if (groupeScolaire==null) fail();

		groupeScolaire.add(new ResultatPartiel(joly, groupeScolaire, 20));

		dao.update(maCommune);

		assertEquals(70, dao.get("lès", 57160).getResultat(joly)); // On recherche avec un autre bout du nom...		
		
		assertEquals(70, DAOCandidatJPA.getInstance().getResultatsComplets(joly.getCode()));

	}

}
