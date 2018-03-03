package jeu.cor;

import jeu.ICarte;
import jeu.capacites.CapaciteAttaqueHeros;
import jeu.cartes.Sort;

public class InterfaceSortAttaqueHeros extends InterfaceConsole {

	public InterfaceSortAttaqueHeros(InterfaceConsole ihm) {
		super(ihm);
	}

	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof Sort))
			return false;
		
		Sort sort = (Sort) obj;
		if (! (sort.getCapacite() instanceof CapaciteAttaqueHeros))
			return false;
		
		return true;
	}
	
	@Override
	protected Object executerInteragir(Object obj) {

		if (! (obj instanceof ICarte)) {
			System.err.println("L'objet est incorrecte pour un sort d'attaque -->"+obj);
			return null;
		}
		
		return ((ICarte)obj).getProprietaire();
		
	}
	
}
