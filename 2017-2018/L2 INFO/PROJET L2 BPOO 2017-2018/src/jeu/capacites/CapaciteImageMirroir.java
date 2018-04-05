package jeu.capacites;

import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteImageMirroir extends Capacite {

	public CapaciteImageMirroir() {
		super("Image mirroir", "Invoque 2 serviteurs 0/2");
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
		IPlateau board = Plateau.getInstance();
		IJoueur	joueur = board.getJoueurCourant();

		try {
			Serviteur	serviteur1 = new Serviteur(joueur, "Serviteur de Jaina", 0, 0, 2, new CapaciteProvocation());
			Serviteur serviteur2 = (Serviteur) serviteur1.clone();
			joueur.getJeu().add(serviteur1);
			joueur.getJeu().add(serviteur2);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
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
