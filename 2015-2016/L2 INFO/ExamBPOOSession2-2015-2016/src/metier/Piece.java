package metier;

public class Piece {
	
	private	String	reference;
	private	String	nom;
	private	double	cout;
	private	String	marque;
	
	
	public Piece(String reference, String nom, double cout, String marque) throws MarqueException {
		setReference(reference);
		setCout(cout);
		setMarque(marque);
		setNom(nom);
	}
	
	public void setNom(String nom) {
		if (nom==null)
			throw new IllegalArgumentException("Le nom ne doit pas être null");
		this.nom = nom;
	}

	public Piece(Piece piece) {
		try {
			setReference(piece.reference);
			setNom(piece.nom);
			setCout(piece.cout);
			setMarque(piece.marque);
		} catch (MarqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		if (reference==null || reference.length()!=8)
			throw new IllegalArgumentException("Reférence invalide");
		this.reference = reference;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		if (cout<20)
			throw new IllegalArgumentException("Cout trop faible");
		this.cout = cout;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque2) throws MarqueException {
		if (!Marque.contient(marque2))
			throw new MarqueException("Marque inexistante");
		this.marque = marque2;
	}

	@Override
	public String toString() {
		return "Piece [reference=" + reference + "nom=" + nom + ", cout=" + cout + ", marque=" + marque + "]";
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
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		return true;
	}
	
	public Piece clone() {
			return new Piece(this);
	}
	

}
