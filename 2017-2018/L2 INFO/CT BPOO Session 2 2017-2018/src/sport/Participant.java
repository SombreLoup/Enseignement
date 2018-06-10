package sport;

import java.util.ArrayList;

public abstract class Participant {

	private	String	nom;
	private	ArrayList<Participation>	palmares = new ArrayList<Participation>();

	public Participant(String nom) {
		this.nom = nom;
	}

	public Participant(Participant participant) {
		this.nom = participant.nom;
		this.palmares = new ArrayList<Participation>(participant.palmares);
	}

	public String getNom() {
		return nom;
	}
	
	public void ajouterParticipation(Participation participation) throws SportException {
		if (participation == null)
			throw new SportException("Participation nulle...");
		
		if (participation.getParticipant()==null)
			this.palmares.add(participation);
	}
	
	public abstract Participant clone();
	
	public String getPalmaresString() {
		String s = "";
		
		s += "Palmares de "+getNom()+"\n";
		for (Participation participation : palmares) {
			Championat  championat = participation.getChampionat();
			s+=championat.getNom()+" en "+participation.getAnnee()+" à la place "+participation.getPlace()+"\n";
		}
		
		return s;
	}

	@Override
	public String toString() {
		String s = "Palmares de "+nom+":\n";
		for (Participation participation : palmares) {
			s += participation+"\n";
		}
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
		Participant other = (Participant) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
}
