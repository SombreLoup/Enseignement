package boisson;

public class BoissonBase extends Boisson {

	private	boolean	petillante = false;
	private	boolean	alcoolisee = false;
	private	double	prixCl;
	
	public BoissonBase(String nom, ETemperature temperature, double prixCl) throws BoissonException {
		super(nom, temperature);
		this.prixCl = prixCl;
	}
	
	public boolean estPetillante() {
		return petillante;
	}
	public void setPetillante(boolean estPetillante) {
		this.petillante = estPetillante;
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
		return "BoissonBase [petillante=" + petillante + ", alcoolisee=" + alcoolisee + ", prixCl=" + prixCl + ", " + super.toString()+"]";
	}
	
	
	
}
