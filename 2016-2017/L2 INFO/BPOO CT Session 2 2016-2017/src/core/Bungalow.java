package core;

import core.Hebergement;;

public class Bungalow extends Hebergement {
	
	private	int surface;

	public Bungalow(int identifiant, String designation, int surface) throws HebergementException {
		super(identifiant, designation);
		this.surface = surface;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	@Override
	public double getTarifNuit() {
		return getCapacite()*110;
	}

	@Override
	public int getCapacite() {
		return surface/10;
	}

	@Override
	public String toString() {
		return "Bungalow ["+super.toString() + ", surface=" + surface + "]";
	}
}
