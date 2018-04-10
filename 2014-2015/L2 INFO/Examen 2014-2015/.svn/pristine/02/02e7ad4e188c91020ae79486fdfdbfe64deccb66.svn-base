package core;

import java.util.ArrayList;

public class Vehicule {
	
	private	String	numeroImmatriculation;
	private	int		kilometrage;
	private	ArrayList<Intervention>	listeInterventions = new ArrayList<Intervention>();
	
	public Vehicule(String numeroImmatriculation) {
		setNumeroImmatriculation(numeroImmatriculation);
	}

	public String getNumeroImmatriculation() {
		return numeroImmatriculation;
	}

	public void setNumeroImmatriculation(String numeroImmatriculation) {
		if (!numeroImmatriculation.matches("[A-Z]{2}[0-9]{3}[A-Zaff]{2}"))
			throw new IllegalArgumentException("Numéro d'immatricualtion incorrect");
		this.numeroImmatriculation = numeroImmatriculation;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public boolean add(Intervention e) {
		return listeInterventions.add(e);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicule other = (Vehicule) obj;
		if (numeroImmatriculation == null) {
			if (other.numeroImmatriculation != null)
				return false;
		} else if (!numeroImmatriculation.equals(other.numeroImmatriculation))
			return false;
		return true;
	}
	
	public void afficherCarnetEntretien() {
		System.out.println("Carnet d'entretien du véhicule : "+numeroImmatriculation);
		System.out.println("-------------------------------------------");
		double	coutTotal = 0;
		for (Intervention intervention : listeInterventions) {
			System.out.println(intervention);
			coutTotal += intervention.getMontant();
		}
		System.out.println("-------------------------------------------");
		System.out.println("Cout total : "+coutTotal);
	}

}
