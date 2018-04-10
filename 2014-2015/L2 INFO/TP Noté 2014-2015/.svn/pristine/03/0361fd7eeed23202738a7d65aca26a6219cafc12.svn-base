package idee2.rdv;

import java.util.ArrayList;

public class ReunionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6755588142453985457L;
	
	private ArrayList<Participant>	participantsIndisponibles = new ArrayList<Participant>();

	public ReunionException(Participant participant) {
		super("Participant indisponible");
		participantsIndisponibles.add(participant);
	}

	public ReunionException(ArrayList<Participant> participants) {
		super("Des participants indisponible");
		participantsIndisponibles.addAll(participants);
	}

	public ArrayList<Participant> getParticipantsIndisponibles() {
		return participantsIndisponibles;
	}
}
