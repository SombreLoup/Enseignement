package dao;

import java.util.List;

import core.Candidat;

public interface DAOCandidat {
	
	/**
	 * Sauvegarde le candidat  
	 * @param candidat
	 * @throws IllegalArgumentException Exception lancée lorsque le candidat est déjà persistent (code != -1) 
	 */
	void save(Candidat candidat);
	
	/**
	 * Renvoie la liste de tous les candidats présents dans la base
	 * @return La liste des candidats présents dans la base
	 */
	List<Candidat>	getTous();
	
	/**
	 * Renvoie le nombre de voix total du candidat
	 * @param code C'est la clé primaire du candidat
	 */
	int	getResultatsComplets(int code);

	/**
	 * Renvoie le candidat sélectionné à partir de sa clé primaire
	 * @param codeCandidat Le code du candidat à rechercher
	 * @return Le candidat ou null si pas pas dans la base
	 */
	Candidat get(int codeCandidat);
}
