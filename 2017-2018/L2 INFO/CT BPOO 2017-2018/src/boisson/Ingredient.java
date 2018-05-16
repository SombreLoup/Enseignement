package boisson;

public class Ingredient {
	
	private	Boisson				boisson;
	private	ComposantCocktail	composant;
	private	double				quantite;
	
	
	public Ingredient(ComposantCocktail composant, double quantite) {
		super();
		this.composant = composant;
		this.quantite = quantite;
	}
	
	public Ingredient(Boisson boisson, double quantite) {
		super();
		this.boisson = boisson;
		this.quantite = quantite;
	}

	public Boisson getBoisson() {
		return boisson;
	}

	public ComposantCocktail getComposant() {
		return composant;
	}

	public double getQuantite() {
		return quantite;
	}
	
	public double getPrix() {
		if (boisson==null)
			return quantite*composant.getPrixKilo();
		else
			return quantite*boisson.getPrix();
	}

	@Override
	public String toString() {
		if (boisson==null)
			return "Ingredient [composant=" + composant + ", quantite=" + quantite + "]";
		else
			return "Ingredient [boisson=" + boisson + ", quantite=" + quantite + "]";
	}

}
