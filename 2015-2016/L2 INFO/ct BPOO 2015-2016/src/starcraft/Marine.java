package starcraft;

public class Marine extends Unite implements Combattant {

	
	public Marine() {
		super(40,30);
	}
	
	public Marine(Marine marine) {
		super(marine);
	}

	@Override
	public void attaquer(Unite victime) throws MortUniteException {
		victime.reduirePointsDeVie(5);
	}

	@Override
	public Unite clone() {
		return new Marine(this);
	}

	
	@Override
	public String toString() {
		return "Marine [getPointsVieCourant()=" + getPointsVieCourant() + ", getVitesse()=" + getVitesse() + "]";
	}

}
