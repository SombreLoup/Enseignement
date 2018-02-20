package core;

import java.io.Serializable;
import java.util.Date;

public class Devoir implements Serializable {
	private	int	code = -1;
	private	Matiere	matiere;
	private	String	description;
	private	Date		dateLimite;
	
	public Devoir() {
	}

	public Devoir(Matiere matiere, String description, Date dateLimite) {
		this.matiere = matiere;
		this.description = description;
		this.dateLimite = dateLimite;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}

	@Override
	public String toString() {
		return "Devoir [code=" + code + ", matiere=" + matiere + ", description=" + description + ", dateLimite=" + dateLimite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((matiere == null) ? 0 : matiere.hashCode());
		result = prime * result + ((dateLimite == null) ? 0 : dateLimite.hashCode());
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
		Devoir other = (Devoir) obj;
		if (code != other.code)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))		
		
		if (dateLimite == null) {
			if (other.dateLimite != null)
				return false;
		} else if (!dateLimite.equals(other.dateLimite))
			return false;
		
		if (matiere == null) {
			if (other.matiere != null)
				return false;
		} else if (!matiere.equals(other.matiere))
			return false;
		return true;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -963179932396779695L;

}
