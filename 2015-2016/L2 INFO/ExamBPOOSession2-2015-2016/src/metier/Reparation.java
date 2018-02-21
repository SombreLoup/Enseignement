package metier;

import java.util.ArrayList;

public class Reparation extends Intervention {

	private	ArrayList<Piece>	listePieces = new ArrayList<Piece>();
	
	public Reparation() {
		super();
	}
	
	public int size() {
		return listePieces.size();
	}

	public Piece get(int index) {
		return listePieces.get(index);
	}

	public boolean add(Piece e) throws MarqueException {
		if (getVehicule()==null)
			throw new MarqueException("Impossible d'ajouter une pièce tant que la marque du véhicule n'est pas connue");
		if (!getVehicule().getMarque().equals(e.getMarque()))
			throw new MarqueException("Marque de la piece incompatible avec la marque de la voiture");
		return listePieces.add(e);
	}

	public Reparation(Reparation reparation) {
		super(reparation);
		for (Piece piece : reparation.listePieces) {
			listePieces.add((Piece) piece.clone());
		}
	}	
	
	@Override
	public double getCout() {
		double	cout = 0;
		
		for (Piece piece : listePieces) {
			cout += piece.getCout();
		}
		
		return cout;
	}

	@Override
	public Intervention clone() {
		return new Reparation(this);
	}

	@Override
	public String toString() {
		return "Reparation [listePieces=" + listePieces + "]";
	}

}
