package jeu;

import java.util.ArrayList;

import jeu.cartes.Carte;

public class Heros implements Cloneable {
	/******
	 * Gestion de la table contenant tous les héros possibles
	 */
	private	static	ArrayList<Heros>		listeDesHeros = new ArrayList<Heros>();
	
	public static	Heros	getHeros(String nom) throws ExceptionHearthstone {
		if (listeDesHeros.size()==0)
			initialiserLesHeros();
		
		for (Heros heros : listeDesHeros) {
			if (heros.nom.contains(nom))
				try {
					return (Heros) heros.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
		}
		
		throw new ExceptionHearthstone("Le héros demandé ("+nom+") n'existe pas");
	}
	
	
	public static void afficherListe() {
		if (listeDesHeros.size()==0)
			initialiserLesHeros();
		
		for (Heros heros : listeDesHeros) {
			System.out.println(heros);
		}
	}


	private static void initialiserLesHeros() {
		Carte c;

		Heros jaina = new Heros("Jaina Portvaillant", 30);
		listeDesHeros.add(jaina);
			
		listeDesHeros.add(new Heros("Rexxar", 30));
		listeDesHeros.add(new Heros("Uther le Porteur de Lumière", 30));
		listeDesHeros.add(new Heros("Garrosh Hurlenfer", 30));
		listeDesHeros.add(new Heros("Malfurion Hurlorage", 30));
		listeDesHeros.add(new Heros("Gul'dan", 30));
		listeDesHeros.add(new Heros("Thrall", 30));
		listeDesHeros.add(new Heros("Anduin Wrynn", 30));
		listeDesHeros.add(new Heros("Valeera Sanguinar", 30));
	}


	/*****
	 * Un héros
	 */
	private		String				nom;
	private		int					pointsDeVieCourant;
	private		int					pointsDeVieMax;
	
	private Heros(String nom, int pointsDeVieMax) {
		this.nom = nom;
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieCourant = pointsDeVieMax;
	}
	
	@SuppressWarnings("unchecked")
	private	Heros(Heros heros) {
		this.nom = heros.nom;
		this.pointsDeVieMax = heros.pointsDeVieMax;
		this.pointsDeVieCourant = heros.pointsDeVieCourant;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Heros(this);
	}

	public int getPointsDeVieCourant() {
		return pointsDeVieCourant;
	}

	public void setPointsDeVieCourant(int pointsDeVieCourant) {
		this.pointsDeVieCourant = pointsDeVieCourant;
	}

	public String getNom() {
		return nom;
	}

	public int getPointsDeVieMax() {
		return pointsDeVieMax;
	}
	
	

	@Override
	public String toString() {
		return "Heros [nom=" + nom + ", pointsDeVieCourant=" + pointsDeVieCourant + ", pointsDeVieMax=" + pointsDeVieMax
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + pointsDeVieCourant;
		result = prime * result + pointsDeVieMax;
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
		Heros other = (Heros) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pointsDeVieCourant != other.pointsDeVieCourant)
			return false;
		if (pointsDeVieMax != other.pointsDeVieMax)
			return false;
		return true;
	}
}
