package jeu.capacites;

import jeu.HearthstoneCibleNullException;
import jeu.HearthstoneException;
import jeu.cartes.Serviteur;

public class CapaciteMarqueChasseur extends CapaciteAttaqueCible {

	public CapaciteMarqueChasseur(String nom, String description) {
		super(nom, description, 0);
	}
	
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (cible == null)
			throw new HearthstoneCibleNullException("La marque du chasseur doit avoir une cible");
		if (nbUtilisation==1)
			throw new HearthstoneException("Pas possible, t'as déjà craché ton sort, man !");
		
		nbUtilisation++;
				
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur)cible;
			
			serviteur.diminuerVie(serviteur.getVie()-1);
			
			return;
		}		
		
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}

}
