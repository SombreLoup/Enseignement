package jeu.capacites;


import jeu.HearthstoneException;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteAttaqueCible extends Capacite {

	private	int attaque;
	
	public CapaciteAttaqueCible(String nom, String description, int attaque) {
		super(nom,description);
		this.attaque = attaque;
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
		
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur)cible;
			
			try {
				serviteur.diminuerVie(attaque);
				if (serviteur.disparait())
					serviteur.getProprietaire().perdreCarte(serviteur);				
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
			
			return;
		}		
		
		nbUtilisation--;

		throw new HearthstoneException("La cible est inconnue...");
	}

	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		nbUtilisation = 0;
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}


}
