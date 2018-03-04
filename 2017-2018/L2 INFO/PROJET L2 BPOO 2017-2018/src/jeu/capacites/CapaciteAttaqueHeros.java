package jeu.capacites;

import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;


public class CapaciteAttaqueHeros extends CapaciteAttaque {
	
	
	public CapaciteAttaqueHeros(String nom, String description, int attaque) {
		super(nom, description, attaque);
	}
	
	@Override
	public void executerEffetMiseEnJeu(Object cible) throws HearthstoneException {
		executerAction(cible);
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (nbUtilisation==1)
			throw new HearthstoneException("Pas possible, t'as déjà craché ton sort, man !");
		
		nbUtilisation++;
		
		IPlateau board = Plateau.getInstance();
		IJoueur joueur = board.getJoueurCourant();
		IJoueur joueurAdverse = board.getAdversaire(joueur);
			
		joueurAdverse.getHeros().diminuerVie(attaque);
		if (joueurAdverse.getHeros().estMort())
			board.gagnePartie(joueur);
	}


}
