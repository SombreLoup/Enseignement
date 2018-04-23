package plateau;

import java.util.ArrayList;

public class Case {
	private	ArrayList<Action>	actionsPossibles = new ArrayList<Action>();
	private	Joueur				dernierArrivant;
	

	public Joueur getDernierArrivant() {
		return dernierArrivant;
	}

	public void setDernierArrivant(Joueur dernierArrivant) {
		this.dernierArrivant = dernierArrivant;
	}

	public boolean addActionPossible(Action e) {
		return actionsPossibles.add(e);
	}

	public boolean containsPossible(Action action) {
		if (action==null)
			throw new IllegalArgumentException("L'action est nulle");
		
		return actionsPossibles.contains(action);
	}	
}
