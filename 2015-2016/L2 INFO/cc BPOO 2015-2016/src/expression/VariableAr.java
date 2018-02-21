package expression;

public class VariableAr implements ExprAr {

	private	double	valeur = 0;
	private	String	nom;
	
	public VariableAr(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public double evaluer() {
		return valeur;
	}
	
	public void setValeur(double d) {
		valeur = d;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableAr other = (VariableAr) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(valeur) != Double.doubleToLongBits(other.valeur))
			return false;
		return true;
	}
}
