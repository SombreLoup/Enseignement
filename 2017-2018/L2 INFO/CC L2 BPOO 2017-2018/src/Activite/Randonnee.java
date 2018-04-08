package Activite;

public class Randonnee implements Activite {

	public final static int	FACILE = 0;
	public final static int	MOYENNE = 1;
	public final static int	DIFFICILE = 2;
	public final static String[] tabDifficulte = {"Facile", "Moyenne", "Difficile"};
	
	private	double	distance;
	private	int difficulte;
	
	
	
	public Randonnee(double distance, int difficulte) {
		this.distance = distance;
		this.difficulte = difficulte;
	}

	@Override
	public double getCout() {
		return 0;
	}

	@Override
	public double getDuree() {
		switch (difficulte) {
		case FACILE:
			return distance/4;
		case MOYENNE:
			return 0.2+(distance-1)/3;
		case DIFFICILE:
			return 0.5+distance/2;
		}
		return 0;
	}

	@Override
	public boolean estCulturelle() {
		return false;
	}

	@Override
	public boolean estSportive() {
		return true;
	}

	@Override
	public String toString() {
		return "Rando ["+tabDifficulte[difficulte]+ ", " + distance + " kms]";
	}
}
