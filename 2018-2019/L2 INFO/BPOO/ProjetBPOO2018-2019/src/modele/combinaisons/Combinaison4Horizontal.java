package modele.combinaisons;

import modele.Bonbon;
import modele.Plateau;
import modele.Sortes;

public class Combinaison4Horizontal extends Combinaison {

	public Combinaison4Horizontal(Bonbon b, int l, int c) {
		super(b, l, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.getSorteRaye(getBonbon().getSorte()));
	}

	@Override
	public void viderCombinaison(Plateau p) {
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart()+1);
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart()+2);
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart()+3);
	}
	

	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getLdepart()==l) && (getCdepart()<=c) && (c<=getCdepart()+3) ;
	}

}
