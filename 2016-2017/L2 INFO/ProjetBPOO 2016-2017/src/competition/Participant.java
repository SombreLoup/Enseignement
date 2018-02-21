package competition;

public class Participant {
	private	Competiteur	competiteur;
	private	Marque		marque;
	
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Participant(Competiteur competiteur) {
		this.competiteur = competiteur;
		this.marque = null;
	}

	public Competiteur getCompetiteur() {
		return competiteur;
	}

	public Marque getMarque() {
		if (marque==null)
			marque = competiteur.getMarqueInitiale();
		return marque;
	}
	
	
}
