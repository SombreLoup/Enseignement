package tennis;

import competition.Competiteur;
import competition.Rencontre;
import competition.exception.CompetiteurInexistantException;
import competition.exception.RencontreIncompleteException;

public class MatchTennis extends Rencontre {
	
	@Override
	public void marque(Competiteur celuiQuiMarque) throws CompetiteurInexistantException {
		if (isEncours()) {
			MarqueTennis m1 = (MarqueTennis)getMarque(celuiQuiMarque);

			MarqueTennis m2 = (MarqueTennis) getMarque(adversaire(celuiQuiMarque));
			
			
			boolean jeuGagne = false;
			if (m1.getSetCourant()==6)
				jeuGagne = traiterTieBreak(m1,m2);
			else
				jeuGagne = traiterJeuNormal(m1,m2);
			
			if (jeuGagne) {
				m1.setJeuCourant(0);
				m1.setSetCourant(m1.getSetCourant()+1);		
				m2.setJeuCourant(0);
			}
			
			boolean setGagne = false;
			if (estSetTermine(m1, m2)) {
				traiteFinSet(m1, m2);
				setGagne = true;
			}
			
			if (setGagne) {
				int nbSetGagnes = 0; 
				int	nbSets = m1.getNombreSets();
				for (int i=0; i<nbSets; i++) {
					if (m1.getSet().get(i).intValue()>m2.getSet().get(i).intValue())
						nbSetGagnes++;
				}
				
				if (nbSets>=2 && (nbSetGagnes==2)) {
					terminer();
					setVainqueur(celuiQuiMarque);
				}
			}

		}
	}

	private void traiteFinSet(MarqueTennis m1, MarqueTennis m2) {
		m1.add(m1.getSetCourant());
		m2.add(m2.getSetCourant());
		m1.setSetCourant(0);
		m2.setSetCourant(0);
	}

	private boolean estSetTermine(MarqueTennis m1, MarqueTennis m2) {
		return (m1.getSetCourant()==6 && m2.getSetCourant()<=4) || m1.getJeuCourant()==7;
	}

	private Competiteur adversaire(Competiteur celuiQuiMarque) throws CompetiteurInexistantException {
		if (getCompetiteur(0).equals(celuiQuiMarque))
			return getCompetiteur(1);
		else
			return getCompetiteur(0);
	}

	private boolean traiterJeuNormal(MarqueTennis m1, MarqueTennis m2) {
		int jeu1 = m1.getJeuCourant();
		int jeu2 = m2.getJeuCourant();

		boolean jeuGagne = false;
				
		if (jeu1==0)
			jeu1 = 15;
		else if (jeu1==15)
			jeu1 = 30;
		else if (jeu1==30 && jeu2!=45)
			jeu1 = 45;
		else if (jeu1==30 || jeu2==45) {
			jeu1 = 50;
			jeu2 = 50;
			m2.setJeuCourant(jeu2);
		}
		else if (jeu1==45 || jeu1==60){
			jeuGagne = true;
		}
		else if (jeu2==60) {
			jeu1 = 50;
			jeu2 = 50;
			m2.setJeuCourant(jeu2);
		}
		else {
			jeu1 = 60;
			jeu2 = 50;
			m2.setJeuCourant(jeu2);
		}
	
		m1.setJeuCourant(jeu1);
		
		return jeuGagne;
	}

	private boolean traiterTieBreak(MarqueTennis m1, MarqueTennis m2) {
		int jeu1 = m1.getJeuCourant();
		int jeu2 = m2.getJeuCourant();
		
		boolean estGagne = false;
		
		jeu1++;
		m1.setJeuCourant(jeu1);
		
		if (jeu1>=7 && jeu1>=jeu2+2)
			estGagne = true;
		
		return estGagne;
	}

	@Override
	public void abandon(Competiteur celuiQuiAbandonne) throws CompetiteurInexistantException {
		setVainqueur(adversaire(celuiQuiAbandonne));
		terminer();
	}
	
	@Override
	public String toString() {
		String s = "";
		try {
			s += getMarque(getCompetiteur(0)).getValeur()+"\n";
			s += getMarque(getCompetiteur(1)).getValeur()+"\n";
		} catch (CompetiteurInexistantException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void debuter() throws RencontreIncompleteException {
		if (getNombreParticipants()==2)
			complete();
		super.debuter();
	}
	
	
}
