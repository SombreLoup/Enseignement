package plateau;

import java.util.ArrayList;

public abstract class Superviseur {
	private	ArrayList<Case>		plateau = new ArrayList<Case>();
	private	ArrayList<Joueur>	lesJoueurs = new ArrayList<Joueur>();
	private	Joueur				joueurCourant;
	
	public boolean add(Joueur joueur) {
		if (joueur==null)
			throw new IllegalArgumentException("Le joueur est null");
		if (lesJoueurs.contains(joueur))
			throw new IllegalArgumentException("Le joueur est déjà dans la partie");
		
		return lesJoueurs.add(joueur);
	}

	public Case getCase(int index) {
		return plateau.get(index);
	}
	
	public int getIndiceCase(Case c) {
		return plateau.indexOf(c);
	}

	public void add(int index, Case element) {
		plateau.add(index, element);
	}

	public int taillePlateau() {
		return plateau.size();
	}
	
	public int nombreJoueurs() {
		return lesJoueurs.size();
	}
	
	public Joueur getJoueur(int i) {
		if (i<0 || i>lesJoueurs.size())
			throw new IllegalArgumentException("Indice de joueur incorrect");
		
		return lesJoueurs.get(i);
	}

	public abstract	void	deplacerJoueur(Joueur joueur, int deplacement);	
	public abstract void	initialiserPartie();

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

}
