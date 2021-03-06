package beans;

import java.io.Serializable;
import java.util.Date;

import core.Concert;
import core.Interprete;
import dao.jpa.DAOConcertJPA;
import dao.jpa.DAOInterpreteJPA;

public class BeanConcert implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 417468708979940382L;
	
	
	private	Concert		concert = new Concert();
	private	Interprete	interprete = new Interprete();
	
	public int getInterpreteSelectionnee() {
		return interprete.getCode();
	}

	public void setInterpreteSelectionnee(int interpreteSelectionnee) {
		this.interprete = DAOInterpreteJPA.getInstance().get(interpreteSelectionnee);
	}

	public String	getNom() {
		return concert.getNom();
	}
	
	public void setNom(String nom) {
		concert.setNom(nom);
	}
	
	public Date	getDate() {
		return concert.getDate();
	}
	
	public void setDate(Date date) {
		concert.setDate(new java.sql.Date(date.getTime()));
	}
	
	public Concert getConcert() {
		return concert;
	}

	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	public String enregistrer() {
		DAOConcertJPA.getInstance().save(concert);
		return "ConcertEnregistrŽ";
	}
	
	public String mettreAJour() {
		concert.add(interprete);
		DAOConcertJPA.getInstance().update(concert);
		return "ConcertMisAJour";
	}
	
}
