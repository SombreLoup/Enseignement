package metier;

public abstract class Intervention {
	
	private	Vehicule	vehicule = null;
	private	int			kilometrage = 0;
	
	public Intervention() {
	}
	
	public Intervention(Intervention intervention) {
		this.vehicule = intervention.vehicule;
		this.kilometrage = intervention.kilometrage;		
	}
	
	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}
	
	@Override
	public String toString() {
		return "Intervention [vehicule=" + vehicule.getImmat() + ", kilometrage=" + kilometrage + "]";
	}

	public abstract double getCout();
	public abstract Intervention clone();

}
