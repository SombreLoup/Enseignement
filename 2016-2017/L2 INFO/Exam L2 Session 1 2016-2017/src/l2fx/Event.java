package l2fx;

public class Event {

	private	int	x,y;	// Position de la souris lors de l'évènement

	public Event(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Event [x=" + x + ", y=" + y + "]";
	}
	
}
