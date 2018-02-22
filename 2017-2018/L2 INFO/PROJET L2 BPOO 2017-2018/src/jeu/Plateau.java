package jeu;

import java.util.ArrayList;


public class Plateau implements IPlateau {
	/******
	 * Singleton
	 */
	private	static IPlateau	plateau = null;
	
	public static IPlateau getInstance() {
		if (plateau==null)
			plateau = new Plateau();
		return plateau;
	}
	
	/*****
	 * L'objet Plateau
	 */
	private	IJoueur	joueurCourant = null;

	private	Plateau() {
		estEnCours = false;
	}
	
	private boolean estEnCours;
	private	ArrayList<IJoueur>	joueurs = new ArrayList<IJoueur>();

	/* (non-Javadoc)
	 * @see jeu.IPlateau#getAdversaire(jeu.IJoueur)
	 */
	@Override
	public IJoueur getAdversaire(IJoueur joueur) throws HearthstoneException {
		if (! estEnCours)
			throw new HearthstoneException("La partie n'est pas encore démarrée....");
		
		if (joueurs.get(0)==joueur)
			return joueurs.get(1);
	
		return joueurs.get(0);
	}

	@Override
	public void ajouterJoueur(IJoueur joueur) throws HearthstoneException {
		if (estEnCours)
			throw new HearthstoneException("La partie est déjà démarrée....");
		if (joueurs.size()==2)
			throw new HearthstoneException("La partie complète....");
		if (joueur==null)
			throw new HearthstoneException("Pas de joueur null dans la partie....");
		if (joueurs.contains(joueur))
			throw new HearthstoneException("T'es déjà dans la partie ! Ectoplasme !....");

		joueurs.add(joueur);
	}

	@Override
	public void demarrerPartie() throws HearthstoneException {
		if (estEnCours)
			throw new HearthstoneException("La partie est déjà démarrée....");
		if (joueurs.size()!=2)
			throw new HearthstoneException("La partie n'est pas complète....");
		int num = (int)(Math.random()+0.5);
		joueurCourant = joueurs.get(num);
		joueurCourant.prendreTour();
		estEnCours = true;
	}

	/* (non-Javadoc)
	 * @see jeu.IPlateau#finTour(jeu.IJoueur)
	 */
	@Override
	public void finTour(IJoueur joueur) throws HearthstoneException {
		if (! estEnCours)
			throw new HearthstoneException("La partie n'est pas encore démarrée....");
		if (joueurCourant != joueur)
			throw new HearthstoneException("C'est pas ton tour, blaireau !");
		
		IJoueur	adversaire = getAdversaire(joueur);
		adversaire.prendreTour();
	}

	@Override
	public void terminerPartie(Joueur joueur) {
		estEnCours = false;
		String s = "* "+joueur.getPseudo()+" a gagné ! *";
		String stars = "";
		for (int i = 0; i < s.length(); i++) 
			stars += "*";
		
		System.out.println(stars);
		System.out.println(s);
		System.out.println(stars);
	}

	@Override
	public boolean estDemarree() {
		return estEnCours;
	}
	
	private String toStringMainJoueurCourant() {
		String s = "";
		s +="### Ta main ###\n";
		s +="###############\n";
		if (getJoueurCourant().getMain().size()==0) {
			s +="Ben, ta main est vide....\n";
		}
		else {
			for (ICarte c : getJoueurCourant().getMain()) {
				s +="### "+c+"\n";
			}
		}
		s += "###############\n";
		
		return s;
	}
	
	@Override
	public String toString() {
		String	s = "";
		
		s += "**************************************************\n";
		s += joueurs.get(0).getPseudo()+"-->"+joueurs.get(0).getHeros()+"\n";
		s += "Stock de mana : "+joueurs.get(0).getStockMana()+"\n";
		
		if (joueurs.get(0).getHeros().getPouvoir()==null)
			s += "C'est un minable héros qui n'a aucun pouvoir\n";
		else
			s += "Pouvoir du héros : "+joueurs.get(0).getHeros().getPouvoir().getNom() + "-->" + joueurs.get(0).getHeros().getPouvoir().getDescription() + "\n";
		
		if (joueurCourant==joueurs.get(0)) {
			s += ">>>> TOUR <<<<\n";
			s += toStringMainJoueurCourant();
		}
		
				s += "\n";
		
		s += "==================================\n";
		for (ICarte carte : joueurs.get(0).getJeu()) {
			s += carte+"\n";
		}
		s += "==================================\n";
		s += "----------------------------------\n";
		s += "==================================\n";
		for (ICarte carte : joueurs.get(1).getJeu()) {
			s += carte+"\n";
		}
		s += "==================================\n";
		s += joueurs.get(1).getPseudo()+"-->"+joueurs.get(1).getHeros()+"\n";
		s += "Stock de mana : "+joueurs.get(1).getStockMana()+"\n";
		
		if (joueurs.get(1).getHeros().getPouvoir()==null)
			s += "C'est un minable héros qui n'a aucun pouvoir\n";
		else
			s += "Pouvoir du héros : "+joueurs.get(1).getHeros().getPouvoir().getNom() + "-->" + joueurs.get(1).getHeros().getPouvoir().getDescription() + "\n";
		
		if (joueurCourant==joueurs.get(1)) {
			s += ">>>> TOUR <<<<\n";
			s += toStringMainJoueurCourant();
		}
		
		s += "**************************************************\n";
		
		return s;
	}

	@Override
	public IJoueur getJoueurCourant() {
		return joueurCourant;
	}

	@Override
	public void setJoueurCourant(IJoueur joueur) throws HearthstoneException {
		if (! joueurs.contains(joueur))
			throw new HearthstoneException("Tu ne joues même pas, troll de pacotille !");
		if (joueur==joueurCourant)
			return;
		
		joueurCourant = joueur;
			
	}
}
