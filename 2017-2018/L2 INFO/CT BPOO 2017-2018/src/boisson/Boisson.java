package boisson;

public abstract class Boisson {

	public enum ETemperature {AMBIANTE, FRAICHE, FRAPPEE};
	
	private	String	nom;
	private	ETemperature temperature;
	
	
	public abstract	double	getPrix();
	public abstract 	boolean	estAlcoolisee();
	
	
	public Boisson(String nom, ETemperature temperature) throws BoissonException {
		setNom(nom);
		setTemperature(temperature);
	}


	private void setTemperature(ETemperature temperature) {
		// Avec un enum, pas d'erreur possible
		this.temperature = temperature;
	}


	private void setNom(String nom) throws BoissonException {
		if ((nom==null) || (nom.trim().equals(""))) 
			throw new BoissonException("Le nom de boisson est incorrect");
		
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public ETemperature getTemperature() {
		return temperature;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boisson other = (Boisson) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Boisson [nom=" + nom + ", temperature=" + temperature + "]";
	}
	
	
}
