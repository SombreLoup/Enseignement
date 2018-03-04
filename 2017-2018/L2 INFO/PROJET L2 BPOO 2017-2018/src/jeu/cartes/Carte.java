package jeu.cartes;

import jeu.ICarte;
import jeu.IJoueur;

public abstract class Carte implements ICarte {
	private	String	nom;
	private	int		cout;
	private	IJoueur	proprietaire;

	public Carte(IJoueur proprietaire, String nom, int cout) {
		this.proprietaire = proprietaire;
		this.nom = nom;
		this.cout = cout;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getCout() {
		return cout;
	}
	
	public IJoueur getProprietaire() {
		return proprietaire;
	}
	
	public void setProprietaire(IJoueur proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "Carte [nom=" + nom + ", cout=" + cout + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cout;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carte other = (Carte) obj;
		if (cout != other.cout)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
