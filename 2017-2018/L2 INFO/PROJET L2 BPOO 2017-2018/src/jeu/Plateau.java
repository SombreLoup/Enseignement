package jeu;

import java.util.ArrayList;

public class Plateau implements IPlateau {
	/******
	 * Singleton
	 */
	private	static IPlateau	plateau = null;
	
	public static IPlateau getInstance() {
		if (plateau==null)
			plateau = new Plateau();
		return plateau;
	}
	
	/*****
	 * L'objet Plateau
	 */
	private	IJoueur	joueurCourant = null;

	private	Plateau() {
		estEnCours = false;
	}
	
	private boolean estEnCours;
	private	ArrayList<IJoueur>	joueurs = new ArrayList<IJoueur>();

	/* (non-Javadoc)
	 * @see jeu.IPlateau#getAdversaire(jeu.IJoueur)
	 */
	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (! estEnCours)
			throw new HearthstoneException("La partie n'est pas encore démarrée....");
		
		if (joueurs.get(0)==joueur)
			return joueurs.get(1);
	
		return joueurs.get(0);
	}

	@Override
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (estEnCours)
			throw new HearthstoneException("La partie est déjà démarrée....");
		if (joueurs.size()==2)
			throw new HearthstoneException("La partie complète....");
		if (joueur==null)
			throw new HearthstoneException("Pas de joueur null dans la partie....");
		if (joueurs.contains(joueur))
			throw new HearthstoneException("T'es déjà dans la partie ! Ectoplasme !....");

		joueurs.add(joueur);
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
		if (estEnCours)
			throw new HearthstoneException("La partie est déjà démarrée....");
		if (joueurs.size()!=2)
			throw new HearthstoneException("La partie n'est pas complète....");
		int num = (int)(Math.random()+0.5);
		joueurCourant = joueurs.get(num);
		joueurCourant.prendreTour();
	}

	/* (non-Javadoc)
	 * @see jeu.IPlateau#finTour(jeu.IJoueur)
	 */
	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException {
		if (! estEnCours)
			throw new HearthstoneException("La partie n'est pas encore démarrée....");
		if (joueurCourant != joueur)
			throw new HearthstoneException("C'est pas ton tour, blaireau !");
		
		IJoueur	adversaire = getAdversaire(joueur);
		adversaire.prendreTour();
	}
	

}
