package core.test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.BureauVote;
import core.Candidat;
import core.Commune;
import core.ResultatPartiel;

public class TestCore {

	@Test
	public void test() {
		Commune moulins = new Commune("Moulins-lès-Metz", 57160);
		BureauVote b1 = new BureauVote("Mairie-école");
		BureauVote b2 = new BureauVote("Ecole Jules FERRY");
		BureauVote b3 = new BureauVote("Groupe scolaire");
		moulins.ajouterBureau(b1);
		moulins.ajouterBureau(b2);
		moulins.ajouterBureau(b3);
		
		Candidat	joly = new Candidat("Mme Eva JOLY");
		Candidat	lepen = new Candidat("Mme Marine LE PEN");
		Candidat	sarko = new Candidat("M. Nicolas SARKOSY");

		b1.add(new ResultatPartiel(joly, b1, 1));
		b1.add(new ResultatPartiel(lepen, b1, 11));
		b1.add(new ResultatPartiel(sarko, b1, 101));
		
		b2.add(new ResultatPartiel(joly, b2, 2));
		b2.add(new ResultatPartiel(lepen, b2, 12));
		b2.add(new ResultatPartiel(sarko, b2, 102));
		
		b3.add(new ResultatPartiel(joly, b3, 3));
		b3.add(new ResultatPartiel(lepen, b3, 13));
		b3.add(new ResultatPartiel(sarko, b3, 103));
		
		assertEquals(6, moulins.getResultat(joly));
		assertEquals(36, moulins.getResultat(lepen));
		assertEquals(306, moulins.getResultat(sarko));
	}

}
