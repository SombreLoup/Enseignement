package core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;


/**
 * La clé primaire de resultat_partiel est une clé composé du code du candidat et du numéro de bureau. Ce point n'a pas été vu en cours. 
 * Je ne l'ai testé moi-même que pour ce TP. Du coup, je vous donne ce mapping. Il fonctionne bien, j'ai vérifié naturellement
 * @author yann
 *
 */

@Entity
@IdClass(ResultatPartielId.class)
@Table(name="resultat_partiel")
public class ResultatPartiel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6660571408142525577L;

	@Id
	@JoinColumn(name="code_cand")
	private	Candidat	candidat;
	
	@Id
	@JoinColumn(name="num_bur")
	private	BureauVote	bureau;
	
	@Column(name="voix")
	private	int			nombreVoix;
	
	public ResultatPartiel() {
	}

	public ResultatPartiel(Candidat candidat, BureauVote bureau, int nombreVoix) {
		this.candidat = candidat;
		this.bureau = bureau;
		this.nombreVoix = nombreVoix;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public BureauVote getBureau() {
		return bureau;
	}

	public void setBureau(BureauVote bureau) {
		this.bureau = bureau;
	}

	public int getNombreVoix() {
		return nombreVoix;
	}

	public void setNombreVoix(int nombreVoix) {
		this.nombreVoix = nombreVoix;
	}

	@Override
	public String toString() {
		return "ResultatPartiel [candidat=" + candidat + ", bureau=" + bureau + ", nombreVoix=" + nombreVoix + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultatPartiel other = (ResultatPartiel) obj;
		if (bureau == null) {
			if (other.bureau != null)
				return false;
		} else if (!bureau.equals(other.bureau))
			return false;
		if (candidat == null) {
			if (other.candidat != null)
				return false;
		} else if (!candidat.equals(other.candidat))
			return false;
		if (nombreVoix != other.nombreVoix)
			return false;
		return true;
	}
	
	
}

class ResultatPartielId {
	private	int	candidat;
	private	int	bureau;

	public ResultatPartielId() {
		super();
	}

	public int getCandidat() {
		return candidat;
	}

	public void setCandidat(int candidat) {
		this.candidat = candidat;
	}

	public int getBureau() {
		return bureau;
	}

	public void setBureau(int bureau) {
		this.bureau = bureau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bureau;
		result = prime * result + candidat;
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
		ResultatPartielId other = (ResultatPartielId) obj;
		if (bureau != other.bureau)
			return false;
		if (candidat != other.candidat)
			return false;
		return true;
	}


	
}
