package core;

import java.util.Date;

public class Entretien extends Intervention {
	
	private	String	type;
	private	double	forfait;
	
	public Entretien(Date date, String type, double forfait) {
		super(date);
		this.type = type;
		this.forfait = forfait;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getForfait() {
		return forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
	}

	@Override
	public String toString() {
		return "Entretien [type=" + type + ", forfait=" + forfait + "]";
	}

	@Override
	public double getMontant() {
		return forfait;
	}

	@Override
	public Intervention clone() {
		return new Entretien(getDate(), type, forfait);
	}

}
