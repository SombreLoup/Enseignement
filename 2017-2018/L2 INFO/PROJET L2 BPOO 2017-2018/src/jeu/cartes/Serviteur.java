package jeu.cartes;

import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.Joueur;
import jeu.Plateau;

public class Serviteur extends Carte {
	private	int	attaque;
	private	int vie;
	private	int	tourEnAttente;
	private	int	tourJoue;
	
	
	public Serviteur(IJoueur proprietaire, String nom, int cout, int attaque, int vie) {
		super(proprietaire, nom, cout);
		this.attaque = attaque;
		this.vie = vie;
	}

	@Override
	public void executerEffetDebutTour() {
		tourJoue = 0;
		
	}

	@Override
	public void executerEffetFinTour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerEffetDebutMiseEnJeu() {
		tourEnAttente = 1;
	}

	@Override
	public void executerEffetDisparition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (tourEnAttente != 0) {
			throw new HearthstoneException("Trop pressé fiston ! Tu la joueras au prochain tour...");
		}
		
		if (tourJoue != 0) {
			throw new HearthstoneException("Tu l'as déjà joué ce tour ! Tricheur !");
		}

		tourJoue++;
		
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
			
			return;
		}
		
		tourJoue--;
		
		throw new HearthstoneException("La cible est inconnue, cloporte bulbeux !");
	}

	@Override
	public boolean disparait() {
		return (vie<=0);
	}
	
	@Override
	public String toString() {
		return "Serviteur ["+super.toString()+" attaque=" + attaque + "; vie=" + vie + "]";
	}

}
