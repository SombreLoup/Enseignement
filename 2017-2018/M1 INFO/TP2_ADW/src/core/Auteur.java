package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name="Auteur")
public class Auteur {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_aut")
	private	int		code	= -1;
	
	@Column(name = "nom_aut")
	private	String	nom 	= "nom";
	
	
	@Column(name = "statut_aut")
	private	int		statut	= 1;
	
	@Column(name = "date_emb_aut")
	@Temporal(TemporalType.DATE)
	private	Date	dateEmbauche = Calendar.getInstance().getTime();
	
	@OneToMany(mappedBy="auteur", cascade=CascadeType.ALL) // Pour que Test4 soit ok
//	@Transient  // Pour que Test3 soit ok
	private	List<Article>	listeArticles = new ArrayList<Article>();

	public Auteur() {
	}

	public Auteur(String nom, int statut, Date dateEmbauche) {
		testerNom(nom);
		testerStatut(statut);
		
		this.nom = nom;
		this.statut = statut;
		this.dateEmbauche = dateEmbauche;
	}

	public void testerNom(String nom) {
		if ((nom==null) || (nom.trim().length()==0))
			throw new IllegalArgumentException("Nom vide ou null");
	}

	public void testerStatut(int nb) {
		if ((nb<1) || (nb>5))
			throw new IllegalArgumentException("Statut incorrect");
	}

	
	public int size() {
		return listeArticles.size();
	}

	public boolean isEmpty() {
		return listeArticles.isEmpty();
	}

	public boolean add(Article e) {
		return listeArticles.add(e);
	}

	public boolean remove(Object o) {
		return listeArticles.remove(o);
	}

	public Article get(int index) {
		return listeArticles.get(index);
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
		testerNom(nom);
		this.nom = nom;
	}

	public int getStatut() {
		return statut;
	}

	public void setStatut(int statut) {
		testerStatut(statut);
		this.statut = statut;
	}

	public boolean contains(Object o) {
		return listeArticles.contains(o);
	}

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	@Override
	public String toString() {
		return "Auteur [code=" + code + ", nom=" + nom + ", statut=" + statut
				+ ", dateEmbauche=" + dateEmbauche + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Auteur other = (Auteur) obj;
		if (code != other.code)
			return false;
		return true;
	}
	
	
}
