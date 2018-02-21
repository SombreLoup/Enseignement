package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Article")
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_a")
	private	int		code = -1;
	
	@Column(name = "titre_a")
	private	String 	titre = "Titre";

	@Column(name = "nb_mots_a")
	private	int 	nombreMots = 30;

	@Column(name = "tarif_a")
	private	double	tarif = 100;
	
	@ManyToOne						// Pour que Test4 soit ok
	@JoinColumn(name="code_aut")	// Pour que Test4 soit ok
//	@Transient	// Pour que Test3 soit ok
	private	Auteur	auteur = new Auteur();
	
	public Article() {
	}

	public Article(String titre, int nombreMots, double tarif, Auteur auteur) {
		testerAuteur(auteur);
		testerTitre(titre);
		testerNombreMots(nombreMots);
		testerTarif(tarif);
		this.titre = titre;
		this.nombreMots = nombreMots;
		this.tarif = tarif;
		this.auteur = auteur;
		auteur.add(this);
	}

	public void testerTitre(String titre) {
		if ((titre==null) || (titre.trim().length()==0))
			throw new IllegalArgumentException("Titre vide ou null");
	}

	public void testerAuteur(Auteur auteur) {
		if (auteur==null) 
			throw new IllegalArgumentException("Auteur null");
	}

	public void testerNombreMots(int nb) {
		if ((nb<30) || (nb>1000))
			throw new IllegalArgumentException("Nombre de mots incorrect");
	}

	public void testerTarif(double t) {
		if ((t<100) || (t>5000))
			throw new IllegalArgumentException("Tarif incorrect");
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		testerTitre(titre);
		this.titre = titre;
	}

	public int getNombreMots() {
		return nombreMots;
	}

	public void setNombreMots(int nombreMots) {
		testerNombreMots(nombreMots);
		this.nombreMots = nombreMots;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		testerTarif(tarif);
		this.tarif = tarif;
	}

	@Override
	public String toString() {
		return "Article [code=" + code + ", titre=" + titre + ", nombreMots="
				+ nombreMots + ", tarif=" + tarif + "]";
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		testerAuteur(auteur);
		if (this.auteur.contains(this))
			this.auteur.remove(this);
		this.auteur = auteur;
		auteur.add(this);
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
		Article other = (Article) obj;
		if (code != other.code)
			return false;
		return true;
	}

	
	
}
