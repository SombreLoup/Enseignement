package core;

import java.util.ArrayList;

public class Cheval {
	private	ArrayList<Participation>	participations = new ArrayList<Participation>();
	private	int	code;
	private	String	nom;
	
		
	public ArrayList<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(ArrayList<Participation> participations) {
		this.participations = participations;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public	int getGains() {
		int cumul = 0;
		
		for (Participation participation : participations) {
			if (participation.getPlace() == 1)
				cumul += participation.getCourse().getRecompense();
		}
		
		return cumul;
	}
}
