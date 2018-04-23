package core;

import java.util.ArrayList;

public class Residence {

	private	String	nom;
	private	ArrayList<Hebergement>	listeHebergement = new ArrayList<>();
	
	public Residence(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

	public boolean add(Hebergement e) {
		return listeHebergement.add(e);
	}

	public ArrayList<Hebergement> getListeHebergement() {
		return listeHebergement;
	}
	
	public int getCapaciteTotale() {
		int	s = 0;
		
		for (Hebergement hebergement : listeHebergement) {
			s += hebergement.getCapacite();
		}
		
		return s;
	}
	
	public ArrayList<Hebergement>	getDisponibilites(int nbPersonnes, int nbNuits, double prixMax) {
		ArrayList<Hebergement>	dispo = new ArrayList<>();
		
		for (Hebergement hebergement : listeHebergement) {
			if (hebergement.isDisponible() && hebergement.getCapacite()>=nbPersonnes && prixMax <= nbNuits*hebergement.getTarifNuit())
				dispo.add(hebergement);
		}
		
		return dispo;
	}
}
