package app;

import boisson.Boisson.ETemperature;
import boisson.BoissonBase;
import boisson.BoissonException;

public class AppBoisson {

	public static void main(String[] args) {
		try {
			BoissonBase badoit = new BoissonBase("Badoit", ETemperature.FRAICHE, 0.2);
			badoit.setPetillante(true);
			System.out.println(badoit);
		} catch (BoissonException e) {
			e.printStackTrace();
		}
		
	}

}
