package plat;

import java.util.ArrayList;

public class CategoriePlat {
	private	String	nom;
	
	/**
	 * Un HashSet est possible et facilite certains traitement. 
	 * Mais je ne leur ai pas donn� de doc. On peut
	 * leur dire de regarder la javadoc
	 */
	private	ArrayList<Plat>	listePlat = new ArrayList<Plat>();
	
	
	public CategoriePlat(String nom) {
		setNom(nom);
	}

	public String getNom() {
		return nom;
	}
	
	private void setNom(String nom) {
		if ((nom==null) || (nom.trim().equals(""))) {
			System.out.println("Argument ill�gal");
			System.exit(-1);
		}
		this.nom = nom;
	}

	public void add(Plat e) {
		if (e.getCategorie()==null)
			listePlat.add(e);
		else {
			e.setCategorie(this);
		}
	}

	public Plat get(int index) {
		if ((index<0) || (index>=size())) {
			System.out.println("Indice incorrect");
			System.exit(-1);
		}
		return listePlat.get(index);
	}

	public boolean remove(Object o) {
		if (!listePlat.contains(o)) {
			throw new IllegalArgumentException("Pas le droit de retirer la catégorie du plat sans la remplacer...");
		}
		else
			return listePlat.remove(o);
	}
	

	
	public boolean contains(Object o) {
		return listePlat.contains(o);
	}

	public int size() {
		return listePlat.size();
	}

	/**
	 * equals g�n�r� par Eclipse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriePlat other = (CategoriePlat) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriePlat [nom=" + nom + ", listePlat=" + listePlat + "]";
	}
}
