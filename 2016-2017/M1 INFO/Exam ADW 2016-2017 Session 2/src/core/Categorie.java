package core;

import java.io.Serializable;
import java.util.ArrayList;

public class Categorie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private	int		code = -1;
	private	String	libelle = "";
	
	private	ArrayList<DVD>	listeDVD = new ArrayList<>();

	public Categorie() {
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArrayList<DVD> getListeDVD() {
		return listeDVD;
	}
	
	public void ajouterDVD(DVD dvd) {
		listeDVD.add(dvd);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + ((listeDVD == null) ? 0 : listeDVD.hashCode());
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
		Categorie other = (Categorie) obj;
		if (code != other.code)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (listeDVD == null) {
			if (other.listeDVD != null)
				return false;
		} else if (!listeDVD.equals(other.listeDVD))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Categorie [code=" + code + ", libelle=" + libelle + ", listeDVD=" + listeDVD + "]";
	}	
}
