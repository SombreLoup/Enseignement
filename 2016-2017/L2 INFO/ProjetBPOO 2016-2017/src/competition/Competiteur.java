package competition;

public abstract class Competiteur {
	public Competiteur(String string) {
		setNom(string);
	}

	public String getNom() {
		return nom;
	}

	private void setNom(String nom) {
		this.nom = nom;
	}
	
	public abstract Marque getMarqueInitiale();
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competiteur other = (Competiteur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}


	private	String	nom;
}
