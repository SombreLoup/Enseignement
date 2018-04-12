package idee2.rdv;

import java.util.ArrayList;

public class GroupeTravail extends Participant {
	
	private	ArrayList<Participant>	membres = new ArrayList<Participant>();

	public GroupeTravail(String nom) {
		super(nom);
	}

	public boolean add(Participant e) {
		if (e==null)
			throw new IllegalArgumentException("La personne ne doit pas être nulle");
		
		if (membres.contains(e))
			throw new IllegalArgumentException("La personne est déjà membre de ce groupe de travail");
		
		return membres.add(e);
	}

	public Participant get(int index) {
		return membres.get(index);
	}

	public boolean remove(Object o) {
		return membres.remove(o);
	}

	public int getNombrePersonnes() {
		return membres.size();
	}


	public ArrayList<Participant> getMembres() {
		return membres;
	}

	@Override
	public boolean estDisponible(Creneau creneau) {
		if (creneau==null)
			throw new IllegalArgumentException("Le creneau ne doit pas être null");
		
		boolean	b = true;
		
		for (Participant p : membres) {
			b = b && p.estDisponible(creneau);
		}
		
		return b;
	}

	@Override
	public void ajouter(Reunion reunion) throws ReunionException {
		if (reunion==null)
			throw new IllegalArgumentException();
		
		ArrayList<Participant> listeIndisponibles = new ArrayList<Participant>();
		
		for (Participant p : membres) {
			try {
				p.ajouter(reunion);
			}
			catch (ReunionException e) {
				listeIndisponibles.addAll(e.getParticipantsIndisponibles());
			}
		}
		
		if (!listeIndisponibles.isEmpty()) {
			throw new ReunionException(listeIndisponibles);
		}
	}

}
