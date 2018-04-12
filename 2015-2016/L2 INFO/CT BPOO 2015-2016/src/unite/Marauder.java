package unite;

import starcraft.Combattant;
import starcraft.MortUniteException;

public class Marauder extends Unite implements Combattant {

	public Marauder() {
		super(100, 100, 20);
	}
	
	public Marauder(Marauder marauder) {
		super(marauder);
	}

	@Override
	public void attaquer(Unite unite) throws MortUniteException {
		unite.reduirePointsDeVie(20);
		unite.setVitesse(unite.getVitesse() / 2);
		if ( unite.getPointDeVieCourant() <= 0 ) throw new MortUniteException("unité "+unite+" kaputt...");
	}

	@Override
	public Marauder clone() {
		return new Marauder(this);
	}
	
}
