import java.util.ArrayList;

/**
 * Cette classe montre
 * - votre compréhension des exceptions
 * - votre capacité à utiliser des classes abstraites
 * - votre capacité à déclarer une constante
 * - à gérer un champ de type collection (add+size+get)
 * @author yann
 *
 */
public class Epreuve {
	final static public double	NOTE_MAX = 20;
	
	private	ArrayList<Question>	listeQuestions = new ArrayList<Question>();
	private	int		duree;
	private	int		session;
	
	public Epreuve(int duree, int session) {
		setDuree(duree);
		setSession(session);
	}

	public int getDuree() {
		return duree;
	}

	private void setDuree(int duree) {
		if (duree < 10 || duree > 240)
			throw new IllegalArgumentException("Duree incorrecte");
		
		this.duree = duree;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		if (session != 1 && session != 2)
			throw new IllegalArgumentException("La session doit être 1 ou 2");
		
		this.session = session;
	}

	public int size() {
		return listeQuestions.size();
	}

	public Question get(int index) {
		return listeQuestions.get(index);
	}

	public boolean add(Question e) throws EpreuveException {
		if (getNombrePoints()+e.getBareme()>NOTE_MAX)
			throw new EpreuveException(getNombrePoints(),e.getBareme());
		return listeQuestions.add(e);
	}
	
	public double getNombrePoints() {
		double	nbPoints = 0;
		
		for (Question question : listeQuestions) {
			nbPoints += question.getBareme();
		}
		
		return nbPoints;
	}
	
	public double getNote() {
		double	note = 0;
		
		for (Question question : listeQuestions) {
			note += question.getNote();
		}
		
		return note;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		s += "Session "+session+"\n"; 
		s += "Durée : "+duree+"\n";
		s += "Barême : "+getNombrePoints()+"\n";
		
		int i = 1;
		for (Question question : listeQuestions) {
			s += "==============================\n";
			s += "Question "+i+"\n";
			s += question;
			s += "\n";
			s += "------------------------------\n\n";
			i++;
		}
		
		return s;
	}
}
