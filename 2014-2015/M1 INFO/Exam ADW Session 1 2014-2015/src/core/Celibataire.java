package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="celibataire")
public class Celibataire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_cel")
	private	int		code	= -1;
	
	@Column(name="nom_cel")
	private	String	nom;

	@Column(name="dn_cel")
	@Temporal(TemporalType.DATE)
	private	Date	dateNaissance;

	@Column(name="cp_cel")
	private	String	codePostal;

	@Column(name="homme")
	private	boolean	homme;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="participation",
	joinColumns={
			@JoinColumn(table="celibataire",name="code_cel", referencedColumnName="code_cel")
	},
	inverseJoinColumns={
			@JoinColumn(table="rencontre", name="code_ren", referencedColumnName="code_ren")
	})	
	private	List<Rencontre>	rencontres = new ArrayList<Rencontre>();
	
	public Celibataire(String nom, Date dateNaissance, String codePostal, boolean homme) {
		super();
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.codePostal = codePostal;
		this.homme = homme;
	}

	public Celibataire() {
		super();
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

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public boolean isHomme() {
		return homme;
	}

	public void setHomme(boolean homme) {
		this.homme = homme;
	}
	
	public boolean add(Rencontre e) {
		if (! e.getParticipants().contains(this))
			e.getParticipants().add(this);

		return rencontres.add(e);
	}

	public boolean remove(Object o) {
		return rencontres.remove(o);
	}

	public Rencontre get(int index) {
		return rencontres.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + (homme ? 1231 : 1237);
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
		Celibataire other = (Celibataire) obj;
		if (code != other.code)
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (homme != other.homme)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public List<Rencontre> getRencontres() {
		return rencontres;
	}

	public void setRencontres(List<Rencontre> rencontres) {
		this.rencontres = rencontres;
	}

	@Override
	public String toString() {
		return "Celibataire [code=" + code + ", nom=" + nom
				+ ", dateNaissance=" + dateNaissance + ", codePostal="
				+ codePostal + ", homme=" + homme + "]";
	}
}
