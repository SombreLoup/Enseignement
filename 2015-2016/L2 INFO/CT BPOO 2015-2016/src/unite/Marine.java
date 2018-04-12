package unite;

import starcraft.Combattant;
import starcraft.MortUniteException;

public class Marine extends Unite implements Combattant {
	
	public Marine() {
		super(40, 40, 30);
	}
	
	public Marine(Marine marine) {
		super(marine);
	}

	@Override
	public void attaquer(Unite unite) throws MortUniteException {
		unite.reduirePointsDeVie(5);
		if ( unite.getPointDeVieCourant() <= 0 ) throw new MortUniteException("Ce marine est un vrai Rambo. Il a zogouillé l'unité "+unite);
	}

	@Override
	public Marine clone() {
		return new Marine(this);
	}

}
