package core;

public class Piece {
	private	String	reference;
	private	double	prix;
	private	int		tempsRemplacement;
	

	public Piece(String reference, double prix, int tempsRemplacement) {
		setReference(reference);
		setPrix(prix);
		setTempsRemplacement(tempsRemplacement);
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		if (reference==null || reference.trim().equals(""))
			throw new IllegalArgumentException("RŽference incorrecte");
		this.reference = reference;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		if (prix<=0)
			throw new IllegalArgumentException("Prix incorrect");		
		this.prix = prix;
	}

	public int getTempsRemplacement() {
		return tempsRemplacement;
	}

	public void setTempsRemplacement(int tempsRemplacement) {
		if ((tempsRemplacement<15) || (tempsRemplacement>360))
			throw new IllegalArgumentException("Temps de remplacement incorrect");					
		this.tempsRemplacement = tempsRemplacement;
	}

	@Override
	public String toString() {
		return "Piece [reference=" + reference + ", prix=" + prix
				+ ", tempsRemplacement=" + tempsRemplacement + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (Double.doubleToLongBits(prix) != Double
				.doubleToLongBits(other.prix))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (tempsRemplacement != other.tempsRemplacement)
			return false;
		return true;
	}
}
