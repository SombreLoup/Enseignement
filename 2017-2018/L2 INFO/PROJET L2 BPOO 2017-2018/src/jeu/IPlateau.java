package jeu;

/**
 * Le plateau gère toute la partie, notamment le début et la fin de partie. Il sait qui est le joueur
 * courant, qui est l'adversaire d'un joueur, etc. Il n'y a qu'un seul plateau en tout et pour tout. 
 * Je vous conseille de faire quelques recherche sur le design pattern 'Singleton'... 
 */
public interface IPlateau {
	void ajouterJoueur(IJoueur joueur) throws HearthstoneException;
	IJoueur getJoueurCourant();
	void setJoueurCourant(IJoueur joueur) throws HearthstoneException;
	IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException;
	void demarrerPartie() throws HearthstoneException;
	boolean estDemarree();
	void finTour(IJoueur joueur) throws HearthstoneException;
	void gagnePartie(Joueur joueur) throws HearthstoneException;
}