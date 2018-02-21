package tennis;

import static org.junit.Assert.*;

import org.junit.Test;

import competition.Competiteur;
import competition.Rencontre;
import competition.exception.CompetiteurInexistantException;
import competition.exception.DoubleException;
import competition.exception.RencontreIncompleteException;

public class TestTennis {

	
	@Test
	public void testFinSetNormal() {
		Competiteur	Federer = new TennisMan("Roger FEDERER");
		Competiteur	Nadal = new TennisMan("NADAL");
		
		Rencontre match = new MatchTennis();
		
		System.out.println("----- Test d'une fin de set normale -------");
		try {
			
			match.addCompetiteur(Federer);
			match.addCompetiteur(Nadal);
			
			MarqueTennis mF = new MarqueTennis();
			mF.setSetCourant(5);
			mF.setJeuCourant(45);
			match.setMarque(0, mF);
			
			MarqueTennis mN = new MarqueTennis();
			mN.setSetCourant(4);
			mN.setJeuCourant(30);
			match.setMarque(1, mN);
			
			match.debuter();

			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
		} catch (CompetiteurInexistantException | DoubleException | RencontreIncompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFinSetTieBreak() {
		Competiteur	Federer = new TennisMan("Roger FEDERER");
		Competiteur	Nadal = new TennisMan("NADAL");
		
		Rencontre match = new MatchTennis();
		
		System.out.println("----- Test d'une fin de set normale -------");
		try {
			
			match.addCompetiteur(Federer);
			match.addCompetiteur(Nadal);
			
			MarqueTennis mF = new MarqueTennis();
			mF.setSetCourant(6);
			mF.setJeuCourant(6);
			match.setMarque(0, mF);
			
			MarqueTennis mN = new MarqueTennis();
			mN.setSetCourant(6);
			mN.setJeuCourant(4);
			match.setMarque(1, mN);
			
			match.debuter();

			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
		} catch (CompetiteurInexistantException | DoubleException | RencontreIncompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUnJeuNormal() {
		Competiteur	Federer = new TennisMan("Roger FEDERER");
		Competiteur	Nadal = new TennisMan("NADAL");
		
		Rencontre match = new MatchTennis();
		
		try {
			match.addCompetiteur(Federer);
			match.addCompetiteur(Nadal);
			
			match.debuter();

			System.out.println("----- Test d'une fin de jeu normale -------");
						
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			match.marque(Nadal);
			System.out.println(""+match);
			match.marque(Nadal);
			System.out.println(""+match);
			match.marque(Nadal);
			System.out.println(""+match);
			match.marque(Nadal);
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			match.marque(Federer);
			System.out.println(""+match);
			
		} catch (Exception e) {
			fail("Une exception s'est produite");
		} 
	}

}
