package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="matiere")
public class Matiere implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="num_mat")
	private	int	numero = -1;
	
	@Column(name="nom_mat")
	private	String	nom;
	
	@Column(name="epreuve_bac")
	private	boolean	epreuveBac;

	public Matiere() {
	}

	public Matiere(String nom, boolean epreuveBac) {
		this.nom = nom;
		this.epreuveBac = epreuveBac;
	}

	public Matiere(int num, String n, boolean b) {
		this.numero = num;
		this.nom = n;
		this.epreuveBac = b;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isEpreuveBac() {
		return epreuveBac;
	}

	public void setEpreuveBac(boolean epreuveBac) {
		this.epreuveBac = epreuveBac;
	}

	@Override
	public String toString() {
		return "Matiere [numero=" + numero + ", nom=" + nom + ", epreuveBac=" + epreuveBac + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (epreuveBac ? 1231 : 1237);
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + numero;
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
		Matiere other = (Matiere) obj;
		if (epreuveBac != other.epreuveBac)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6358262093846246121L;

}
