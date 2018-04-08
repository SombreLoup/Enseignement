package core;

import java.util.ArrayList;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	
	@OneToMany(mappedBy="course")
	private	ArrayList<Participation>	participants = new ArrayList<Participation>();

	@Column(name="nom_c")
	private	String nom;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="num_c")
	private	int	numero;
	
	@Column(name="recompense")
	private	int recompense;
	
	
	public Course(int i, String string, int j) {
		this.nom = string;
		this.numero = i;
		this.recompense = j;
	}
	
	public Course() {
	}

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

	@Override
	public String toString() {
		return "Course [participants=" + participants + ", nom=" + nom + ", numero=" + numero + ", recompense="
				+ recompense + "]";
	}
	
	
}
