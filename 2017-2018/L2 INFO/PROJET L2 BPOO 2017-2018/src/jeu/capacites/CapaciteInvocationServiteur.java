package jeu.capacites;

import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;
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
		
	}

	@Override
	public void executerEffetMiseEnJeu() throws HearthstoneException {
		IPlateau board = Plateau.getInstance();
		IJoueur	joueur = board.getJoueurCourant();

		try {
			Serviteur clone = (Serviteur) serviteurInvoque.clone();
			clone.setProprietaire(joueur);
			joueur.getJeu().add(clone);
			clone.executerEffetDebutMiseEnJeu(null);

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executerEffetDisparition() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

}
