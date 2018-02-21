package tennis;

import java.util.ArrayList;

import competition.Marque;
import foot.MarqueFoot;

public class MarqueTennis implements Marque {

	private	ArrayList<Integer> set = new ArrayList<Integer>();
	private	int	setCourant = 0;
	private	int	jeuCourant = 0;
	
	@Override
	public boolean estSuperieur(Marque m) {
		if (m instanceof MarqueTennis) {
			MarqueTennis mf = (MarqueTennis) m;
			
			boolean estSup = true;
			int i = 0;
			while (estSup && (i<set.size())) {
				if (mf.set.get(i)>set.get(i))
					estSup = false;
				else
					i++;
			}
			if (estSup && mf.setCourant>setCourant)
				estSup = false;
			
			if (estSup && mf.jeuCourant>jeuCourant)
				estSup = false;
			
			return false;
		}

		return false;
	}

	@Override
	public String getValeur() {
		String s = "";
		for (Integer score : set) {
			s = s+score+" ";
		}
		s += setCourant+" ";
		
		if (jeuCourant==50)
			s += "Eg ";
		else if (jeuCourant==60)
			s += "Av ";
		else
			s += jeuCourant+" ";
		return s;
	}

	public boolean add(Integer e) {
		return set.add(e);
	}
	
	public int getNombreSets() {
		return set.size();
	}

	public void setSetCourant(int setCourant) {
		this.setCourant = setCourant;
	}

	public void setJeuCourant(int jeuCourant) {
		this.jeuCourant = jeuCourant;
	}

	public ArrayList<Integer> getSet() {
		return set;
	}

	public int getSetCourant() {
		return setCourant;
	}

	public int getJeuCourant() {
		return jeuCourant;
	}

}
