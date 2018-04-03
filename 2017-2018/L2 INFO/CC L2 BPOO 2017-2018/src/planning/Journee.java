package planning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Activite.Activite;

public class Journee {

	private	ArrayList<Activite>	planning = new ArrayList<Activite>();
	private	Date					date;
	
	public Journee(String sDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.setDate(format.parse(sDate));
	}
		
	public boolean ajouterActivite(Activite e) {
		return planning.add(e);
	}

	public double dureeInactivite() {
		double d = 0;
		for (Activite activite : planning)
			d += activite.getDuree();
		
		return 12-d;
	}
	
	public double cout() {
		double c = 0;
		for (Activite activite : planning)
			c += activite.getCout();
		
		return c;
	}
	
	public boolean estValide() {
		boolean culturelle = false;
		boolean sportivive = false;

		for (Activite activite : planning) {
			culturelle = culturelle || activite.estCulturelle();
			sportivive = sportivive || activite.estSportive();
		}

		return culturelle && sportivive;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		String s = "Journée du "+format.format(date);
		s += "\n";
		for (Activite activite : planning) {
			s += activite+"\n";
		}
		
		return s;
	}
}
