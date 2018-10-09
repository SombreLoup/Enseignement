package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3VerticalRaye;

public class DetecteurAlignement3VerticalRaye extends DetecteurCombinaison {
	
	public DetecteurAlignement3VerticalRaye(DetecteurCombinaison d) {
		super(d);
	}
	
	private boolean convient(Bonbon b1, Bonbon b2) {
		return b1.getSorte().getPasRaye().equals(b2.getSorte().getPasRaye());
	}
	
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		if (l>7) 
			return null;
		
		if (aucunRaye(l, c, p))
			return null;
		
		if (convient(p.getBonbon(l, c),b) && convient(p.getBonbon(l+1, c),b) && convient(p.getBonbon(l+2, c),b)) {
			System.out.println("3 vertical rayé");
			return new Combinaison3VerticalRaye(b, l, c);
		}
		
		return null;
	}

	private boolean aucunRaye(int l, int c, Plateau p) {
		return !p.getBonbon(l, c).getSorte().estRaye() && !p.getBonbon(l+1, c).getSorte().estRaye() && !p.getBonbon(l+2, c).getSorte().estRaye();
	}

}
