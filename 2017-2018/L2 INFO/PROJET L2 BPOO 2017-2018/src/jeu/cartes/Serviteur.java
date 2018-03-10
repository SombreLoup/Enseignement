package jeu.cartes;

import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IJoueur;
import jeu.IPlateau;
import jeu.Joueur;
import jeu.Plateau;
import jeu.capacites.Capacite;
import jeu.capacites.CapaciteProvocation;

public class Serviteur extends Carte {
	private	int	attaque;
	private	int vie;
	private	int	tourEnAttente;
	private	int	tourJoue;
	private Capacite capacite;
	
	
	public Serviteur(IJoueur proprietaire, String nom, int cout, int attaque, int vie, Capacite capacite) {
		super(proprietaire, nom, cout);
		this.capacite = capacite;
		this.attaque = attaque;
		this.vie = vie;
	}
	
	public Serviteur(Serviteur serviteur) {
		super(serviteur.getProprietaire(),serviteur.getNom(), serviteur.getCout());
		this.capacite = serviteur.capacite;
		this.attaque = serviteur.attaque;
		this.vie = serviteur.vie;		
	}

	@Override
	public void executerEffetDebutTour(Object cible) throws HearthstoneException {
		tourJoue = 0;
		if (tourEnAttente>0)
			tourEnAttente--;
		if (capacite != null)
			capacite.executerEffetDebutTour();
	}

	@Override
	public void executerEffetFinTour(Object cible) throws HearthstoneException {
		if (capacite != null)
			capacite.executerEffetFinTour();
	}

	@Override
	public void executerEffetDebutMiseEnJeu(Object cible) throws HearthstoneException {
		tourEnAttente = 1;
		if (capacite != null)
			capacite.executerEffetMiseEnJeu(cible);
	}

	@Override
	public void executerEffetDisparition(Object cible) throws HearthstoneException {
		if (capacite != null)
			capacite.executerEffetDisparition(null);
	}

	@Override
	public void executerAction(Object cible) throws HearthstoneException {
		if (tourEnAttente != 0) {
			throw new HearthstoneException("Trop pressé fiston ! Tu la joueras au prochain tour...");
		}
		
		if (tourJoue != 0) {
			throw new HearthstoneException("Tu l'as déjà joué ce tour ! Tricheur !");
		}
		
		if (problemeProvocation(cible))
			throw new HearthstoneException("Impossible d'attaquer cette cible car un serviteur a provocation");

		tourJoue++;
		
		if (cible instanceof Joueur) {
			Joueur joueur = (Joueur)cible;
			
			joueur.getHeros().diminuerVie(attaque);
			if (joueur.getHeros().estMort())
				Plateau.getInstance().gagnePartie(joueur);
			
			return;
		}
			
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur)cible;
			
			try {
				serviteur.vie -= attaque;
				if (serviteur.disparait())
					serviteur.getProprietaire().perdreCarte(serviteur);
				
				this.vie -= serviteur.attaque;
				if (this.disparait())
					this.getProprietaire().perdreCarte(this);
				
			} catch (HearthstoneException e) {
				e.printStackTrace();
			}
			
			return;
		}
		
		tourJoue--;
		
		throw new HearthstoneException("La cible est inconnue, cloporte bulbeux !");
	}

	private boolean problemeProvocation(Object cible) {
		if (cible instanceof Serviteur) {
			Serviteur serviteur = (Serviteur) cible;
			if (serviteur.getCapacite() instanceof CapaciteProvocation)
				return false;
		}
		
		boolean probleme = false;
		IPlateau board = Plateau.getInstance();
		try {
			IJoueur jouerAdverse = board.getAdversaire(getProprietaire());
			
			for (ICarte carte : jouerAdverse.getJeu()) {
				Serviteur serviteur = (Serviteur)carte;
				probleme = serviteur.getCapacite() instanceof CapaciteProvocation;
				if (probleme)
					return probleme;
			}
		} catch (HearthstoneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	private Capacite getCapacite() {
		return this.capacite;
	}

	@Override
	public boolean disparait() {
		return (vie<=0);
	}
	
	@Override
	public String toString() {
		return "Serviteur ["+super.toString()+" attaque=" + attaque + "; vie=" + vie + "]"+capacite+(tourEnAttente==1?"(En attente)":"")+(tourEnAttente==0 && tourJoue==0?"(jouable)":"");
	}

	public void diminuerVie(int attaque) {
		vie -= attaque;
	}

	public void rendreJouable() {
		tourEnAttente = 0;
	}

	public void ajouterBonusVie(int bonusVie) {
		vie += bonusVie;
	}

	public void ajouterBonusAttaque(int bonusAttaque) {
		attaque += bonusAttaque;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Serviteur(this);
	}

	public int getVie() {
		return vie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + attaque;
		result = prime * result + ((capacite == null) ? 0 : capacite.hashCode());
		result = prime * result + tourEnAttente;
		result = prime * result + tourJoue;
		result = prime * result + vie;
		return result;
	}	
}
