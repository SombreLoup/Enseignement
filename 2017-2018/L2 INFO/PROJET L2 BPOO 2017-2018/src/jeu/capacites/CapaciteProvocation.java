package jeu.capacites;

import jeu.HearthstoneException;

public class CapaciteProvocation extends Capacite {

	public CapaciteProvocation(String nom, String description) {
		super(nom, description);
	}

	public CapaciteProvocation() {
		super("Provocation", "Les attaques n'atteindront pas votre héros");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
