package jeu.capacites;

import jeu.HearthstoneException;
import jeu.cartes.Serviteur;

public class CapaciteCharge extends Capacite {

	public CapaciteCharge() {
		super("Charge", "vise un serviteur en attente et il peut attaquer tout de suite");
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (! (cible instanceof Serviteur))
			throw new HearthstoneException("Ver de terre greloteux que tu es, tu dois désigner un serviteur !");
		
		Serviteur serviteur = (Serviteur)cible;
		serviteur.rendreJouable();
	}

}
