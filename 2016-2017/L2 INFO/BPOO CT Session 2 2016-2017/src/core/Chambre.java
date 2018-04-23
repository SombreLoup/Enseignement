package core;

public class Chambre extends Hebergement {
	
	private	int		nombrePlace;
	private	double	tarifNuit;
	

	public Chambre(int identifiant, String designation, int nombrePlace, double tarifNuit) throws HebergementException {
		super(identifiant, designation);
		this.nombrePlace = nombrePlace;
		this.tarifNuit = tarifNuit;
	}

	@Override
	public double getTarifNuit() {
		return tarifNuit;
	}

	@Override
	public int getCapacite() {
		return nombrePlace;
	}

	@Override
	public String toString() {
		return "Chambre ["+super.toString()+", nombrePlace=" + nombrePlace + ", tarifNuit=" + tarifNuit + "]";
	}
}
