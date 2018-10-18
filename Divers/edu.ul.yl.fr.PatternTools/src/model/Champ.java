package model;

public class Champ {
	private	String	visibilite = "private";
	private	String	nom = "monChamp";
	private	String	type = "String";
	private boolean	getter = true;
	private	boolean	setter = true;
	private	boolean construction = true;
	private	boolean egalite = true;
	private	boolean affichage = true;
	private	String	valeurParDefaut = "";
	private	String	templateCollection = "";
	
	public Champ() {
		super();
	}
	
	public Champ(String visibilite, String nom, String type) {
		super();
		this.visibilite = visibilite;
		this.nom = nom.trim();
		this.type = type.trim();
		if (type.equals("ArrayList"))
			construction = false;
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
		this.nom = nom.trim();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type.trim();
		if (type.equals("ArrayList"))
			construction = false;
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

	public boolean isGetter() {
		return getter;
	}

	public void setGetter(boolean getter) {
		this.getter = getter;
	}

	public boolean isSetter() {
		return setter;
	}

	public void setSetter(boolean setter) {
		this.setter = setter;
	}

	public boolean isConstruction() {
		return construction;
	}

	public void setConstruction(boolean construction) {
		this.construction = construction;
	}

	public boolean isEgalite() {
		return egalite;
	}

	public void setEgalite(boolean egalite) {
		this.egalite = egalite;
	}

	public String getValeurParDefaut() {
		return valeurParDefaut;
	}

	public void setValeurParDefaut(String valeurParDefaut) {
		this.valeurParDefaut = valeurParDefaut;
	}

	public boolean isAffichage() {
		return affichage;
	}

	public void setAffichage(boolean affichage) {
		this.affichage = affichage;
	}

	public String getTemplateCollection() {
		return templateCollection;
	}

	public void setTemplateCollection(String templateCollection) {
		this.templateCollection = templateCollection;
	}

	String genererType() {
		if (isCollection())
			return Classe.TYPE_COLLECTION +"<"+getTemplateCollection()+">";
		else 
			return getType();
	}

	public boolean isCollection() {
		return type.equals(Classe.TYPE_COLLECTION);
	}
}
