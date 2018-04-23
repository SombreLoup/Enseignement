package monopoly.actions;

import monopoly.JoueurMonopoly;
import monopoly.Monopole;
import monopoly.Terrain;
import plateau.Action;

public class PayerLoyer extends Action {
	private	Terrain			terrain;
	
	public PayerLoyer(Terrain terrain) {
		this.terrain = terrain;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	@Override
	public void executer() throws Exception {
		JoueurMonopoly visiteur = (JoueurMonopoly) terrain.getDernierArrivant();

		if (terrain.getProprietaire()==null)
			throw new IllegalArgumentException("Pas encore de propriétaire");
		
		if (visiteur==null)
			throw new IllegalArgumentException("Le joueur est encore null");
		
		if (visiteur==terrain.getProprietaire())
			return;
		
		int	loyer = terrain.getLoyer();
		Monopole monopole = terrain.getFamille();
		if (monopole.estComplet()) {
			loyer *= 2;
			System.out.println("Loyer doublé");
		}
		
		if (visiteur.getCompte()<loyer)
			throw new Exception("Le joueur n'a pas assez d'argent pour le loyer");
		
		JoueurMonopoly proprio = terrain.getProprietaire();
		if (proprio==null)
			return;
		
		proprio.setCompte(proprio.getCompte()+loyer);
		visiteur.setCompte(visiteur.getCompte()-loyer);
	}
}
