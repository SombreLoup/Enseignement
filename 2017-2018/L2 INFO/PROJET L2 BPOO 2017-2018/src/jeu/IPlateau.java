package jeu;

public interface IPlateau {
	void ajouterJoueur(IJoueur joueur) throws HearthstoneException;
	void demarrerPartie() throws HearthstoneException;
	void finTour(IJoueur joueur) throws HearthstoneException;
	IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException;
	void terminerPartie(Joueur joueur);
	boolean estDemarree();
}