package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3Horizontal;

public class DetecteurAlignement3Horizontal extends DetecteurCombinaison {
	
	public DetecteurAlignement3Horizontal(DetecteurCombinaison d) {
		super(d);
	}
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		if (c>7) 
			return null;
		
		if (p.getBonbon(l, c).getSorte().equals(b.getSorte()) && p.getBonbon(l, c+1).getSorte().equals(b.getSorte()) && p.getBonbon(l, c+2).getSorte().equals(b.getSorte()))
			return new Combinaison3Horizontal(b, l, c);
		
		
		return null;
	}

}
