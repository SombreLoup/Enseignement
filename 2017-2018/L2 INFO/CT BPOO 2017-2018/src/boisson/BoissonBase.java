package boisson;

public class BoissonBase extends Boisson {

	private	boolean	alcoolisee = false;
	private	double	prixCl;
	
	public BoissonBase(String nom, ETemperature temperature, double prixCl) throws BoissonException {
		super(nom, temperature);
		this.prixCl = prixCl;
	}
	
	public boolean estAlcoolisee() {
		return alcoolisee;
	}
	public void setAlcoolisee(boolean estAlcoolisee) {
		this.alcoolisee = estAlcoolisee;
	}
	public double getPrix() {
		return prixCl;
	}
	public void setPrixCl(double prixCl) {
		this.prixCl = prixCl;
	}

	@Override
	public String toString() {
		return "BoissonBase [alcoolisee=" + alcoolisee + ", prixCl=" + prixCl + ", " + super.toString()+"]";
	}
	
	
	
}
