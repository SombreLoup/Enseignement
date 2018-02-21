package dao;

import core.Commune;

public interface DAOCommune {
	/**
	 * Sauvegarde la commune et tous ses bureaux de vote. 
	 * @param commune
	 * @throws IllegalArgumentException Exception lancée lorsque la commune est déjà persistente (numero != -1) 
	 */
	void save(Commune commune) throws IllegalArgumentException;
	
	
	/**
	 * Recherche une commune donnée par un morceau de son nom et de son code postal. Par exemple, pour retrouver Moulins-lès-Metz (57160), 
	 * il suffit d'appeler get("Mou",57160) !
	 * @param boutDeNom
	 * @param codePostal
	 * @return la commune trouvée ou bien null si aucune commune n'a été trouvée
	 */
	Commune get(String boutDeNom, int codePostal);

	/**
	 * Met à jour la commune, par exemple après l'ajout d'un bureau de vote, ou d'un résultat partiel
	 * @throws IllegalArgumentException Exception lancée lorsque la commune n'est pas déjà persistente (numero == -1)
	 * @param commune
	 */
	void update(Commune commune) throws IllegalArgumentException;



}
