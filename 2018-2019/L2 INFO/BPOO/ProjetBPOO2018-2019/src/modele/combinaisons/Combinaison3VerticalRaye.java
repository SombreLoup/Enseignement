package modele.combinaisons;

import modele.Bonbon;
import modele.Sortes;
import modele.plateau.Plateau;

public class Combinaison3VerticalRaye extends Combinaison {

	public Combinaison3VerticalRaye(Bonbon b, int l, int c) {
		super(b, l, c);
		setNombrePoints(60);
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.VIDE);
	}

	@Override
	public void viderCombinaison(Plateau p) {
		comptabiliserNombrePoints(p);

		for (int l=0; l<10; l++)
			p.setBonbon(new Bonbon(Sortes.VIDE), l, getCdepart());
	}
	
	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getCdepart()==c) && (getLdepart()<=l) && (l<=getLdepart()+2) ;
	}

}
