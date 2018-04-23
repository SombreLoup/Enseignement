package l2fx;

public interface Handler {
	
	boolean	canHandle(Event e);
	void	handle(Event e);
	
}
