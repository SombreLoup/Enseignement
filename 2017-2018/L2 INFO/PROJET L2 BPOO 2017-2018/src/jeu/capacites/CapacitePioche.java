package jeu.capacites;

import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;

public class CapacitePioche extends Capacite {

	private	int	nombreCartesPiochees;
	
	public CapacitePioche(int nbCartes) {
		super("Pioche", "Pioche des cartes");
		this.nombreCartesPiochees = nbCartes;
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
		IPlateau	 board = Plateau.getInstance();
		IJoueur joueur = board.getJoueurCourant();
		
		for (int i = 0; i < nombreCartesPiochees; i++) {
			joueur.piocher();
		}
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
