package modele;

//public enum Sortes {VIDE, BLEU, VIOLET, JAUNE, VERT, BLEU_RAYE, VIOLET_RAYE, JAUNE_RAYE, VERT_RAYE }
public enum Sortes {
	VIDE, BLEU, VIOLET, JAUNE, VERT, BLEU_RAYE, VIOLET_RAYE, JAUNE_RAYE, VERT_RAYE;

	public static Sortes getSorteRaye(Sortes sorte) {
		if ((sorte.ordinal()<1) || (sorte.ordinal()>4))
				return sorte;
		else
			return values()[sorte.ordinal()+4];
	}

	public boolean estRaye() {
		// TODO Auto-generated method stub
		return ordinal()>4 && ordinal()<=8;
	}
	
	public Sortes getPasRaye() {
		if (estRaye())
			return values()[ordinal()-4];
		else
			return this;
	}	
	
	public Sortes getRaye() {
		if (estRaye())
			return values()[ordinal()+4];
		else
			return this;
	}
}