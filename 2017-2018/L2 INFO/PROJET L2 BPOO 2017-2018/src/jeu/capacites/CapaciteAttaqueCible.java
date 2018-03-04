package jeu.capacites;


import jeu.HearthstoneCibleNullException;
import jeu.HearthstoneException;
import jeu.Joueur;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteAttaqueCible extends CapaciteAttaque {

	
	public CapaciteAttaqueCible(String nom, String description, int attaque) {
		super(nom,description, attaque);
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (cible == null)
			throw new HearthstoneCibleNullException("Cette attaque nécessite une cible");

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
}
