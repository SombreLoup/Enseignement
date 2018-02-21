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
		estEnCours = true;
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

	@Override
	public void terminerPartie(Joueur joueur) {
		estEnCours = false;
		String s = "* "+joueur.getPseudo()+" a gagné ! *";
		String stars = "";
		for (int i = 0; i < s.length(); i++) 
			stars += "*";
		
		System.out.println(stars);
		System.out.println(s);
		System.out.println(stars);
	}

	@Override
	public boolean estDemarree() {
		return estEnCours;
	}
	
	@Override
	public String toString() {
		String	s = "";
		
		s += "**************************************************\n";
		if (joueurCourant==joueurs.get(0))
			s += ">>>> TOUR <<<<\n";
		
		s += joueurs.get(0);
		s += "\n";
		
		s += "==================================\n";
		for (ICarte carte : joueurs.get(0).getJeu()) {
			s += carte;
		}
		s += "==================================\n";
		s += "----------------------------------\n";
		s += "==================================\n";
		for (ICarte carte : joueurs.get(1).getJeu()) {
			s += carte;
		}
		s += "==================================\n";
		if (joueurCourant==joueurs.get(1))
			s += ">>>> TOUR <<<<\n";
		
		s += joueurs.get(1);
		s += "**************************************************\n";
		
		return s;
	}
}
