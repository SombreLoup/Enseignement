package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison4Vertical;

public class DetecteurAlignement4Vertical extends DetecteurCombinaison {
	
	public DetecteurAlignement4Vertical(DetecteurCombinaison d) {
		super(d);
	}
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		if (l>6) 
			return null;
		
		if (p.getBonbon(l, c).getSorte().equals(b.getSorte()) && p.getBonbon(l+1, c).getSorte().equals(b.getSorte()) && p.getBonbon(l+2, c).getSorte().equals(b.getSorte()) && p.getBonbon(l+3, c).getSorte().equals(b.getSorte()))
			return new Combinaison4Vertical(b, l, c);	
		
		return null;
	}

}
