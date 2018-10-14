package modele;

import java.util.ArrayList;

import modele.plateau.Plateau;

public class PartieCandyCrush {
	
	private	ArrayList<Plateau>	listePlateaux = new ArrayList<>();
	public 	int					score = 0;
	public	int					numeroPlateauCourant = 0;
	
	public PartieCandyCrush() {
	}

	public int getScore() {
		return score;
	}

	public void comptabiliser(int points) {
		score += points;
	}

	public int size() {
		return listePlateaux.size();
	}

	public Plateau get(int index) {
		return listePlateaux.get(index);
	}

	public boolean add(Plateau e) {
		return listePlateaux.add(e);
	}

	public int getNumeroPlateauCourant() {
		return numeroPlateauCourant;
	}

	public void setNumeroPlateauCourant(int numeroPlateauCourant) {
		this.numeroPlateauCourant = numeroPlateauCourant;
	}
	
	public Plateau getPlateauCourant() {
		return listePlateaux.get(numeroPlateauCourant);
	}
	
	public void changerPlateau() {
		numeroPlateauCourant++;
	}
}
