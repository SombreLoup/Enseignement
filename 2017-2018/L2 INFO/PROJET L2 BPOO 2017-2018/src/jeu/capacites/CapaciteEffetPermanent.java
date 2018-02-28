package jeu.capacites;

import jeu.HearthstoneException;

public class CapaciteEffetPermanent extends Capacite {

	private int bonusAttaque;
	private int bonusVie;

	public CapaciteEffetPermanent(String nom, String description, int bonusAttaque, int bonusVie) {
		super(nom, description);
		this.bonusAttaque = bonusAttaque;
		this.bonusVie = bonusVie;
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

}
