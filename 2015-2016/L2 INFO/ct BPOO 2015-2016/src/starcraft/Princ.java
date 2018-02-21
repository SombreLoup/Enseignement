package starcraft;

public class Princ {

	public static void main(String[] args) {
		// Construction d'une caserne
		Batiment	b1 = new Batiment("Caserne");
		
		// au départ, la caserne ne peut construire que des marines
		b1.add(new Marine());

		
		// La caserne produit crée deux marines
		Marine soldat1 = null, soldat2 = null;
		try {
			soldat1 = (Marine)b1.produire(Marine.class);
			soldat2 = (Marine)b1.produire(Marine.class);
		}
		catch(UniteInterditeException e) {
			System.out.println("La caserne ne peut pas produire des unité de type Marine");
		}
		
		// On ajoute le type Marauder
		b1.add(new Marauder());
		
		// On crée un marauder
		Marauder rambo = new Marauder();
		
		// Combat à mort...
		for (;;) {
			try {
				rambo.attaquer(soldat1);		
				System.out.println(soldat1);
			} catch (MortUniteException e) {
				System.out.println("Le pov' soldat est mort...");
				break;
			}
		}
	}

}
