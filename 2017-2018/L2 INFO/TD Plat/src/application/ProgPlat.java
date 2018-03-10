package application;

import plat.CategoriePlat;
import plat.Plat;

public class ProgPlat {
	public static void main(String[] args) {
		/**
		 * Scénario exo 2
		 */
		CategoriePlat	salades = new CategoriePlat("Salades");
		CategoriePlat	viandes = new CategoriePlat("Viandes");
		CategoriePlat	desserts = new CategoriePlat("Desserts");
		CategoriePlat	poissons = new CategoriePlat("Poissons");
		
		Plat	saladeVosgienne = new Plat("Salade Vosgienne", 5.5, 2.25, false, salades);
		System.out.println(salades);
		
		Plat	tournedosRossini = new Plat("Tournedos ROSSINI", 17.4, 11, false, viandes);
		System.out.println(viandes);
		
		Plat	ilesFlottantes = new Plat("Iles Flottantes", desserts);
		ilesFlottantes.setPrixVente(6);
		System.out.println(desserts);
	}
}
