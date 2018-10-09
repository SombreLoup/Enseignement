package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3Vertical;

public class DetecteurAlignement3Vertical extends DetecteurCombinaison {
	
	public DetecteurAlignement3Vertical(DetecteurCombinaison d) {
		super(d);
	}
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		if (l>7) 
			return null;
		
		if (p.getBonbon(l, c).getSorte().equals(b.getSorte()) && p.getBonbon(l+1, c).getSorte().equals(b.getSorte()) && p.getBonbon(l+2, c).getSorte().equals(b.getSorte()))
			return new Combinaison3Vertical(b, l, c);	
		
		return null;
	}

}
