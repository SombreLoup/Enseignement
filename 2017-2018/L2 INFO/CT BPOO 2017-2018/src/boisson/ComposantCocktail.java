package boisson;

public class ComposantCocktail {
	
	private	String	nom;
	private	double	prixKilo;
	
	public ComposantCocktail(String nom, double prixKilo) {
		super();
		this.nom = nom;
		this.prixKilo = prixKilo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrixKilo() {
		return prixKilo;
	}

	public void setPrixKilo(double prixKilo) {
		this.prixKilo = prixKilo;
	}

	@Override
	public String toString() {
		return "ComposantCocktail [nom=" + nom + ", prixKilo=" + prixKilo + "]";
	}
	
	
}
