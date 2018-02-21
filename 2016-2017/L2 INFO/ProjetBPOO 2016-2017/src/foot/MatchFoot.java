package foot;
import competition.Competiteur;
import competition.Rencontre;
import competition.exception.CompetiteurInexistantException;
import competition.exception.RencontreIncompleteException;

public class MatchFoot extends Rencontre {

	@Override
	public void marque(Competiteur celuiQuiMarque) throws CompetiteurInexistantException {
		MarqueFoot	marque = (MarqueFoot) getMarque(celuiQuiMarque);
		marque.incrementer();
	}

	@Override
	public void abandon(Competiteur celuiQuiAbandonne) throws CompetiteurInexistantException {
		terminer();
		removeCompetiteur(celuiQuiAbandonne);
	}
	
	@Override
	public String toString() {
		Competiteur equipe0 = getCompetiteur(0);
		Competiteur equipe1 = getCompetiteur(1);
		try {
			return "Score : "+equipe0.getNom()+" "+getMarque(equipe0)+" / "+equipe1.getNom()+" "+getMarque(equipe1);
		} catch (CompetiteurInexistantException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public void debuter() throws RencontreIncompleteException {
		if (getNombreParticipants()==2)
			complete();
		super.debuter();
	}

}
