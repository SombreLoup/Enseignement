package jeu.capacites;


import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteAttaqueTousLesServiteurs extends CapaciteAttaque {

	private	int attaque;
	
	public CapaciteAttaqueTousLesServiteurs(String nom, String description, int attaque) {
		super(nom,description, attaque);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (nbUtilisation==1)
			throw new HearthstoneException("Pas possible, t'as déjà craché ton sort, man !");
		
		nbUtilisation++;
		
		IPlateau	 board = Plateau.getInstance();
		IJoueur joueurAdverse = board.getAdversaire(board.getJoueurCourant());
		
		for (ICarte carte : joueurAdverse.getJeu()) {
			if (carte instanceof Serviteur) {
				Serviteur serviteur = (Serviteur)carte;
				serviteur.diminuerVie(attaque);
				if (serviteur.disparait())
					serviteur.getProprietaire().perdreCarte(serviteur);
			}
		}
		
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}
}
