package main;

import monopoly.JoueurMonopoly;
import monopoly.Monopoly;
import monopoly.Terrain;
import monopoly.actions.AcheterTerrain;
import monopoly.actions.PayerLoyer;


public class Main {

	public static void main(String[] args) {
		Monopoly	partie = new Monopoly();
		JoueurMonopoly	yann = new JoueurMonopoly("Yann");
		JoueurMonopoly	cha = new JoueurMonopoly("Charlotte");
		
		partie.add(yann);
		partie.add(cha);
		
		Terrain belleville = (Terrain) partie.getCase(Monopoly.BELLEVILLE);
		Terrain lecourbe = (Terrain) partie.getCase(Monopoly.LECOURBE);
		
		

		partie.initialiserPartie();
		
		System.out.println("Yann : "+yann);
		System.out.println("Charlotte : "+cha);

		int jet;
		
		jet = 1; // pour arriver sur Belleville

		try {
			yann.executer(new PayerLoyer(belleville));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		partie.deplacerJoueur(cha, jet);
		
		try {
			cha.executer(new AcheterTerrain(belleville));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			yann.executer(new PayerLoyer(belleville));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		partie.deplacerJoueur(yann, jet);
		
		try {
			yann.executer(new PayerLoyer(belleville));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Yann : "+yann);
		System.out.println("Charlotte : "+cha);
		
		
		System.out.println("Monopole : "+partie.famillepauvre1.estComplet());
		
		jet = 2;
		
		partie.deplacerJoueur(cha, jet);

		try {
			cha.executer(new AcheterTerrain(lecourbe));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Monopole : "+partie.famillepauvre1.estComplet());
		
		partie.deplacerJoueur(yann, jet);
		
		try {
			yann.executer(new PayerLoyer(lecourbe));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
