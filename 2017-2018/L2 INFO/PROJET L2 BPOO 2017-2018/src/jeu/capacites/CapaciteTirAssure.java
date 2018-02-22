package jeu.capacites;

import jeu.HearthstoneException;
import jeu.Joueur;
import jeu.Plateau;


public class CapaciteTirAssure extends Capacite {
	
	private int attaque = 2;
	
	public CapaciteTirAssure() {
		super("Tir assuré", "Inflige 2 points de dégat au héros adverse");
	}
	
	@Override
	public void executerEffetDebutTour() {
		nbUtilisation=0;
	}

	@Override
	public void executerEffetFinTour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (nbUtilisation==1)
			throw new HearthstoneException("Pas possible, t'as déjà craché ton sort, man !");
		
		nbUtilisation++;
		
		if (cible instanceof Joueur) {
			Joueur joueur = (Joueur)cible;
			
			joueur.getHeros().diminuerVie(attaque);
			if (joueur.getHeros().estMort())
				Plateau.getInstance().terminerPartie(joueur);
			
			return;
		}
				
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}

}
