package core;

public class Credit {
	public final static double	TAUX_NORMAL = 2.5;
	public final static double	TAUX_PLANCHER = 1.85;
	
	private	double	taux;
	private	int		nombreMois;
	private	double	capital;
	
	public Credit(double taux, int nombreMois, double capital) throws CreditException {
		setTaux(taux);
		setNombreMois(nombreMois);
		setCapital(capital);
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) throws CreditException {
		if (taux<TAUX_PLANCHER || taux>TAUX_NORMAL)
			throw new CreditException("Le taux n'est pas dans la fourchette");
		
		this.taux = taux;
	}

	public int getNombreMois() {
		return nombreMois;
	}

	public void setNombreMois(int nombreMois) throws CreditException {
		if (nombreMois<4 || nombreMois>240)
			throw new CreditException("Durée du crédit erronée");
		this.nombreMois = nombreMois;
	}

	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		if (capital < 0)
			throw new IllegalArgumentException("Le capital doit être positif");
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "Credit [taux=" + taux + ", nombreMois=" + nombreMois + ", capital=" + capital + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credit other = (Credit) obj;
		if (Double.doubleToLongBits(capital) != Double.doubleToLongBits(other.capital))
			return false;
		if (nombreMois != other.nombreMois)
			return false;
		if (Double.doubleToLongBits(taux) != Double.doubleToLongBits(other.taux))
			return false;
		return true;
	}
	
	
}
