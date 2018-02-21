package metier;

public class Application {

	public static void main(String[] args) throws MarqueException {
		
		// Création de 3 véhicules
		Vehicule	v1 = new Vehicule("AUDI", "GH 567 HN");
		Vehicule	v2 = new Vehicule("PEUGEOT", "HG 987 AA");
		Vehicule	v3 = new Vehicule("CITROEN", "KK 345 DF");
		
		// Création d'un entretien (une vidange)
		Entretien	ent1 = new Entretien("Vidange", 35);
		
		// On clone cet entretien
		Entretien	ent2 = (Entretien) ent1.clone();
		
		// On associe la première vidange au véhicule v1
		ent1.setVehicule(v1);
		ent1.setKilometrage(45324);
		
		// On associe la seconde vidange à v2
		ent1.setVehicule(v2);
		ent1.setKilometrage(18246);
		
		
		// v3 est en panne. On crée une réparation avec les pièces qu'il faut
		Reparation	rep1 = new Reparation();

		Piece	p1 = new Piece("AFDC5432", "Filtre à air", 35.5, "AUDI"),
				p2 = new Piece("YGHT5678", "Convecteur temporel", 78.3, "AUDI");

		
		rep1.add(p1);
		rep1.add(p2);
		
		v1.add(rep1);
		
		System.out.println(v1);
		System.out.println("Cout : "+rep1.getCout());

		try {
			Piece p3 = new Piece("UIOP7686", "Turbo Garett T3", 105, "RENAULT");
			rep1.add(p3);
		}
		catch (MarqueException e) {
			e.printStackTrace();
		}
	}

}
