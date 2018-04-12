package idee2.rdv;

import java.util.ArrayList;

public class Reunion {
	private	String	titre;
	
	private	Creneau	creneau;

	private	ArrayList<Participant>	participants = new ArrayList<Participant>();

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public Reunion(String titre, Creneau creneau) {
		this.titre = titre;
		this.setCreneau(creneau);
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		if (titre==null)
			throw new IllegalArgumentException();

		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Reunion [titre=" + titre + "]";
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		if (creneau==null)
			throw new IllegalArgumentException();

		this.creneau = creneau;
	}
	
	public void inviter(Participant participant) throws ReunionException {
		if (participant==null)
			throw new IllegalArgumentException();

		participants.add(participant);
		participant.ajouter(this);
		
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reunion other = (Reunion) obj;
		if (creneau == null) {
			if (other.creneau != null)
				return false;
		} else if (!creneau.equals(other.creneau))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	
}
