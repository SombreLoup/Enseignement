package jeu.capacites;

import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.cartes.Serviteur;

public class CapaciteInvocationChiens extends CapaciteInvocationServiteur {

	public CapaciteInvocationChiens() {
		super("Lachez les chiens", "Invoque autant de chiens que de serviteurs adverses",new Serviteur(null,"Chien",0,1,1,new CapaciteCharge()));
	}
	
	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		IPlateau board = Plateau.getInstance();
		IJoueur	joueur = board.getJoueurCourant();
		IJoueur	joueurAdverse = board.getAdversaire(joueur);

		try {
			int nbServiteursAdverses = 0;
			for (ICarte carte : joueurAdverse.getJeu()) {
				if (carte instanceof Serviteur)
					nbServiteursAdverses++;
			}
			
			for (int i = 0; i < nbServiteursAdverses; i++) {
				Serviteur clone = (Serviteur) getServiteurInvoque().clone();
				clone.setProprietaire(joueur);
				clone.setNom(clone.getNom()+" "+(i+1));
				joueur.getJeu().add(clone);
			}
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	
}
