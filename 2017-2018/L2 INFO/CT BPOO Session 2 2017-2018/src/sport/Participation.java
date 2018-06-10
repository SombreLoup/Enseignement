package sport;

public class Participation {

	private	Championat	championat;
	private	Participant	participant;
	private	int			place;
	private	int			annee;
	
	public Participation(Championat championat, Participant participant, int place, int annee) throws SportException {
		setChampionat(championat);
		setParticipant(participant);
		setPlace(place);
		setAnnee(annee);
	}

	public Championat getChampionat() {
		return championat;
	}

	private void setChampionat(Championat championat) throws SportException {
		if (championat==null) 
			throw new SportException("Championat nul");
		
		this.championat = championat;
	}

	public Participant getParticipant() {
		return participant;
	}

	private void setParticipant(Participant participant) throws SportException {
		if (participant==null) 
			throw new SportException("Participant nul");
		
		participant.ajouterParticipation(this);
		
		this.participant = participant;
	}

	public int getPlace() {
		return place;
	}

	private void setPlace(int place) throws SportException {
		if (place<0 || place>4)
			throw new SportException("Place incorrecte");

		this.place = place;
	}

	public int getAnnee() {
		return annee;
	}

	private void setAnnee(int annee) throws SportException {
		if (annee<1900 || annee>2018)
			throw new SportException("Annee incorrecte");

		this.annee = annee;
	}

	@Override
	public String toString() {
		String s =  participant.getNom()+ " a participé à "+ championat.getNom()+ " et à terminé à la place "+place;
		return s;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participation other = (Participation) obj;
		if (annee != other.annee)
			return false;
		if (championat == null) {
			if (other.championat != null)
				return false;
		} else if (!championat.equals(other.championat))
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		return true;
	}
}
