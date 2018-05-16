package app;

import boisson.Boisson.ETemperature;
import boisson.BoissonBase;
import boisson.BoissonException;
import boisson.Cocktail;
import boisson.ComposantCocktail;
import boisson.Ingredient;

public class AppBoisson {

	public static void main(String[] args) {
		try {
			BoissonBase badoit = new BoissonBase("Badoit", ETemperature.FRAICHE, 0.2);
			BoissonBase rhum = new BoissonBase("Rhum cubain", ETemperature.AMBIANTE, 1);
			ComposantCocktail	sucreCanne = new ComposantCocktail("Sucre de canne", 2.5);
			ComposantCocktail	citronVert = new ComposantCocktail("Citron vert", 6);
			ComposantCocktail	feuilleMenthe = new ComposantCocktail("Feuilles de menthe", 15);
			
			
			Cocktail	cocktail = new Cocktail("Mojito", ETemperature.FRAICHE);
			cocktail.ajouterIngredient(new Ingredient(badoit,10));
			cocktail.ajouterIngredient(new Ingredient(rhum,4));
			cocktail.ajouterIngredient(new Ingredient(sucreCanne,0.2));
			cocktail.ajouterIngredient(new Ingredient(citronVert,0.1));
			cocktail.ajouterIngredient(new Ingredient(feuilleMenthe,0.05));
			
			System.out.println(cocktail);
			
		} catch (BoissonException e) {
			e.printStackTrace();
		}
		
	}

}
