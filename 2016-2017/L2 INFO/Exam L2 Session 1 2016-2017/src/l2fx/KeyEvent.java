package l2fx;

public class KeyEvent extends Event {

	private	int	key = 0;
	
	public KeyEvent(int x, int y) {
		super(x, y);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "KeyEvent ["+super.toString()+", key=" + key + "]";
	}
}
