package core;

import java.util.ArrayList;

public class Course {
	private	ArrayList<Participation>	participants = new ArrayList<Participation>();
	private	String nom;
	private	int	numero;
	private	int recompense;
	
	
	public ArrayList<Participation> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<Participation> participants) {
		this.participants = participants;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getRecompense() {
		return recompense;
	}
	public void setRecompense(int recompense) {
		this.recompense = recompense;
	}
	
	
}
