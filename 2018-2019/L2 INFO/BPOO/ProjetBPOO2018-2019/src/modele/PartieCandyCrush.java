package modele;

import java.util.ArrayList;

import modele.plateau.Plateau;

public class PartieCandyCrush {
	
	private	ArrayList<Plateau>	listePlateaux = new ArrayList<>();
	public 	int					score;
	
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
	
	
}
