package jeu;

/**
 * Le plateau gère toute la partie, notamment le début et la fin de partie. Il sait qui est le joueur
 * courant, qui est l'adversaire d'un joueur, etc. Il n'y a qu'un seul plateau en tout et pour tout. 
 * Je vous conseille de faire quelques recherche sur le design pattern 'Singleton'... 
 */
public interface IPlateau {
	/**
	 * Ajoute un joueur à la partie. 
	 * @param joueur Le joueur à ajouter
	 * @throws HearthstoneException si l'on essaie d'ajouter un 3e joueur par exemple, si le joueur est null, etc...
	 */
	void ajouterJoueur(IJoueur joueur) throws HearthstoneException;
	
	/**
	 * Renvoie le joueur courant, c'est-à-dire celui qui a le tour et qui doit jouer
	 * @return le joueur courant, ou null si la partie n'est pas démarée
	 */
	IJoueur getJoueurCourant();
	
	/**
	 * Le setter qui va avec le getter
	 * @param joueur Le nouveau joueur courant
	 * @throws HearthstoneException si le nouveau joueur est null, etc. (faut tout tester naturellement...)
	 */
	void setJoueurCourant(IJoueur joueur) throws HearthstoneException;
	
	/**
	 * Renvoie....l'adversaire
	 * @param joueur dont on veut l'adversaire
	 * @return le joueur adverse
	 * @throws HearthstoneException si le param est null ou si le param ne correspond à aucun des 2 joueurs de la partie, etc.
	 */
	IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException;

	/**
	 * Ca démarre la partie. Il faut déterminer aléatoirement le joueur qui commence, etc.
	 * @throws HearthstoneException si 2 joueurs ne sont pas ajoutés, etc....
	 */
	void demarrerPartie() throws HearthstoneException;
	
	/**
	 * à votre avis ?
	 * @return vrai si la partie a démarée, faux sinon
	 */
	boolean estDemarree();
	
	/**
	 * Le joueur passé en paramètre vient de décider de finir son tour. Du coup, le plateau
	 * doit gérer le changement de joueur courant (entre autres)
	 * @param joueur le joueur qui a fini son tour et qui passe la main
	 * @throws HearthstoneException si le joueur qui passe son tour, n'avait pas le tour, alors
	 * c'est qu'il fait n'importe quoi !
	 */
	void finTour(IJoueur joueur) throws HearthstoneException;
	
	/**
	 * Le héros du joueur adverse du joueur passé en paramètre est mort. Donc le joueur à gagné !
	 * @param joueur celui qui a gagné
	 * @throws HearthstoneException au cas on souhaite...tricher !
	 */
	void gagnePartie(IJoueur joueur) throws HearthstoneException;
}