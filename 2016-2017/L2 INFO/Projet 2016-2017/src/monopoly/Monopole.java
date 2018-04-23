package monopoly;

import java.util.ArrayList;

import plateau.Joueur;

public class Monopole {
	private	ArrayList<Terrain>	listeTerrain = new ArrayList<>();
	private String	nom;
	
	public boolean estComplet() {
		Joueur prop = get(0).getProprietaire();
		
		for (int i=0; i<size(); i++)
			if (get(i).getProprietaire() != prop)
				return false;
		return true;
	}
	
	public Monopole(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public int size() {
		return listeTerrain.size();
	}

	public Terrain get(int index) {
		return listeTerrain.get(index);
	}

	public Monopole add(Terrain e) {
		listeTerrain.add(e);
		return this;
	}
}
