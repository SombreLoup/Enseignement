package model;

public class Champ {
	private	String	visibilite = "private";
	private	String	nom = "monChamp";
	private	String	type = "String";
	
	public Champ() {
		super();
	}
	
	public Champ(String visibilite, String nom, String type) {
		super();
		this.visibilite = visibilite;
		this.nom = nom;
		this.type = type;
	}
	
	public String getVisibilite() {
		return visibilite;
	}
	
	public void setVisibilite(String visibilite) {
		this.visibilite = visibilite;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((visibilite == null) ? 0 : visibilite.hashCode());
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
		Champ other = (Champ) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (visibilite == null) {
			if (other.visibilite != null)
				return false;
		} else if (!visibilite.equals(other.visibilite))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Champ [visibilite=" + visibilite + ", nom=" + nom + ", type=" + type + "]";
	}
}
