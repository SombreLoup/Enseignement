package idee2.rdv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creneau est une classe simple, developpee selon le modele standard. Un creneau a lieu a un jour 
 * (date) avec une heure de debut (heureDebut) et une heure de fin (heureFin). Cette classe ne fait
 * strictement aucune verification des donnees. Ce n'est pas bien mais c'est volontaire dans le cadre
 * de ce TP note. Vous devez donc considerer qu'un creneau est toujours bien construit! 
 * La fonction principale de cette classe est la fonction chevauche(unCreneau) qui compare le creneau courant (this)
 * avec le parametre. Si les deux creneaux se supperposent, m�me en partie, la fonction renvoie true. Si
 * les deux creneaux sont bien disjoints, elle renvoie false.
 * La maniere la plus pratique de manipuler des dates et des heures est d'utiliser des chaines de
 * caracteres. Il y a donc des instructions speciales chargees de faire les conversions appropriees.
 */
public class Creneau {
	private	Date	date;
	private	Date	heureDebut, heureFin;
	
	
	private static SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");		// Format de conversion pour les heures
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	// Format de conversion pour les dates

	//-------------------------------------
	
	/**
	 * Constructeur complet avec toutes les informations pour initialiser un creneau
	 * @param strDate
	 * @param strHeureDebut
	 * @param strHeureFin
	 * @throws ParseException Exception lancee si dans les chaines de caracteres, des caracteres incorrects sont presents
	 */
	public Creneau(String strDate, String strHeureDebut, String strHeureFin) throws ParseException {
		date = convertirDate(strDate);
		
		heureDebut = convertirHeure(strHeureDebut);
		heureFin = convertirHeure(strHeureFin);
	}

	/**
	 * Constructeur d'un creneau a une date precise. L'heure de debut est 8:00 et celle de fin est 18:00
	 * @param strDate
	 * @throws ParseException Exception lancee si dans les chaines de caracteres, des caracteres incorrects sont presents
	 */
	public Creneau(String strDate) throws ParseException {
		date = convertirDate(strDate);
		
		heureDebut = convertirHeure("08:00");
		heureFin = convertirHeure("18:00");
	}
	
	//--------------------------------

	private Date convertirHeure(String strHeure) throws ParseException {
		return heureFormat.parse(strHeure);
	}

	private Date convertirDate(String strDate) throws ParseException {
		return  dateFormat.parse(strDate);
	}

	//--------------------------------
	// Accesseurs
	public Date getDate() {
		return date;
	}

	public void setDate(String strDate) throws ParseException {
		date =  convertirDate(strDate);	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(String strHeure) throws ParseException {
		this.heureDebut = convertirHeure(strHeure);
	}

	public Date getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String strHeure) throws ParseException {
		this.heureFin = convertirHeure(strHeure);
	}
	
	//-------------------------------
	/**
	 * Renvoie vrai si les deux creneaux se chevauchent. La fonction verifie normalement les inclusions
	 * @param creneau
	 * @return vrai si chevauchement, faux si disjoints
	 */
	public boolean chevauche(Creneau creneau) {
		if (!this.getDate().equals(creneau.getDate()))
				return false;
		
		if (creneau.getHeureDebut().equals(this.getHeureDebut()))
			return true;
		if (creneau.getHeureFin().equals(this.getHeureFin()))
			return true;
		
		if (creneau.getHeureDebut().after(this.getHeureDebut()) && creneau.getHeureDebut().before(this.getHeureFin()))
			return true;
		if (creneau.getHeureFin().after(this.getHeureDebut()) && creneau.getHeureFin().before(this.getHeureFin()))
			return true;
		
		if (this.getHeureDebut().after(creneau.getHeureDebut()) && this.getHeureDebut().before(creneau.getHeureFin()))
			return true;
		if (this.getHeureFin().after(creneau.getHeureDebut()) && this.getHeureFin().before(creneau.getHeureFin()))
			return true;
		
		
		return false;
	}
	
	//----------------------------------

	@Override
	public String toString() {
		return "Creneau [date=" + dateFormat.format(date) + ", heureDebut=" + heureFormat.format(heureDebut)
				+ ", heureFin=" + heureFormat.format(heureFin) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creneau other = (Creneau) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (heureDebut == null) {
			if (other.heureDebut != null)
				return false;
		} else if (!heureDebut.equals(other.heureDebut))
			return false;
		if (heureFin == null) {
			if (other.heureFin != null)
				return false;
		} else if (!heureFin.equals(other.heureFin))
			return false;
		return true;
	}
	
	
	
}
