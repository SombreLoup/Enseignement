package competition;
import java.util.ArrayList;

import competition.exception.CompetiteurInexistantException;
import competition.exception.DoubleException;
import competition.exception.RencontreIncompleteException;

public abstract class Rencontre {
	public enum Etat {INCOMPLETE, COMPLET, ENCOURS, TERMINEE};
	
	
	// *************** Partie abstraite **************
	
	public abstract void		marque(Competiteur celuiQuiMarque) throws CompetiteurInexistantException;
	public abstract void		abandon(Competiteur celuiQuiAbandonne) throws CompetiteurInexistantException;
	
	// **************** Partie concrète **************

	private	ArrayList<Participant>	participants = new ArrayList<Participant>();
	private	 Etat					etat;
	private	Competiteur				vainqueur = null;

	
	public Competiteur getVainqueur() {
		return vainqueur;
	}
	
	public void setVainqueur(Competiteur vainqueur) {
		this.vainqueur = vainqueur;
	}
	
	public Competiteur	getGagnant() throws RencontreIncompleteException {
		if (etat!=Etat.ENCOURS && etat != Etat.TERMINEE)
			throw new RencontreIncompleteException("La rencontre n'a pas encore commencée car elle est incomplete ou pas encore débutée");
		
		Participant	vainqueur = participants.get(0);
		for (int i = 1; i < participants.size(); i++) {
			if (participants.get(i).getMarque().estSuperieur(vainqueur.getMarque()))
				vainqueur = participants.get(i);
		}
		return vainqueur.getCompetiteur();
	}
	
	
	public int getNombreParticipants() {
		return participants.size();
	}
	
	
	public Competiteur getCompetiteur(int index) {
		validationIndice(index);
		return participants.get(index).getCompetiteur();	
	}
	
	public void addCompetiteur(Competiteur competiteur) throws DoubleException {
		validationCompetiteurAdd(competiteur);
		participants.add(new Participant(competiteur));
	}
	
	public void removeCompetiteur(Competiteur competiteur) throws CompetiteurInexistantException {
		validationCompetiteurRemove(competiteur);
		participants.add(new Participant(competiteur));
	}
	
	private void validationCompetiteurRemove(Competiteur competiteur) throws CompetiteurInexistantException {
		if (competiteur==null)
			throw new NullPointerException("Le compétiteur est null");
		if (! participants.contains(competiteur))
			throw new CompetiteurInexistantException("Le compétiteur fait déjà partie des participants");
	}
	
	private void validationCompetiteurAdd(Competiteur competiteur) throws DoubleException {
		if (competiteur==null)
			throw new NullPointerException("Le compétiteur est null");
		if (participants.contains(competiteur))
			throw new DoubleException("Le compétiteur fait déjà partie des participants");
	}
	
	public Marque getMarque(Competiteur competiteur) throws CompetiteurInexistantException {
		for (Participant participant : participants) {
			if (participant.getCompetiteur().equals(competiteur))
				return participant.getMarque();
		}
		throw new CompetiteurInexistantException("Le compétiteur "+competiteur.getNom()+" n'existe pas dans la rencontre");
	}
	
	private void validationIndice(int index) {
		if (index<0 || index>=participants.size())
			throw new IndexOutOfBoundsException("L'indice du participant est incorrect");
	}
	
	public void complete() {
		etat = Etat.COMPLET;
	}
	
	public void debuter() throws RencontreIncompleteException {
		if (etat != Etat.COMPLET)
			throw new RencontreIncompleteException("La rencontre ne peut pas débuter car elle est incomplete");
		etat = Etat.ENCOURS;
	}
	
	public void terminer() {
		etat = Etat.TERMINEE;
	}
	
	public Rencontre() {
		etat = Etat.INCOMPLETE;
	}
	
	public boolean	isTerminee() {
		return etat==Etat.TERMINEE;
	}
	
	public boolean	isEncours() {
		return etat==Etat.ENCOURS;
	}
	
	public boolean	isIncomplete() {
		return etat==Etat.ENCOURS;
	}
	
	public void setMarque(int iCompetiteur, Marque m) {
		participants.get(iCompetiteur).setMarque(m);
	}
	
}
