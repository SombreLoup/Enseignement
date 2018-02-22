package jeu.capacites;

import jeu.ICapacite;

public abstract class Capacite implements ICapacite {
	private	String nom;
	private	String description;
	protected	int	nbUtilisation = 0;


	public Capacite(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Capacite [nom=" + nom + ", description=" + description + "]";
	}
}
