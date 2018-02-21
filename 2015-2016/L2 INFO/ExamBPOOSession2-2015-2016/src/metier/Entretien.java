package metier;

public class Entretien extends Intervention {

	private	String	description;
	private	double	forfait;
	
	public Entretien(String description, double forfait) {
		super();
		this.description = description;
		this.forfait = forfait;
	}

	public Entretien(Entretien entretien) {
		super(entretien);
		this.description = entretien.description;
		this.forfait = entretien.forfait;		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getForfait() {
		return forfait;
	}

	public void setForfait(double forfait) {
		this.forfait = forfait;
	}

	@Override
	public String toString() {
		return "Entretien [description=" + description + ", forfait=" + forfait + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entretien other = (Entretien) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(forfait) != Double.doubleToLongBits(other.forfait))
			return false;
		return true;
	}

	@Override
	public double getCout() {
		return forfait;
	}

	@Override
	public Intervention clone() {
		return new Entretien(this); 
	}

}
