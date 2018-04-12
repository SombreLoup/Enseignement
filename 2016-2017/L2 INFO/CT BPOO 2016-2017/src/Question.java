
/**
 * La classe Question est une classe abstraite puisqu'elle
 * contient deux méthodes abstraites, getNote et RAZReponse 
 * @author yann
 *
 */
public abstract class Question {
	private	String	enonce;
	private	double	bareme;
	// Il n'y a pas de champ note puisque cette note va dépendre des classes dérivée
	// et des méthodes de calcul spécifiques
	
	public Question(String enonce, double bareme) {
		this.enonce = enonce;
		this.bareme = bareme;
	}


	public double getBareme() {
		return bareme;
	}


	public void setBareme(double bareme) {
		this.bareme = bareme;
	}


	public String getEnonce() {
		return enonce;
	}


	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	
	/**
	 * Fonction obligatoire car fait partie du modèle "standard"
	 */
	@Override
	public String toString() {
		String s = "";

		s += getEnonce()+"("+getBareme()+" pts)\n";
		
		return s;
	}


	/**
	 * Fonction présente obligatoirement conformément à l'énoncé. C'est-à-dire 
	 * que cette classe est standard et en plus je précise la règle d'égalité
	 * La fonction equals doit être écrite selon ce modèle, qui a été détaillé en cours
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (bareme == other.bareme) // et pas bareme.equals(other.bareme)
			return false;
		if (enonce == null) {
			if (other.enonce != null)
				return false;
		} else if (!enonce.equals(other.enonce)) // et pas enonce==other.enonce
			return false;
		return true;
	}


	public abstract double	getNote();
	public abstract void RAZReponse();
}
