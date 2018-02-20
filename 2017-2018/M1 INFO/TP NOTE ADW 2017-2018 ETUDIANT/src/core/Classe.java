package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classe implements Serializable, Cloneable {
	
	private	int		identifiant;
	private	String	nom;
	private	List<Matiere>	listeMatieres = new ArrayList<Matiere>();
	private	List<Devoir>		listeDevoirs = new ArrayList<Devoir>();

	public Classe() {
	}

	public Classe(String nom, List<Matiere> lm, List<Devoir> ld) {
		this.nom = nom;
		
		if (lm != null)
			this.listeMatieres = lm;
		if (ld != null)
			this.listeDevoirs = ld;
	}

	public Classe(int id, String n, List<Matiere> lm, List<Devoir> ld) {
		this.identifiant = id;
		this.nom = n;
		if (lm != null)
			this.listeMatieres = lm;
		if (ld != null)
			this.listeDevoirs = ld;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Matiere> getListeMatieres() {
		return listeMatieres;
	}

	public void setListeMatieres(List<Matiere> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

	public List<Devoir> getListeDevoir() {
		return listeDevoirs;
	}

	public void setListeDevoir(List<Devoir> listeDevoir) {
		this.listeDevoirs = listeDevoir;
	}

	public void 	ajouterMatiere(Matiere e) {
		listeMatieres.add(e);
	}

	public int getNombreMatieres() {
		return listeMatieres.size();
	}

	public boolean ajouterDevoir(Devoir e) {
		return listeDevoirs.add(e);
	}

	public int getNombreDevoirs() {
		return listeDevoirs.size();
	}

	@Override
	public String toString() {
		return "Classe [identifiant=" + identifiant + ", nom=" + nom + ", listeMatieres=" + listeMatieres
				+ ", listeDevoirs=" + listeDevoirs + "]";
	}
	
	@Override
	public Classe clone()  {
		Classe clone = new Classe(identifiant,nom, new ArrayList<Matiere>(listeMatieres),new ArrayList<Devoir>(listeDevoirs));
		return clone;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identifiant;
		result = prime * result + ((listeDevoirs == null) ? 0 : listeDevoirs.hashCode());
		result = prime * result + ((listeMatieres == null) ? 0 : listeMatieres.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classe other = (Classe) obj;
		if (identifiant != other.identifiant)
			return false;
		if (listeDevoirs == null) {
			if (other.listeDevoirs != null)
				return false;
		} else if (!listeDevoirs.equals(other.listeDevoirs))
			return false;
		if (listeMatieres == null) {
			if (other.listeMatieres != null)
				return false;
		} else if (!listeMatieres.equals(other.listeMatieres))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 5069900576564710131L;

}
