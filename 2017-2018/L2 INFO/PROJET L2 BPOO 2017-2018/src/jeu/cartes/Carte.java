package jeu.cartes;

public class Carte {
	private	String	nom;
	private	int		cout;

	public Carte(String nom, int cout) {
		this.nom = nom;
		this.cout = cout;
	}

	public String getNom() {
		return nom;
	}
	
	public int getCout() {
		return cout;
	}

	@Override
	public String toString() {
		return "Carte [nom=" + nom + ", cout=" + cout + "]";
	}
}
