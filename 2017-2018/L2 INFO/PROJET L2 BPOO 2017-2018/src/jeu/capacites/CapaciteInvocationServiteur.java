package jeu.capacites;

import jeu.HearthstoneException;
import jeu.cartes.Serviteur;

public class CapaciteInvocationServiteur extends Capacite {

	private	Serviteur serviteurInvoque;
	
	public CapaciteInvocationServiteur(String nom, String description, Serviteur serviteur) {
		super(nom, description);
		this.serviteurInvoque = serviteur;
	}

	public Serviteur getServiteurInvoque() {
		return serviteurInvoque;
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
