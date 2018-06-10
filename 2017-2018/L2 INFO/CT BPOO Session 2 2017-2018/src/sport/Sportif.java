package sport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sportif extends Participant {
	
	private	Date		dateNaissance;

	public Sportif(String nom, String sDate) {
		super(nom);
		
		SimpleDateFormat	format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dateNaissance = format.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Sportif(Sportif sportif) {
		super(sportif);
	}

	@Override
	public Participant clone() {
		return new Sportif(this);
	}
	
	@Override
	public String toString() {
		return "Sportif ["+getNom()+"]";
	}

}
