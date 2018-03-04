package jeu.capacites;


import java.util.ArrayList;

import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteAttaqueTousLesServiteurs extends CapaciteAttaque {

	
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
		
		ArrayList<ICarte> newJeu = new ArrayList<ICarte>();
		for (ICarte carte : joueurAdverse.getJeu()) {
			if (carte instanceof Serviteur) {
				Serviteur serviteur = (Serviteur)carte;
				serviteur.diminuerVie(attaque);
				if (serviteur.disparait())
					newJeu.add(serviteur);
			}
		}
		for (ICarte carte : newJeu) {
			joueurAdverse.perdreCarte(carte);
		}
	}
}
