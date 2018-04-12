import java.util.ArrayList;

public class ChoixMultiple extends Question {

	private	ArrayList<String>	listeItems = new ArrayList<String>();
	private	ArrayList<String>	bonnesReponses = new ArrayList<String>();
	ArrayList<String>	reponsesCandidat = new ArrayList<String>();
	
	public void ajouterReponseEtudiant(String item) {
		reponsesCandidat.add(item);
	}
	
	
	public ChoixMultiple(String enonce, double bareme) {
		super(enonce,bareme);
	}

	public void ajouterItem(String e, boolean bonne) {
		listeItems.add(e);
		if (bonne)
			bonnesReponses.add(e);
	}
		

	public String	get(int i) {
		return listeItems.get(i);
	}

	/**
	 * Méthode centrale de la classe mettant surtout en oeuvre vos
	 * compétences en logique et sur la bonne utilisation des ArrayList
	 */
	@Override
	public double getNote() {
		if (reponsesCandidat==null || reponsesCandidat.isEmpty())
			return 0;
		if (reponsesCandidat.size()!=bonnesReponses.size())
			return 0;
		
		boolean	correct = true;
		
		for (String item : reponsesCandidat) {
			correct = correct && bonnesReponses.contains(item);
			if (!correct)
				break;
		}
		
		if (correct)
			return getBareme();
		else
			return 0;
	}
	
	public void RAZReponse() {
		reponsesCandidat.clear();
	}
	
	/**
	 * Méthode obligatoire pour pour coller au main illustrant le sujet
	 */
	@Override
	public String toString() {
		String s = super.toString();
		
		for (int i=0; i<listeItems.size(); i++) {
			s += ""+(i+1)+": "+listeItems.get(i)+"\n";
		}
		
		return s;
	}

}
