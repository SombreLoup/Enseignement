package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class Commune implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2468783138369927308L;


	private	int		numero	= -1;
	
	
	private	String	nom = "";

	
	private	int		codePostal = 0;
	
	
	private	List<BureauVote>	listBureaux = new ArrayList<BureauVote>();
	
	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public Commune(String nom, int cp) {
		this.nom = nom;
		this.codePostal = cp;
	}

	public Commune() {
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

	public List<BureauVote> getListBureaux() {
		return listBureaux;
	}

	public void setListBureaux(List<BureauVote> listBureaux) {
		this.listBureaux = listBureaux;
	}
	
	public BureauVote	get(String boutNomBureau) {
		BureauVote b = null;
		
		for (BureauVote bureauVote : listBureaux) {
			if (bureauVote.getLieu().indexOf(boutNomBureau)!=-1)
				return bureauVote;
		}
		
		return b;
	}

	@Override
	public String toString() {
		return "Commune [numero=" + numero + ", nom=" + nom + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commune other = (Commune) obj;
		if (codePostal != other.codePostal)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public int getResultat(Candidat candidat) {
		int	total = 0;
		
		for (BureauVote bureauVote : listBureaux) {
			total += bureauVote.getResultat(candidat);
		}
		
		return total;
	}

	public int getNombreBureaux() {
		return listBureaux.size();
	}

	public boolean ajouterBureau(BureauVote e) {
		return listBureaux.add(e);
	}
}
