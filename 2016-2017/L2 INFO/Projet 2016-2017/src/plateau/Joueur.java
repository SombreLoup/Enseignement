package plateau;


public abstract class Joueur {
	private	String	nom;
	private	Case	caseCourante;

	
	private void setNom(String nom) {
		if (nom==null || nom.trim().equals(""))
			throw new IllegalArgumentException("Le nom du joueur est incorrect");
		
		this.nom = nom;
	}

	public Joueur(String nom) {
		setNom(nom);
	}

	public String getNom() {
		return nom;
	}

	public abstract int	lancerDes();

	public void executer(Action action) throws Exception {
		action.executer();
	}

	public Case getCaseCourante() {
		return caseCourante;
	}

	public void setCaseCourante(Case caseCourante) {
		if (caseCourante==null)
			throw new IllegalArgumentException("La case courante est nulle");
		
		this.caseCourante = caseCourante;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	
}
