package expression;

import instruction.Instruction;

public class Litteral implements ExprAr {
	
	private	double	valeur;
	
	public Litteral(double v) {
		valeur = v;
	}
	
	@Override
	public double evaluer() {
		return valeur;
	}

	@Override
	public String toString() {
		return "" + valeur;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Litteral other = (Litteral) obj;
		if (Double.doubleToLongBits(valeur) != Double.doubleToLongBits(other.valeur))
			return false;
		return true;
	}
	
	

}
