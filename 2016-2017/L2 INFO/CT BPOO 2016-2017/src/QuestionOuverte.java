
// extends Question, et pas implements Questio
public class QuestionOuverte extends Question {
	
	private	String 	reponse = "";
	private	double	note; // Ici on a besoin de la note
	// surtout pas remettre enoncé et bareme car c'est hérité

	
	public QuestionOuverte(String enonce, double bareme) {
		super(enonce, bareme); // Doit être la première instruction !
	}

	public String getReponse() {
		return reponse;
	}


	public void setReponse(String reponse) {
		this.reponse = reponse;
	}


	public void setNote(double note) {
		this.note = note;
	}


	/**
	 * C'est un getter standard, mais qui, en l'occurrence, surcharge
	 * aussi le getter abstrait de Question
	 */
	@Override
	public double getNote() {
		return note;
	}


	/**
	 * A redéfinir pour ajouter les spécificités de QuestionOuverte
	 * Notation indulgente si la méthode est au moins présente
	 */
	@Override
	public String toString() {
		String s = super.toString(); // Cela montre que l'on a bien compris l'héritage

		if (reponse != null)
			s += " Question ouverte[reponse="+reponse+" ,note="+note+"]";
		
		return s;
	}


	@Override
	public void RAZReponse() {
		reponse = "";
		note = 0;
	}
	
	
	
}
