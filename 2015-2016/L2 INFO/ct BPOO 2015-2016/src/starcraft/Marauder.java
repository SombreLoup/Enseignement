package starcraft;

public class Marauder extends Unite implements Combattant {

	public Marauder() {
		super(100, 20);
	}
	
	public Marauder(Marauder marauder) {
		super(marauder);
	}

	@Override
	public void attaquer(Unite victime) throws MortUniteException {
		victime.reduirePointsDeVie(10);
		victime.setVitesse(victime.getVitesse()/2);
	}

	@Override
	public Unite clone() {
		return new Marauder(this);
	}

	@Override
	public String toString() {
		return "Marauder [getPointsVie()=" + getPointsVieInitial() + ", getVitesse()=" + getVitesse() + "]";
	}
	
	

}
