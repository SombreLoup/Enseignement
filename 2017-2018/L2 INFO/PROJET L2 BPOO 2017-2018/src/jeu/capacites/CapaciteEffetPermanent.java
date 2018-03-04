package jeu.capacites;

import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.cartes.Serviteur;

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
		IPlateau board = Plateau.getInstance();
		IJoueur	joueur = board.getJoueurCourant();
		
		for (ICarte carte : joueur.getJeu()) {
			if (carte instanceof Serviteur) {
				Serviteur serviteur = (Serviteur)carte;
				serviteur.ajouterBonusVie(bonusVie);
				serviteur.ajouterBonusAttaque(bonusAttaque);
			}
		}
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		IPlateau board = Plateau.getInstance();
		IJoueur	joueur = board.getJoueurCourant();
		
		for (ICarte carte : joueur.getJeu()) {
			if (carte instanceof Serviteur) {
				Serviteur serviteur = (Serviteur)carte;
				serviteur.ajouterBonusVie(-bonusVie);
				serviteur.ajouterBonusAttaque(-bonusAttaque);
			}
		}
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
	}

	@Override
	public void executerEffetDisparition() throws HearthstoneException {
	}

}
