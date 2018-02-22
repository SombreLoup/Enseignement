package jeu.capacites;


import jeu.HearthstoneException;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteBouleDeFeu extends Capacite {

	private	int attaque = 1;
	
	public CapaciteBouleDeFeu() {
		super("Boule de feu", "Inflige 1 point de dégat à la cible");
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
		
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur)cible;
			
			try {
				serviteur.diminuerVie(attaque);
				if (serviteur.disparait())
					serviteur.getProprietaire().perdre(serviteur);				
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
			
			return;
		}		
		
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}

	@Override
	public void executerEffetDebutTour() {
		nbUtilisation=0;
	}

	@Override
	public void executerEffetFinTour() {
		// TODO Auto-generated method stub
		
	}

}
