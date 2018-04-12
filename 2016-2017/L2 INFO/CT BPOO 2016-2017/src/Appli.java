
public class Appli {

	public static void main(String[] args) {

		
		
		// ****** Création d'une épreuve complète *******
		// **** Cette partie est normalement l'oeuvre de l'autreur de l'épreuve *****
		Epreuve epreuve = new Epreuve(120, 1); // 2h, en session 1

		// Création d'une QCM, un seul item est correct. La question vaut 1 point
		ChoixMultiple q1 = new ChoixMultiple("Quelle est la couleur du cheval blanc d'Henry IV ?", 1);
		q1.ajouterItem("Noir", false);
		q1.ajouterItem("Blanc", true);
		q1.ajouterItem("Gris", false);
		q1.ajouterItem("Rouge", false);

		// Création d'une question ouverte, qui vaut 5 points
		QuestionOuverte q2 = new QuestionOuverte("Etre ou ne pas être ?", 5);

		
		// On ajoute les 2 questions à l'épreuve. On gère proprement les exceptions
		try {
			epreuve.add(q1);
			epreuve.add(q2);
		} catch (EpreuveException e) {
			System.out.println(e);
		}
		
		System.out.println("\n\n");
		System.out.println("Epreuve de BPOO");
		System.out.println("=============================\n");
		System.out.println(epreuve);
		
		// ****** Un étudiant passe l'épreuve *******
		
		// Sa réponse pour la QCM
		q1.ajouterReponseEtudiant(q1.get(1));
		
		// Sa réponse pour la question ouverte
		q2.setReponse("Bof ! c'est quand même mieux d'être que de ne pas être...");

		// ****** Un correcteur corrige, seulement la question ouverte...
		// Il fixe tout simplement la note (Il a pas beaucoup aimé la réponse...
		q2.setNote(1);
		
		// ******* Affichage de la note après correction des réponses
		System.out.println("Note de l'étudiant : "+epreuve.getNote());
		// Il a 1 point pour la couleur du cheval blanc d'H4, plus le 1 en philo...ça fait 2

	}

}
