package metier;

import java.util.ArrayList;

public class Marque {

	private static	ArrayList<String>	marques = null;
	
	static {
		marques = new ArrayList<String>();
		marques.add("RENAULT");
		marques.add("AUDI");
		marques.add("BMW");
		marques.add("PEUGEOT");
		marques.add("LADA");
	}
	
	public static boolean contient(String marque) {
		return marques.contains(marque);
	}
	
	
	
}
