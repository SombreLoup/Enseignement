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
		if (l<=6) 
			if (b.estMemeCouleur(p.getBonbon(l, c), p.getBonbon(l+1, c), p.getBonbon(l+2, c), p.getBonbon(l+3, c)))
				return new Combinaison4Vertical(b, l, c);	
		
		if ((l>=1) && (l<=7)) 
			if (b.estMemeCouleur(p.getBonbon(l-1, c), p.getBonbon(l, c), p.getBonbon(l+1, c), p.getBonbon(l+2, c)))
				return new Combinaison4Vertical(b, l-1, c);	
		
		if ((l>=2) && (l<=8)) 
			if (b.estMemeCouleur(p.getBonbon(l-2, c), p.getBonbon(l-1, c), p.getBonbon(l, c), p.getBonbon(l+1, c)))
				return new Combinaison4Vertical(b, l-2, c);	
		
		if (l>=3) 
			if (b.estMemeCouleur(p.getBonbon(l-3, c), p.getBonbon(l-2, c), p.getBonbon(l-1, c), p.getBonbon(l, c)))
				return new Combinaison4Vertical(b, l-3, c);	
		
		return null;
	}

}
