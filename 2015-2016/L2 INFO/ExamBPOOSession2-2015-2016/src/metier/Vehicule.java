package metier;

import java.util.ArrayList;

public class Vehicule {
	
	private	String	marque;
	private	String	immat;
	private	ArrayList<Intervention>	interventions = new ArrayList<Intervention>();
	
	public Vehicule(String marque, String immat) {
		this.marque = marque;
		this.immat = immat;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getImmat() {
		return immat;
	}

	public void setImmat(String immat) {
		this.immat = immat;
	}

	public int size() {
		return interventions.size();
	}

	public Intervention get(int index) {
		return interventions.get(index);
	}

	public boolean add(Intervention e) {
		return interventions.add(e);
	}

	@Override
	public String toString() {
		return "Vehicule [marque=" + marque + ", immat=" + immat + ", interventions=" + interventions + "]";
	}

	
}
