package Activite;

public class Promenade extends Randonnee {
	
	private	String	description;

	public Promenade(double distance, int difficulte, String description) {
		super(distance, difficulte);
		this.description = description;
	}
	
	@Override
	public boolean estCulturelle() {
		return true;
	}

	@Override
	public String toString() {
		return "Promenade ["+description+" --> "+super.toString()+"]";
	}
}
