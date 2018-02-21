package foot;
import competition.Marque;

public class MarqueFoot implements Marque {
	private	int	nombreButs;

	@Override
	public boolean estSuperieur(Marque m) {
		if (m instanceof MarqueFoot) {
			MarqueFoot mf = (MarqueFoot) m;
			return this.nombreButs > mf.nombreButs;
		}
		
		return false;
	}

	@Override
	public String getValeur() {
		return ""+nombreButs;
	}

	public void incrementer() {
		nombreButs++;
	}
	
	@Override
	public String toString() {
		return ""+nombreButs;
	}

}
