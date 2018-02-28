package jeu;

/**
 * Une carte, ben c'est une carte du jeu quoi ! Une carte peut être
 * une carte de sort ou une carte serviteur (on ne s'occupe pas des autres sortes de cartes).
 * @see IJoueur
 */
public interface ICarte extends Cloneable {
	
	String getNom();				// Une carte doit avoir un nom
	int getCout();				// Une carte coûte un crtain nombre de mana
	IJoueur getProprietaire();	// Une carte appartient à un joueur
	
	/**
	 * Une carte peut avoir un effet au début de chaque tour où elle est en jeu
	 * @param cible ce parametre peut être null si la carte n'a pas besoin d'une cible pour l'effet en question. 
	 * La cible peut être égale à n'importe quoi d'autre qui arrange la carte (un héros, un serviteur, une autre carte...) 
	 * @throws HearthstoneException
	 */
	void executerEffetDebutTour(Object cible) throws HearthstoneException;

	/**
	 * Une carte peut avoir un effet à la fin d'un chaque tour où elle est en jeu
	 * @param cible ce parametre peut être null si la carte n'a pas besoin d'une cible pour l'effet en question.
	 * La cible peut être égale à n'importe quoi d'autre qui arrange la carte (un héros, un serviteur, une autre carte...) 
	 * @throws HearthstoneException
	 */
	void executerEffetFinTour(Object cible) throws HearthstoneException;
	
	/**
	 * Une carte peut avoir un effet au début de sa mise en jeu 
	 * @param cible ce parametre peut être null si la carte n'a pas besoin d'une cible pour l'effet en question. 
	 * La cible peut être égale à n'importe quoi d'autre qui arrange la carte (un héros, un serviteur, une autre carte...)	 * @throws HearthstoneException
	 */
	void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException;
	
	/**
	 * Une carte peut avoir un effet au moment de sa disparition du jeu 
	 * @param cible ce parametre peut être null si la carte n'a pas besoin d'une cible pour l'effet en question. 
	 * La cible peut être égale à n'importe quoi d'autre qui arrange la carte (un héros, un serviteur, une autre carte...)	 * @throws HearthstoneException
	 * @throws HearthstoneException
	 */
	void executerEffetDisparition(Object cible) throws HearthstoneException;
	
	/**
	 * Une carte peut avoir une action qui se commande à n'importe quel moment du tour lorsqu'elle est en jeu 
	 * @param cible ce parametre peut être null si la carte n'a pas besoin d'une cible pour l'action en question. 
	 * La cible peut être égale à n'importe quoi d'autre qui arrange la carte (un héros, un serviteur, une autre carte...)	 * @throws HearthstoneException
	 * @throws HearthstoneException
	 */
	void executerAction(Object cible) throws HearthstoneException;
	
	/**
	 * Fonction qui teste si les conditions pour que la carte soit encore présente au tour suivant. Si la fonction
	 * renvoie vrai, il faut sûrement la retirer du board...
	 * @return true si la carte est foutu (un serviteur tué, un sort lancé, etc.)
	 */
	boolean disparait();
}
