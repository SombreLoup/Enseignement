package foot;

import static org.junit.Assert.*;

import org.junit.Test;

import competition.Competiteur;
import competition.Rencontre;
import competition.exception.CompetiteurInexistantException;
import competition.exception.DoubleException;
import competition.exception.RencontreIncompleteException;

public class TestMatch {

	
	
	@Test(expected=RencontreIncompleteException.class)
	public void testDebutIncomplet() throws RencontreIncompleteException {
		Competiteur	OM = new EquipeFoot("Olympique de Marseille");
		
		Rencontre match = new MatchFoot();
		
		try {
			match.addCompetiteur(OM);
		} catch (DoubleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		match.debuter();
		
	}

	@Test
	public void testNominal() {
		Competiteur	OM = new EquipeFoot("Olympique de Marseille");
		Competiteur	OL = new EquipeFoot("Olympique Lyonnais");
		
		Rencontre match = new MatchFoot();
		
		try {
			match.addCompetiteur(OL);
			match.addCompetiteur(OM);
			
			match.debuter();
			
			assertEquals("0",match.getMarque(OM).getValeur());
			assertEquals("0",match.getMarque(OL).getValeur());
			
			match.marque(OM);
			assertEquals("1",match.getMarque(OM).getValeur());
			
			match.marque(OL);
			assertEquals("1",match.getMarque(OL).getValeur());
			
			match.marque(OM);
			assertEquals("2",match.getMarque(OM).getValeur());
			
			assertEquals(OM, match.getGagnant());
			
			match.terminer();
			
			assertTrue(match.isTerminee());
			
		} catch (Exception e) {
			fail("Une exception s'est produite");
		} 
	}

}
