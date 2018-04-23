package l2fx;

public class MouseButtonEvent extends Event {
		
	private boolean	pressed = false;
	
	public MouseButtonEvent(int x, int y) {
		super(x, y);
	}

	public boolean isPressed() {
		return pressed;
	}

	public void press() {
		this.pressed = true;
	}

	public void release() {
		this.pressed = false;
	}

	@Override
	public String toString() {
		return "ButtonEvent ["+super.toString()+", pressed=" + pressed + "]";
	}
	
	
}
