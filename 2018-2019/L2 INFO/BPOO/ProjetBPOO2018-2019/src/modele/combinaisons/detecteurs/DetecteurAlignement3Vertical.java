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
		if (l<=7) // On peut tester depuis le début
			if (b.estMemeCouleur(p.getBonbon(l, c), p.getBonbon(l+1, c), p.getBonbon(l+2, c)))
				return new Combinaison3Vertical(b, l, c);	
		
		if ((l>=1) && (l<=8)) // On peut tester au milieu
			if (b.estMemeCouleur(p.getBonbon(l-1, c), p.getBonbon(l, c), p.getBonbon(l+1, c)))
				return new Combinaison3Vertical(b, l-1, c);	
		
		if (l>=2) // On peut tester à la fin
			if (b.estMemeCouleur(p.getBonbon(l-2, c), p.getBonbon(l-1, c), p.getBonbon(l, c)))
				return new Combinaison3Vertical(b, l-2, c);	
			
		
		return null;
	}

}
