package idee1;

import java.util.ArrayList;

public class Balise {
	private	String	nom;
	
	private	ArrayList<Balise>	baliseImbriquees = new ArrayList<Balise>();
	private ArrayList<Attribut> attributs = new ArrayList<Attribut>();
	
	public Balise(String nom) {
		setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		testerNomVide(nom);
		this.nom = nom;
	}

	public void testerNomVide(String nom) {
		if ((nom==null) || (nom.trim().equals("")))
			throw new IllegalArgumentException("Le nom de la balise ne doit pas être vide");
	}
	
	@Override
	public String toString() {
		String	s = "<"+nom;
		
		if (!attributs.isEmpty())
			for (Attribut attribut : attributs) {
				s += attribut;
			}

		if (baliseImbriquees.isEmpty())
			s += "/>";
		else {
			s += ">\n";
			for (Balise balise : baliseImbriquees) {
				s += "\t"+balise;
			}
			s += "\n</"+nom+">";
		}
		return s;
	}

	public void addBalise(Balise balise) {
		baliseImbriquees.add(balise);
	}
	
	public void addAttribut(Attribut attribut) {
		attributs.add(attribut);
	}
}
