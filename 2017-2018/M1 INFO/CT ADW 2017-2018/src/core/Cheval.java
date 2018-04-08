package core;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cheval")
public class Cheval {
	
	@OneToMany(mappedBy="cheval")
	private	ArrayList<Participation>	participations = new ArrayList<Participation>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_ch")
	private	int	code;
	
	@Column(name="nom_ch")
	private	String	nom;
	
		
	
	public Cheval() {
	}
	
	public Cheval(int i, String string) {
		this.code = i;
		this.nom = string;
	}

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

	@Override
	public String toString() {
		return "Cheval [participations=" + participations + ", code=" + code + ", nom=" + nom + "]";
	}
	
	
}
