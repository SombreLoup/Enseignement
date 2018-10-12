package modele.combinaisons;

import modele.Bonbon;
import modele.Sortes;
import modele.plateau.Plateau;

public class Combinaison4Vertical extends Combinaison {

	public Combinaison4Vertical(Bonbon b, int l, int c) {
		super(b, l, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.getSorteRaye(getBonbon().getSorte()));
	}

	@Override
	public void viderCombinaison(Plateau p) {
		comptabiliserNombrePoints(p);

		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+1, getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+2, getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+3, getCdepart());
	}
	
	
	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getCdepart()==c) && (getLdepart()<=l) && (l<=getLdepart()+3) ;
	}

}
