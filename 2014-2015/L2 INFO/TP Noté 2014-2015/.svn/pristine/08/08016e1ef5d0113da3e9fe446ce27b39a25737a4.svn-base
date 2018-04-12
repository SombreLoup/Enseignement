package idee2.rdv;

import java.util.ArrayList;
import java.util.Date;


public class Personne extends Participant {

	private	String				service;
	private	ArrayList<Reunion>	agenda = new ArrayList<Reunion>();
	
	public Personne(String nom, String service) {
		super(nom);
		setService(service);
	}

	public ArrayList<Reunion> getAgenda() {
		return agenda;
	}

	public boolean add(Reunion e) {
		if (e==null)
			throw new IllegalArgumentException("La réunion ne doitpas être nulle");
		
		if (agenda.contains(e))
			throw new IllegalArgumentException("La réunion est déjà notée");
		
		return agenda.add(e);
	}

	public boolean remove(Object o) {
		return agenda.remove(o);
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		if (super.estVide(service))
			throw new IllegalArgumentException("Le service est vide");
		
		this.service = service;
	}

	ArrayList<Reunion>	getPlanningDuJour(Date jour) {
		ArrayList<Reunion>	planning = new ArrayList<Reunion>();
		
		for (Reunion reunion : agenda) {
			if (reunion.getCreneau().getDate().equals(jour))
				planning.add(reunion);
		}
		
		return planning;
	}

	@Override
	public boolean estDisponible(Creneau creneau) {
		if (creneau==null)
			throw new IllegalArgumentException("Le créneau ne doit pas être nulle");

		for (Reunion rendezVous : getPlanningDuJour(creneau.getDate())) {
			if (rendezVous.getCreneau().chevauche(creneau))
				return false;
		}
		
		return true;
	}

	@Override
	public void ajouter(Reunion reunion) throws ReunionException {
		if (reunion==null)
			throw new IllegalArgumentException();
		
		if (!estDisponible(reunion.getCreneau()))
			throw new ReunionException(this);
		
		agenda.add(reunion);
	}

	@Override
	public int getNombrePersonnes() {
		return 1;
	}

}
