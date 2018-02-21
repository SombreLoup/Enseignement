package core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="interprete")
public class Interprete {
	
	@ManyToMany(mappedBy="interpretes")
	private List<Concert>	concerts = new ArrayList<Concert>();

	@Id
	@Column(name="code_int")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	int		code = 		-1;

	
	@Column(name="nom_int")
	private	String	nom = 		null;

	
	@OneToMany(mappedBy="interprete")
	private	List<Chanson>	chansons = new ArrayList<Chanson>();

	
	
	
	public boolean add(Chanson o) {
		if (!chansons.contains(o))
			return chansons.add(o);
		else
			return false;
	}


	public Interprete() {
		super();
	}


	public Interprete(String nom) {
		super();
		this.nom = nom;
	}


	public Interprete(int code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Interprete other = (Interprete) obj;
		if (code != other.code)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}


	public boolean add(Concert e) {
		return concerts.add(e);
	}


	@Override
	public String toString() {
		return "["+this.code+";"+this.nom+"{"+this.chansons+"}]";
	}


	public List<Chanson> getChansons() {
		return chansons;
	}


	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public List<Concert> getConcerts() {
		return concerts;
	}


	public void setConcerts(List<Concert> concerts) {
		this.concerts = concerts;
	}
	
}
