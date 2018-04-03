package Activite;

public class Randonnee implements Activite {

	public final static int	FACILE = 0;
	public final static int	MOYENNE = 1;
	public final static int	DIFFICILE = 2;
	
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
			return distance/5;
		case MOYENNE:
			return 0.2+(distance-1)/6;
		case DIFFICILE:
			return 0.5+distance/8;
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
		return "Randonnee [distance=" + distance + ", difficulte=" + difficulte+ ", duree=" + getDuree() + "]";
	}
}
