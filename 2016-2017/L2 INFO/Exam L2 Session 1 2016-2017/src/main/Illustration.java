package main;

import l2fx.MouseButtonEvent;
import l2fx.Composant;
import l2fx.Event;
import l2fx.Handler;
import l2fx.KeyEvent;


class MonHandlerClavier implements Handler {

	// Ce handler ne gère que les KeyEvent, et uniquement lorsque la touche 'A' est préssée
	@Override
	public boolean canHandle(Event e) {
		
		// Est-ce que c'est un KeyEvent ?
		if ( !(e instanceof KeyEvent) )
			return false;
		KeyEvent ke = (KeyEvent)e;
		
		if (ke.getKey()=='A')
			return true;
		else
			return false;
	}

	@Override
	public void handle(Event e) {
		System.out.println("Et c'est encore un A ! \n"+e);
	}
}


public class Illustration {

	public static void main(String[] args) {
		// On crée un composant 
		Composant	composant = new Composant(100, 100, 200, 150);
		
		// On crée un hadler et on l'associe au composant
		Handler	handler = new MonHandlerClavier();
		composant.addHandler(handler);
				
		// On crée un évènement MouseButtonEvent dans le composant
		MouseButtonEvent e1 = new MouseButtonEvent(120, 150); 
		e1.press(); // C'est un click
		composant.processEvent(e1); // Normalement aucun affichage
		
		// On crée un évènement KeyEvent dans le composant mais avec la lettre 'Z'
		KeyEvent e2 = new KeyEvent(120, 150); 
		e2.setKey('Z');
		composant.processEvent(e2); // Normalement aucun affichage
		
		// On crée un évènement KeyEvent dans le composant mais avec la lettre 'A' cette fois
		KeyEvent e3 = new KeyEvent(120, 150); 
		e3.setKey('A');
		composant.processEvent(e3); // Normalement aucun affichage
	}
}
