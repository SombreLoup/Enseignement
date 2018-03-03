package jeu.capacites;


import jeu.HearthstoneException;

public  abstract class CapaciteAttaque extends Capacite {

	protected	int attaque;
	
	public CapaciteAttaque(String nom, String description, int attaque) {
		super(nom,description);
		this.attaque = attaque;
	}
	


	@Override
	public void executerEffetDebutTour() throws HearthstoneException {
		nbUtilisation = 0;
	}

	@Override
	public void executerEffetFinTour() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void executerEffetMiseEnJeu() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void executerEffetDisparition() throws HearthstoneException {
		// TODO Auto-generated method stub
		
	}


}
