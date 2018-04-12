package starcraft;

import java.util.ArrayList;

import unite.Unite;

public class Batiment {
	private String nom;
	private ArrayList<Unite> unites;
	
	public Batiment(String nom) {
		setNom(nom);
		unites = new ArrayList<Unite>();
	}
	
	public Unite produire(Class<?> classe) throws UniteInterditeException {
		for (Unite unite : unites) {
			if ( unite.getClass() == classe )
				return unite.clone();
		}
		
		throw new UniteInterditeException("Le bâtiment "+nom+" ne peut pas produire l'unité "+classe.getName());
	}
	
	public void add(Unite unite) {
		if ( !unites.contains(unite) )
			unites.add(unite);
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		if (nom == null || nom.trim().equals("")) throw new IllegalArgumentException("Nom du batiment vide...");
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		String s = "Batiment[nom="+nom+"]\n-------------------------";
		for (Unite unite : unites) {
			s += "Unité : "+unite.getClass().getName()+"\n";
		}
		s+="\n";

		return s;
	}
}
