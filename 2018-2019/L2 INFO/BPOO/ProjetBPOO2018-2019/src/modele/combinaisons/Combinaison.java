package modele.combinaisons;

import modele.Bonbon;
import modele.Plateau;

public abstract class Combinaison {
	private	int	ldepart;
	private	int cdepart;
	private Bonbon bonbon;
	private	int	nombrePoints = 0;
	
	public Combinaison(Bonbon b, int l, int c) {
		this.bonbon = b;
		this.ldepart = l;
		this.cdepart = c;
	}
	
	public abstract Bonbon getBonbonSpecial();
	public abstract void viderCombinaison(Plateau p);
	public abstract boolean contient(Plateau p, int l, int c);

	public int getLdepart() {
		return ldepart;
	}

	public int getCdepart() {
		return cdepart;
	}

	public Bonbon getBonbon() {
		return bonbon;
	}

	public int getNombrePoints() {
		return nombrePoints;
	}

	public void setNombrePoints(int nombrePoints) {
		this.nombrePoints = nombrePoints;
	}
	
	public void comptabiliserNombrePoints(Plateau p) {
		p.comptabiliser(getNombrePoints());
	}

}
