package fraction;

class DenominateurNulException extends Exception {
	public DenominateurNulException() {
	}
}

class DenominateurNegatifException extends Exception {
	public DenominateurNegatifException() {
	}
}


public class Fraction {
	
	private	int	numerateur, denominateur;

	public Fraction(int n, int d) throws DenominateurNulException, DenominateurNegatifException {
		setNumerateur(n);
		setDenominateur(d);
	}
	
	public int getNumerateur() {
		return numerateur;
	}

	public void setNumerateur(int numerateur) {
		this.numerateur = numerateur;
	}

	public int getDenominateur() {
		return denominateur;
	}

	public void setDenominateur(int d) throws DenominateurNulException, DenominateurNegatifException {
		if (d==0)
			throw new DenominateurNulException();
		if (d<0)
			throw new DenominateurNegatifException();
		
		this.denominateur = d;
	}
	
	public String toString() {
		return ""+numerateur+"/"+denominateur;
	}
	
	public Fraction	inverse() throws DenominateurNulException {
		Fraction f = null;
		
		try {
			f = new Fraction(denominateur, numerateur);
		} catch (DenominateurNegatifException e) {
			try {
				f = new Fraction(-denominateur, -numerateur);
			} catch (DenominateurNegatifException e1) {
			}
		}
		
		return f;
	}

	
	private Fraction reduite() {
		int d = MathUtil.pgcd(numerateur, denominateur);

		Fraction f = null;
		
		try {
			f = new Fraction(numerateur/d, denominateur/d);
		} catch (Exception e) {
		}
		
		return f;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Fraction f1 = this.reduite();
		Fraction other = (Fraction) obj;
		Fraction f2 = other.reduite();
		
		if (f1.denominateur != f2.denominateur)
			return false;
		if (f1.numerateur != f2.numerateur)
			return false;

		return true;
	}

	
}
