package cor;

import dessin.Dessin;

public abstract class Interface {

	private	Interface	suivant = null;
	
	public Interface(Interface ihm) {
		suivant = ihm;
	}
	
	public Interface getSuivant() {
		return suivant;
	}

	public abstract String getDescription();
	public abstract boolean	saisInteragir(String actionDemandee);
	public abstract void	executerInteraction(Dessin d) throws Exception;	
	
	
	public void interagir(String actionDemandees, Dessin d) throws Exception {
		if (saisInteragir(actionDemandees))
			executerInteraction(d);
		else if (suivant != null)
			suivant.interagir(actionDemandees,d);
		else
			throw new InteractionException("Il n'existe aucune interaction pour "+actionDemandees);
	}


}
