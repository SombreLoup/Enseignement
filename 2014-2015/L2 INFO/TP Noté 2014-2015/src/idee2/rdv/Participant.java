package idee2.rdv;



public abstract class Participant {
	private	String	nom;

	public Participant(String nom)  {
		setNom(nom);
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom)  {
		if (estVide(nom))
			throw new IllegalArgumentException("Le nom du particpant est vide");
		this.nom = nom;	
	}

	protected boolean estVide(String nom) {
		return (nom==null) || (nom.trim().equals(""));
	}
	
	public abstract boolean estDisponible(Creneau creneau);
	public abstract void ajouter(Reunion reunion) throws ReunionException;
	public abstract int getNombrePersonnes();


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
}
