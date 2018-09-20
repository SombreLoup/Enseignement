package model;

import java.util.ArrayList;

public class Classe {
	
	private	String				nom;
	private	ArrayList<Champ>	listeChamps = new ArrayList<Champ>();
	
	public Classe() {
		super();
		listeChamps.add(new Champ("public", "Truc", "int"));
	}

	public Classe(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int size() {
		return listeChamps.size();
	}

	public boolean add(Champ e) {
		return listeChamps.add(e);
	}
	
	

	public Object[] toArray() {
		return listeChamps.toArray();
	}

	@Override
	public String toString() {
		return "Classe [nom=" + nom + ", listeChamps=" + listeChamps + "]";
	}

}
