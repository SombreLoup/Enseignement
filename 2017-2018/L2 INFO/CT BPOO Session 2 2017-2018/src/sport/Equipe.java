package sport;

import java.util.ArrayList;

public class Equipe extends Participant {

	private	ArrayList<Sportif>	membre = new ArrayList<Sportif>();
	
	public Equipe(String nom) {
		super(nom);
	}
	
	public Equipe(Equipe equipe) {
		super(equipe);
		this.membre = new ArrayList<Sportif>(equipe.membre);
	}

	public boolean ajouterMembre(Sportif e) {
		return membre.add(e);
	}

	@Override
	public String toString() {
		String s =  "Equipe ["+getNom()+ "]\n";
		
		for (Sportif sportif : membre) {
			s += sportif+"\n";
		}
		
		return s;
	}

	@Override
	public Participant clone() {
		return new Equipe(this);
	}
	
	

}
