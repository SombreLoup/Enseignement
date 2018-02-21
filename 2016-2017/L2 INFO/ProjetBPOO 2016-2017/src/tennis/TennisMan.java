package tennis;

import competition.Competiteur;
import competition.Marque;

public class TennisMan extends Competiteur {

	public TennisMan(String string) {
		super(string);
	}

	@Override
	public Marque getMarqueInitiale() {
		MarqueTennis	m = new MarqueTennis();
		return m;
	}

}
