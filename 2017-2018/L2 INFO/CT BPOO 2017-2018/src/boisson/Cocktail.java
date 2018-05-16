package boisson;

import java.util.ArrayList;

public class Cocktail extends Boisson {
	
	public Cocktail(String nom, ETemperature temperature) throws BoissonException {
		super(nom, temperature);
	}

	private	ArrayList<Ingredient>	listeIngredients = new ArrayList<Ingredient>();

	@Override
	public double getPrix() {
		double	p = 0;
		
		for (Ingredient ingredient : listeIngredients) {
			p += ingredient.getPrix();
		}
		
		return p;
	}

	@Override
	public boolean estAlcoolisee() {
		for (Ingredient ingredient : listeIngredients) {
			if ((ingredient.getBoisson() != null) && (ingredient.getBoisson().estAlcoolisee()))
					return true;
		}
		
		return false;
	}

	public void ajouterIngredient(Ingredient ingredient) {
		listeIngredients.add(ingredient);
	}

	@Override
	public String toString() {
		String	s = super.toString()+"\n"+"-----------------\n";
		for (Ingredient ingredient : listeIngredients) {
			s += ingredient+"\n";
		}
		s+="Prix : "+getPrix();
		return s;
	}

	
	
}
