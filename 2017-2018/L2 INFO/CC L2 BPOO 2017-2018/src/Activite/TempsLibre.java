package Activite;

public class TempsLibre implements Activite {

	private double duree;
	
	public TempsLibre(double duree) throws ActiviteException {
		setDuree(duree);
	}
	
	public void setDuree(double duree) throws ActiviteException {
		if (duree>2 || duree<0.5)
			throw new ActiviteException("Durée du temps libre incorrecte");
		
		this.duree = duree;
	}
	

	@Override
	public String toString() {
		return "TempsLibre ["+duree + "h]";
	}

	@Override
	public double getCout() {
		return 0;
	}

	@Override
	public double getDuree() {
		return duree;
	}

	@Override
	public boolean estCulturelle() {
		return false;
	}

	@Override
	public boolean estSportive() {
		return false;
	}

}
