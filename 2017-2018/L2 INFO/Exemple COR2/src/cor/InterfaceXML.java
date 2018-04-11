package cor;

import application.Application;
import dessin.Dessin;

public class InterfaceXML extends Interface {

	public InterfaceXML(Interface ihm) {
		super(ihm);
	}


	@Override
	public void executerInteraction(Dessin d) throws Exception {
		Application.es.println(d.toXml());
	}


	@Override
	public String getDescription() {
		return "Générer XML";
	}


	@Override
	public boolean saisInteragir(String actionDemandee) {
		return (getDescription().equals(actionDemandee));
	}

}
