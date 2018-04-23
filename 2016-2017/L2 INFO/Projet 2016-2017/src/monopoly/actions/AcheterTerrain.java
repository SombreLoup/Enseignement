package monopoly.actions;

import monopoly.JoueurMonopoly;
import monopoly.Terrain;
import plateau.Action;

public class AcheterTerrain extends Action {
	private	Terrain			terrain;
	
	public AcheterTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	@Override
	public void executer() throws Exception {
		JoueurMonopoly visiteur = (JoueurMonopoly) terrain.getDernierArrivant();

		if (terrain.getProprietaire()!=null)
			throw new IllegalArgumentException("Déja un propriétaire");
		
		if (visiteur==null)
			throw new IllegalArgumentException("Personne n'est arrivé sur ce terrain");
		
		if (visiteur.getCompte()<terrain.getCoutTerrain())
			throw new Exception("Le joueur n'a pas assez d'argent pour acheter le terrain");
		
		terrain.setProprietaire(visiteur);
		
		visiteur.setCompte(visiteur.getCompte()-terrain.getCoutTerrain());
	}

}
