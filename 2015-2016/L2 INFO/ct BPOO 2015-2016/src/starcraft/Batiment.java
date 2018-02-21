package starcraft;

import java.util.ArrayList;

public class Batiment {
	private	String				nom;
	private	ArrayList<Unite>	modeles = new ArrayList<Unite>();
	
	public Batiment(String nom) {
		testerNom(nom);
		this.nom = nom;
	}	
	
	private void testerNom(String string) {
		if ((string==null) || string.trim().isEmpty())
			throw new IllegalArgumentException();
	}

	public String getNom() {
		return nom;
	}

	private void testerObjetNull(Object obj) {
		if (obj==null)
			throw new IllegalArgumentException();
	}

	public boolean add(Unite e) {
		testerObjetNull(e);
		for (Unite u:modeles)
			if (u.getClass().equals(e.getClass()))
				throw new IllegalArgumentException("Le modèle d'unité est déjà prévu");
		
		return modeles.add(e);
	}	
	
	public Unite produire(Class<?> c ) throws UniteInterditeException {
		for (Unite unite:modeles) {
			if (unite.getClass()==c)
				return unite.clone();
		}
		throw new UniteInterditeException();
	}
	
	@Override
	public String toString() {
		return "Batiment["+nom+"]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batiment other = (Batiment) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}	
}
