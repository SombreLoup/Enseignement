package starcraft;

public class MortUniteException extends Exception {
	private Unite leMort;
	
	public MortUniteException() {
		super();
	}

	public MortUniteException(Unite unite) {
		leMort = unite;
	}

	public Unite getLeMort() {
		return leMort;
	}
	
	
}
