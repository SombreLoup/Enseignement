package core;

import java.io.Serializable;




public class Candidat implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6464462665455036677L;


	private	int		code = -1;
	
	
	private	String	nom = "";
	
	public Candidat() {
	}

	public Candidat(String nom) {
		this.nom = nom;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Candidat [code=" + code + ", nom=" + nom + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidat other = (Candidat) obj;
		if (code != other.code)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
}
