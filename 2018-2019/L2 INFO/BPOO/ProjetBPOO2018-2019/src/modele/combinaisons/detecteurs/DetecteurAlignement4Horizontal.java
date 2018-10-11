package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison4Horizontal;

public class DetecteurAlignement4Horizontal extends DetecteurCombinaison {
	
	public DetecteurAlignement4Horizontal(DetecteurCombinaison d) {
		super(d);
	}
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		if (c<=6) 
			if (b.estMemeCouleur(p.getBonbon(l, c), p.getBonbon(l, c+1), p.getBonbon(l, c+2), p.getBonbon(l, c+3)))
				return new Combinaison4Horizontal(b, l, c);

		if ((c>=1) && (c<=7)) 
			if (b.estMemeCouleur(p.getBonbon(l, c-1), p.getBonbon(l, c), p.getBonbon(l, c+1), p.getBonbon(l, c+2)))
				return new Combinaison4Horizontal(b, l, c-1);

		if ((c>=2) && (c<=8)) 
			if (b.estMemeCouleur(p.getBonbon(l, c-2), p.getBonbon(l, c-1), p.getBonbon(l, c), p.getBonbon(l, c+1)))				
				return new Combinaison4Horizontal(b, l, c-2);

		if (c>=3) 
			if (b.estMemeCouleur(p.getBonbon(l, c-3), p.getBonbon(l, c-2), p.getBonbon(l, c-1), p.getBonbon(l, c)))
				return new Combinaison4Horizontal(b, l, c-3);
		
		
		return null;
	}

}
