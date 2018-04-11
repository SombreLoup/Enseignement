package cor;

import application.Application;
import dessin.Dessin;

public class InterfaceQuitter extends Interface {
	
	public InterfaceQuitter(Interface ihm) {
		super(ihm);
	}

	@Override
	public boolean saisInteragir(String s) {
		return (getDescription().equals(s));
	}
	
	@Override
	public String getDescription() {
		return "Quitter";
	}

	@Override
	public void executerInteraction(Dessin d) throws Exception {
		Console	es = Application.es;
		
		es.println("Au revoir !");
		System.exit(0);
	}

}
