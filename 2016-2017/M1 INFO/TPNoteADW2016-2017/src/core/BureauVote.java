package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




public class BureauVote implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 276837767294667813L;


	private	int						numero = -1;
	
	
	private	String					lieu = "";
	
	
	private	List<ResultatPartiel>	resultatPartiel = new ArrayList<ResultatPartiel>();
	
	public BureauVote() {
	}

	public BureauVote(String lieu) {
		this.lieu = lieu;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public List<ResultatPartiel> getResultatPartiel() {
		return resultatPartiel;
	}

	public void setResultatPartiel(List<ResultatPartiel> resultatPartiel) {
		this.resultatPartiel = resultatPartiel;
	}

	public int size() {
		return resultatPartiel.size();
	}

	public boolean add(ResultatPartiel e) {
		return resultatPartiel.add(e);
	}

	public ResultatPartiel get(int index) {
		return resultatPartiel.get(index);
	}

	@Override
	public String toString() {
		return "BureauVote [numero=" + numero + ", lieu=" + lieu + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BureauVote other = (BureauVote) obj;
		if (lieu == null) {
			if (other.lieu != null)
				return false;
		} else if (!lieu.equals(other.lieu))
			return false;
		if (numero != other.numero)
			return false;
		if (resultatPartiel == null) {
			if (other.resultatPartiel != null)
				return false;
		} else if (!resultatPartiel.equals(other.resultatPartiel))
			return false;
		return true;
	}

	public int getResultat(Candidat candidat) {
		
		for (ResultatPartiel res : resultatPartiel) {
			if (res.getCandidat().equals(candidat))
				return res.getNombreVoix();
		}
		return 0;
	}
	
	
}
