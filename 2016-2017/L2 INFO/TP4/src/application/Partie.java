package application;

import java.util.Random;

public class Partie {
	public static final int MAX_MYSTERE = 10;
	public static int		record = MAX_MYSTERE;
	
	private static Random random = new Random();

	private int nombreTentatives = 0;
	private int	nombreMysterieux;
	private boolean gagne;
	private boolean inferieur;
	private boolean nouveauRecord;
	
	public Partie() {
		gagne = false;
		nouveauRecord = false;
		nombreTentatives=0;
		nombreMysterieux=random.nextInt(MAX_MYSTERE);
	}

	public int getNombreTentatives() {
		return nombreTentatives;
	}
	public int getNombreMysterieux() {
		return nombreMysterieux;
	}

	public boolean isNouveauRecord() {
		return nouveauRecord;
	}
	public void testerProposition(int proposition) {
		nombreTentatives++;
		if (proposition==nombreMysterieux) {
			gagne = true;
			if (nombreTentatives<record) {
				record = nombreTentatives;
				nouveauRecord = true;
			}
		}
		else if (proposition<nombreMysterieux)
			inferieur = true;
		else
			inferieur = false;
	}

	public boolean isGagne() {
		return gagne;
	}

	public boolean isInferieur() {
		return inferieur;
	}

	public int getRecord() {
		return record;
	}
}
