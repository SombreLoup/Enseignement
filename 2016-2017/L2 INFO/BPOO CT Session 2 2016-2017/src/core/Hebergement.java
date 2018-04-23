package core;

public abstract class Hebergement {
	private	int		identifiant;
	private	String	designation;
	private	int		nombreOccupant;
	
	public Hebergement(int identifiant, String designation) throws HebergementException {
		this.setDesignation(designation);
		this.setIdentifiant(identifiant);
	}

	public String getDesignation() {
		return designation;
	}

	private void setDesignation(String designation) throws HebergementException {
		if (designation == null || designation.trim().equals(""))
			throw new HebergementException("Désignation incorrecte");
		this.designation = designation;
	}

	public int getNombreOccupant() {
		return nombreOccupant;
	}

	public void setNombreOccupant(int nombreOccupant) throws HebergementException {
		if (nombreOccupant>getCapacite())
			throw new HebergementException("Le nombre d'occupant est supérieur à la capacité de l'hébergement");
		this.nombreOccupant = nombreOccupant;
	}

	public boolean isDisponible() {
		return nombreOccupant==0;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	private void setIdentifiant(int identifiant) throws HebergementException {
		if (identifiant<100 || identifiant>999)
			throw new HebergementException("Identifiant incorrect");
		this.identifiant = identifiant;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hebergement other = (Hebergement) obj;
		if (identifiant != other.identifiant)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hebergement [identifiant=" + identifiant + ", designation=" + designation + ", disponible=" + isDisponible()
				+ "]";
	}
	
	
	public abstract double 	getTarifNuit();
	public abstract int		getCapacite();
	
	
}
