package modele.combinaisons;

import modele.Bonbon;
import modele.Plateau;
import modele.Sortes;

public class Combinaison3Vertical extends Combinaison {

	public Combinaison3Vertical(Bonbon b, int l, int c) {
		super(b, l, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.VIDE);
	}

	@Override
	public void viderCombinaison(Plateau p) {
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+1, getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+2, getCdepart());
	}
	
	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getCdepart()==c) && (getLdepart()<=l) && (l<=getLdepart()+2) ;
	}

}
