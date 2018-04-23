package monopoly.actions;

import monopoly.JoueurMonopoly;
import plateau.Action;

public class ToucherCaseDepart extends Action {
	private	JoueurMonopoly	joueur;
	
	public ToucherCaseDepart(JoueurMonopoly joueur) {
		this.joueur = joueur;
	}

	@Override
	public void executer() {
		int	compte = joueur.getCompte();
		compte += 200;
		joueur.setCompte(compte);
	}
	
	
}
