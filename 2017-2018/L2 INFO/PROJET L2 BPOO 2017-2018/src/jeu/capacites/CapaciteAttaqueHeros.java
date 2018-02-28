package jeu.capacites;

import jeu.HearthstoneException;
import jeu.Joueur;
import jeu.Plateau;


public class CapaciteAttaqueHeros extends Capacite {
	
	private int attaque = 2;
	
	public CapaciteAttaqueHeros(String nom, String description, int attaque) {
		super(nom, description);
		this.attaque = attaque;
	}
	
	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		nbUtilisation=0;
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
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
				Plateau.getInstance().gagnePartie(joueur);
			
			return;
		}
				
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}

}
