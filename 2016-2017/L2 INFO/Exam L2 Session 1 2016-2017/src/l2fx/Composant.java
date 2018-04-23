package l2fx;

import java.util.ArrayList;

public class Composant {

	private	int	x,y;
	private	int	largeur, hauteur;
	private	ArrayList<Handler>	handlers = new ArrayList<Handler>();

	public Composant(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getNombreHandler() {
		return handlers.size();
	}

	public Handler get(int index) {
		return handlers.get(index);
	}

	public boolean addHandler(Handler e) {
		return handlers.add(e);
	}

	@Override
	public String toString() {
		return "Composant [x="+ x + ", y=" + y + "largeur=" + largeur + ", hauteur=" + hauteur + ", handlers=" + handlers + ", nombre de handlers=" + getNombreHandler() + "]";
	}
	
	public void processEvent(Event e) {
		if (! estDansComposant(e))
			return;
		
		for (Handler handler : handlers) {
			if (handler.canHandle(e))
				handler.handle(e);
		}
	}

	private boolean estDansComposant(Event e) {
		return (x<=e.getX()) && (e.getX()<=x+largeur) && (y<=e.getY()) && (e.getY()<=y+hauteur);
	}
}
