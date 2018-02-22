package jeu;

public interface ICarte extends Cloneable {
	void executerEffetDebutTour();
	void executerEffetFinTour();
	void executerEffetDebutMiseEnJeu();
	void executerEffetDisparition();
	void executerAction(Object cible) throws HearthstoneException;
	boolean disparait();
	String getNom();
	int getCout();
}
