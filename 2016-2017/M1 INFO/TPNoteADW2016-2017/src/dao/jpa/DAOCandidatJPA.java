package dao.jpa;

import java.util.List;

import javax.persistence.Query;

import core.Candidat;
import dao.DAOCandidat;

public class DAOCandidatJPA extends DAOJPA implements DAOCandidat {

	private	 DAOCandidatJPA() {
	}
	
	private static DAOCandidat instance = null;
	
	public static DAOCandidat getInstance() {
		if (instance==null)
			instance = new DAOCandidatJPA();
		return instance;
	}
	
	@Override
	public void save(Candidat candidat) {
		// TODO DAOCandidatJPA.save
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Candidat> getTous() {
		// TODO DAOCandidat.getTous
		return null;
	}

	@Override
	/**
	 * J'ai un peu galéré pour mettre au point cette fonction. Alors, je vous la laisse !
	 * C'est cadeau !
	 * Rq : Avant que vous ne posiez des questions, elle marche. Elle est testée ! Donc
	 * si vous avez des erreurs quelques part, elles ne viennent pas de cette fonction...
	 */
	public int getResultatsComplets(int code) {
		Query query = getManager().createNativeQuery("SELECT sum(resultat_partiel.voix) FROM resultat_partiel,bureau_vote WHERE resultat_partiel.code_cand=? AND resultat_partiel.num_bur=bureau_vote.num_bur")
								  .setParameter(1, code);
		Object obj = query.getSingleResult();
		if (obj==null)
			return 0;
		else
			return ((Number)obj).intValue();

	}

	@Override
	public Candidat get(int codeCandidat) {
		// TODO DAOCandidatJPA.get
		return null;
	}

}
