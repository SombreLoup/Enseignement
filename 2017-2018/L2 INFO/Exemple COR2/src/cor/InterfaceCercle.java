package cor;

import application.Application;
import dessin.Dessin;
import forme.Cercle;

public class InterfaceCercle extends Interface {
	
	public InterfaceCercle(Interface ihm) {
		super(ihm);
	}

	@Override
	public boolean saisInteragir(String s) {
		return (getDescription().equals(s));
	}
	
	@Override
	public String getDescription() {
		return "Construire un cercle";
	}

	@Override
	public void executerInteraction(Dessin d) throws Exception {
		Console	es = Application.es;
		
		es.println("Saisie d'un cercle");
		es.println("==================");
		es.print("Abscisse du centre : ");
		int x = es.readInt();
		
		es.print("Ordonnée du centre : ");
		int y = es.readInt();
		
		es.print("Rayon : ");
		int r = es.readInt();

		d.add(new Cercle(x,y,r));
	}

}
