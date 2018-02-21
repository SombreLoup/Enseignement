package foot;
import competition.Competiteur;
import competition.Marque;

public class EquipeFoot extends Competiteur {

	public EquipeFoot(String nomEquipe) {
		super(nomEquipe);
	}

	@Override
	public Marque getMarqueInitiale() {
		return new MarqueFoot();
	}

}
