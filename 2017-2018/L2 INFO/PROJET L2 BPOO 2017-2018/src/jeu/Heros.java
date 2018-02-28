package jeu;

import java.util.ArrayList;

import jeu.capacites.CapaciteAttaqueCible;
import jeu.capacites.CapaciteAttaqueHeros;
import jeu.capacites.CapaciteInvocationServiteur;
import jeu.cartes.Serviteur;

public class Heros implements Cloneable {
	/******
	 * Gestion de la table contenant tous les héros possibles
	 */
	private	static	ArrayList<Heros>		listeDesHeros = new ArrayList<Heros>();
	
	public static	Heros	getHeros(String nom) throws HearthstoneException {
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
		
		throw new HearthstoneException("Le héros demandé ("+nom+") n'existe pas");
	}
	
	
	public static void afficherListe() {
		if (listeDesHeros.size()==0)
			initialiserLesHeros();
		
		for (Heros heros : listeDesHeros) {
			System.out.println(heros);
		}
	}


	private static void initialiserLesHeros() {
		Heros jaina = new Heros("Jaina Portvaillant", 15, new CapaciteAttaqueCible("Boule de feu", "Inflige 1 point de dégat à la cible", 1));
		listeDesHeros.add(jaina);
			
		Heros rexxar = new Heros("Rexxar", 15, new CapaciteAttaqueHeros("Tir assuré", "Inflige 2 points de dégat au héros adverse",2));
		listeDesHeros.add(rexxar);
		
		Heros uther = new Heros("Uther le Porteur de Lumière", 15, new CapaciteInvocationServiteur("Pouvoir héroïque", "Invoque une recrue de la Main d'Argent 1/1", new Serviteur(null,"Recrue de la Main d'Argent",0,1,1,null)));
		listeDesHeros.add(uther);
		
		listeDesHeros.add(new Heros("Garrosh Hurlenfer", 15));
		listeDesHeros.add(new Heros("Malfurion Hurlorage", 15));
		listeDesHeros.add(new Heros("Gul'dan", 15));
		listeDesHeros.add(new Heros("Thrall", 15));
		listeDesHeros.add(new Heros("Anduin Wrynn", 15));
		listeDesHeros.add(new Heros("Valeera Sanguinar", 15));
	}


	/*****
	 * Un héros
	 */
	private		String				nom;
	private		int					pointsDeVieCourant;
	private		int					pointsDeVieMax;
	private		ICapacite			pouvoir;
	
	private Heros(String nom, int pointsDeVieMax) {
		this.nom = nom;
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieCourant = pointsDeVieMax;
		this.pouvoir = null;
	}
	
	private Heros(String nom, int pointsDeVieMax, ICapacite pouvoir) {
		this.nom = nom;
		this.pointsDeVieMax = pointsDeVieMax;
		this.pointsDeVieCourant = pointsDeVieMax;
		this.pouvoir = pouvoir;
	}

	private	Heros(Heros heros) {
		this.nom = heros.nom;
		this.pointsDeVieMax = heros.pointsDeVieMax;
		this.pointsDeVieCourant = heros.pointsDeVieCourant;
		this.pouvoir = heros.pouvoir;
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
	
	public ICapacite getPouvoir() {
		return pouvoir;
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


	public void diminuerVie(int attaque) {
		pointsDeVieCourant -= attaque;
	}


	public boolean estMort() {
		return (pointsDeVieCourant<=0);
	}
}
