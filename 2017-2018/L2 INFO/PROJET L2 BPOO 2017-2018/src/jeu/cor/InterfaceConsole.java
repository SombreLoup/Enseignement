package jeu.cor;

public class InterfaceConsole {
	private	InterfaceConsole	suivant = null;
	
	public InterfaceConsole() {
	}
	
	public InterfaceConsole(InterfaceConsole suivant) {
		if (suivant==null) 
			return;
		
		this.suivant = suivant;
	}
	
	public Object interagir(Object obj) {
		if (peutInteragir(obj)) {
			return executerInteragir(obj);
		}
		else {
			if (suivant == null) {
				System.err.println("Impossible d'interagir pour cet objet : "+obj);
				return null;
			}
			
			return suivant.interagir(obj);
		}
	}

	protected Object executerInteragir(Object obj) {
		return null;
	}

	protected boolean peutInteragir(Object obj) {
		return false;
	}
}
