package jeu.cartes;

import jeu.HearthstoneCibleNullException;
import jeu.HearthstoneException;
import jeu.IJoueur;
import jeu.capacites.Capacite;

public class Sort extends Carte {
	
	private	Capacite		capacite;

	public Sort(IJoueur proprietaire, String nom, int cout, Capacite capacite) {
		super(proprietaire, nom, cout);
		this.capacite = capacite;
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		if (cible == null)
			throw new HearthstoneCibleNullException("Un sort nécessite une cible");
		
		capacite.executerAction(cible);
		this.getProprietaire().perdreCarte(this);
	}

	public Capacite getCapacite() {
		return capacite;
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean disparait() {
		// TODO Auto-generated method stub
		return false;
	}

}
