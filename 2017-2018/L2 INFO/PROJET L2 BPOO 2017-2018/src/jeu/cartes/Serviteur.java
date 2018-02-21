package jeu.cartes;

import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.Joueur;
import jeu.Plateau;

public class Serviteur extends Carte {
	private	int	attaque;
	private	int vie;
	
	
	public Serviteur(IJoueur proprietaire, String nom, int cout, int attaque, int vie) {
		super(proprietaire, nom, cout);
		this.attaque = attaque;
		this.vie = vie;
	}

	@Override
	public void executerEffetDebutTour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetFinTour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerAction(Object cible) {
		if (cible instanceof Joueur) {
			Joueur joueur = (Joueur)cible;
			
			joueur.getHeros().diminuerVie(attaque);
			if (joueur.getHeros().estMort())
				Plateau.getInstance().terminerPartie(joueur);
			
			return;
		}
			
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur)cible;
			
			try {
				serviteur.vie -= attaque;
				if (serviteur.disparait())
					serviteur.getProprietaire().perdre(serviteur);
				
				this.vie -= serviteur.attaque;
				if (this.disparait())
					this.getProprietaire().perdre(this);
				
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean disparait() {
		return (vie<=0);
	}

}
