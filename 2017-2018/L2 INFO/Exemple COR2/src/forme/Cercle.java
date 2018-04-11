package forme;


public class Cercle implements IForme {

	private	int	rayon;
	private	int	x,y;
	
	public Cercle() {
	}
	
	public int getRayon() {
		return rayon;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
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

	public Cercle(int x, int y, int r) {
		this.x = x;
		this.y = y;
		this.rayon = r;
	}
	
	@Override
	public String toXML() {
		return "<Cercle x=\""+x+"\" y=\""+y+"\" rayon=\""+rayon+"\"/>";
	}

}
