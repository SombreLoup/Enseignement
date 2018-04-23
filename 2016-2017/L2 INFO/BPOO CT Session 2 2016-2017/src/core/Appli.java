package core;

import java.util.ArrayList;

public class Appli {

	public static void main(String[] args) {
		
		Residence	residence = new Residence("Les capucines");
		
		try {
			residence.add(new Chambre(245, "Chambre simple", 1, 80));
			residence.add(new Chambre(246, "Chambre double", 2, 120));
			residence.add(new Bungalow(987, "Bungalow Ford", 45));
			residence.add(new Bungalow(657, "Bungalow Willis", 34));
		} catch (HebergementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Hebergement>	possibilites = residence.getDisponibilites(2, 3, 500);
		
		if (possibilites.size()>0) {
			try {
				possibilites.get(0).setNombreOccupant(2);
				System.out.println(possibilites);
			} catch (HebergementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
