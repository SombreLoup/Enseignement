package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Princ {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	// Format de conversion pour les dates

	private static Date convertirDate(String strDate) throws ParseException {
		return  dateFormat.parse(strDate);
	}


	public static void main(String[] args) throws ParseException, ReparationException {
		Piece	p1 = new Piece("Courroie transmission A4 3.0", 130, 25);
		Piece	p2 = new Piece("Alternateur A4", 245, 45);
		Reparation	r = new Reparation(convertirDate("23/10/2014"), "Pb d'alternateur A4");
		r.add(p1);
		r.add(p2);
		
		Entretien	entretien = new Entretien(convertirDate("11/03/2015"), "Revision A4 normale", 450);
		
		Vehicule	A4 = new Vehicule("FL309DE");
		A4.add(entretien);
		A4.add(r);
		
		A4.afficherCarnetEntretien();
		
	}

}
