package monopoly;

import monopoly.actions.ToucherCaseDepart;
import plateau.Case;
import plateau.Joueur;
import plateau.Superviseur;

public class Monopoly extends Superviseur {
	public final static int	CASE_DEPART = 0;
	public final static int	BELLEVILLE = 1;
	public final static int	CAISSE1 = 2;
	public final static int	LECOURBE = 3;
	public final static int	IMPOTS = 4;
	public final static int	MONTPARNASSE = 5;
	public final static int	VAUGIRARD = 6;
	public final static int	CHANCE1 = 7;
	public final static int	COURCELLES = 8;
	public final static int	REPUBLIQUE = 9;

	Terrain belleville  = new Terrain("Belleville", 50, 20, 50);
	Terrain lecourbe  = new Terrain("Lecourbe", 50, 20, 50);

	public Monopole	famillepauvre1 = new Monopole("Pauvre");
	
	public Monopoly() {
		add(0, new Case());
		add(1, belleville);
		add(2, new Case());
		add(3, lecourbe);
		belleville.setFamille(famillepauvre1);
		lecourbe.setFamille(famillepauvre1);
		
		famillepauvre1.add(belleville).add(lecourbe);
		
		for (int i=4; i<10; i++) {
			Case c = new Case();
			add(i, c);
		}
	}

	@Override
	public void deplacerJoueur(Joueur joueur, int deplacement) {
		int	positionCourante = getIndiceCase(joueur.getCaseCourante());
		
		traiterPassageCaseDepart(joueur, deplacement, positionCourante);
		
		positionCourante = (positionCourante+deplacement) % taillePlateau();
		
		Case c = getCase(positionCourante);
		joueur.setCaseCourante(c);
		c.setDernierArrivant(joueur);
				
	}

	private void traiterPassageCaseDepart(Joueur joueur, int deplacement, int positionCourante) {
		if (positionCourante+deplacement > taillePlateau())
			new ToucherCaseDepart((JoueurMonopoly) joueur).executer();
	}

	@Override
	public void initialiserPartie() {
		for (int i=0; i<nombreJoueurs(); i++)
			getJoueur(i).setCaseCourante(getCase(CASE_DEPART));
	}


}
