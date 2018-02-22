package jeu;

public interface ICapacite {
	void executerEffetDebutTour();
	void executerEffetFinTour();
	void executerAction(Object cible) throws HearthstoneException;
	String getNom();
	String getDescription();

}
