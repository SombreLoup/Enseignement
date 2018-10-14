package ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.CandyException;
import modele.PartieCandyCrush;
import modele.combinaisons.Combinaison;
import modele.combinaisons.detecteurs.DetecteurCombinaison;
import modele.plateau.Plateau;
import modele.plateau.PlateauFactory;

public class CandyCrush extends Application {

	private static final double TEMPS_REMPLISSAGE = 0.1;
	private static final double TEMPS_AFFICHAGE_CASES_VIDES = 0.1;
	private static final int NOMBRE_DE_CANDIES = 10;
	private Canvas grillePane;
	private Image[] candies;
	private Timeline timeline;
	private Pane root;
	private Scene scene;
	private int xd, yd, xf, yf;
	private GraphicsContext gc;

	private Combinaison comboSpeciale = null;
	private int lBonbonSpecial;
	private int cBonbonSpecial;

	private Timeline timelineChrono;

	private PartieCandyCrush partie;
	private PanneauFooter panneauFooter;
	private PanneauTitre panneauTitre;
	private PanneauScore panneauScore;

	private void rangerComboSpeciale(Combinaison combo, int l, int c) {
		comboSpeciale = combo;
		lBonbonSpecial = l;
		cBonbonSpecial = c;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Candy Crush");

			
			initImagesCandies();
			initPartie();

			
			root = new BorderPane(grillePane);

			initTitre();
			initFooter();
			initGrille(); // construction de grillePane
			initBarreScore();

			scene = new Scene(root);

			initTimelineJeu();
			initTimelineChrono();

			primaryStage.setScene(scene);
			primaryStage.show();

			
			demarrerPartie();

		} catch (Exception e) {
			e.printStackTrace();
//			afficherException(e);
		}
	}

	private void demarrerPartie() {
		dessinerPlateau();
		panneauTitre.afficherTitre(partie.getPlateauCourant().getDesciption());

		timeline.play();
	}

	private void initFooter() {
		panneauFooter = new PanneauFooter();
		((BorderPane) root).setBottom(panneauFooter);
	}

	private void initTitre() {
		panneauTitre = new PanneauTitre();
		((BorderPane) root).setTop(panneauTitre);
	}

	private void initGrille() {
		grillePane = new Canvas(640, 640);
		((BorderPane) root).setCenter(grillePane);

		gc = grillePane.getGraphicsContext2D();

		grillePane.setOnDragDetected(new DragDetectedEvent());
		grillePane.setOnDragOver(new DragOverEvent());
		grillePane.setOnDragDropped(new DragDroppedEvent());
	}

	private void initPartie() {
		try {
			partie = new PartieCandyCrush();
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau4.csv"));
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau2.csv"));
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau3.csv"));

		} catch (CandyException e) {
			afficherException(e);
		}
	}

	private void afficherException(Exception e) {
		if (panneauFooter == null)
			e.printStackTrace();
		else
			panneauFooter.afficherMessage(e.getMessage());
	}

	public void changerPlateau() {
		if (partie.getNumeroPlateauCourant() < partie.size()) {
			partie.changerPlateau();
			
			
			panneauTitre.afficherTitre(partie.getPlateauCourant().getDesciption());
			panneauFooter.effacer();
			panneauScore.initialiser();
	
			
			grillePane.setOnDragDetected(new DragDetectedEvent());
			timelineChrono.play();
	
			dessinerPlateau();
		} else {
			panneauFooter.afficherResultat("La partie est finie !");
		}
	}

	private void initTimelineChrono() {
		KeyFrame k = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				panneauScore.mettreAJourTemps();
			}
		});

		timelineChrono = new Timeline(k);
		timelineChrono.setCycleCount(Animation.INDEFINITE);
		timelineChrono.play();
	}

	private void initBarreScore() {
		panneauScore = new PanneauScore(this);
		((BorderPane) root).setLeft(panneauScore);

	}

	private void initTimelineJeu() {
		final KeyFrame eliminerCombo = new KeyFrame(Duration.seconds(0), new EventEliminerCombo());
		final KeyFrame remplirCasesVides = new KeyFrame(
				Duration.seconds(TEMPS_AFFICHAGE_CASES_VIDES + TEMPS_REMPLISSAGE), new EventRemplirCasesVides());
		timeline = new Timeline(eliminerCombo, remplirCasesVides);
		timeline.setCycleCount(Animation.INDEFINITE);
	}

	private void dessinerPlateau() {

		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				int indiceImage = partie.getPlateauCourant().getBonbon(l, c).getSorte().ordinal();

				gc.drawImage(candies[indiceImage], c * 64, l * 64);
			}
		}
	}

	private void initImagesCandies() {
		candies = new Image[NOMBRE_DE_CANDIES];

		try {
			for (int i = 0; i < candies.length; i++) {
				candies[i] = new Image(getClass().getResourceAsStream("/Candy_" + i + ".png"));
			}
		} catch (Exception e) {
			panneauFooter.afficherMessage(e.getMessage());
		}
	}

	private final class EventRemplirCasesVides implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {

			gc.clearRect(0, 0, 640, 640);
			partie.getPlateauCourant().eliminerCasesVides();
			dessinerPlateau();
		}
	}

	private final class EventEliminerCombo implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {

			if (comboSpeciale != null) {
				comboSpeciale.viderCombinaison(partie.getPlateauCourant());
				partie.getPlateauCourant().placerBonbon(comboSpeciale.getBonbonSpecial(), lBonbonSpecial, cBonbonSpecial);
				dessinerPlateau();
				partie.getPlateauCourant().incrementerDeplacements();
				mettreAJourPanneauScore(comboSpeciale);

				comboSpeciale = null;

			} else {
				Combinaison combo = DetecteurCombinaison.detecterCombinaison(partie.getPlateauCourant());
				if (combo != null) {
					combo.viderCombinaison(partie.getPlateauCourant());
					partie.getPlateauCourant().comptabiliser(combo.getNombrePoints());
					partie.comptabiliser(combo.getNombrePoints());
					dessinerPlateau();

					mettreAJourPanneauScore(combo);
				} else
					timeline.pause();
			}

		}

		private void mettreAJourPanneauScore(Combinaison combo) {
			partie.getPlateauCourant().comptabiliser(combo.getNombrePoints());
			partie.comptabiliser(combo.getNombrePoints());
			
			panneauScore.mettreAJourNombreDeplacements();
			panneauScore.mettreAJourScore();

			if (partie.getPlateauCourant().estTermine()) {
				grillePane.setOnDragDetected(null);
				timelineChrono.pause();

				if (partie.getPlateauCourant().objectifAtteint()) {
					panneauFooter.afficherResultat("Plateau terminé avec succès !");
					panneauScore.autoriserContinuer();
				} else {
					panneauFooter.afficherResultat("Plateau échoué !");
				}
			}
		}
	}

	private final class DragDetectedEvent implements EventHandler<MouseEvent> {

		private DragDetectedEvent() {
		}

		public void handle(MouseEvent event) {
			Dragboard db = grillePane.startDragAndDrop(TransferMode.ANY);

			xd = (int) event.getX();
			yd = (int) event.getY();

			int l = yd / 64;
			int c = xd / 64;

			db.setDragView(candies[partie.getPlateauCourant().getBonbon(l, c).getSorte().ordinal()]);

			ClipboardContent content = new ClipboardContent();
			content.putString("");
			db.setContent(content);

			event.consume();
		}
	}

	private final class DragOverEvent implements EventHandler<DragEvent> {

		private DragOverEvent() {
		}

		public void handle(DragEvent event) {

			event.acceptTransferModes(TransferMode.ANY);

			event.consume();
		}
	}

	private final class DragDroppedEvent implements EventHandler<DragEvent> {
		private DragDroppedEvent() {
		}

		public void handle(DragEvent event) {

			xf = (int) event.getX();
			yf = (int) event.getY();

			echangerSourceTarget();

			event.consume();
		}

		private void echangerSourceTarget() {
			int ls = 0, cs = 0, lt = 0, ct = 0;

			ls = yd / 64;
			cs = xd / 64;
			lt = yf / 64;
			ct = xf / 64;

			try {
				partie.getPlateauCourant().echanger(ls, cs, lt, ct);
				Combinaison combo = DetecteurCombinaison.detecterCombinaison(partie.getPlateauCourant(), lt, ct);
				if (combo == null) {
					combo = DetecteurCombinaison.detecterCombinaison(partie.getPlateauCourant(), ls, cs);
					if (combo != null)
						rangerComboSpeciale(combo, ls, cs);
				} else
					rangerComboSpeciale(combo, lt, ct);

				if (combo == null) {
					partie.getPlateauCourant().echanger(ls, cs, lt, ct);
					throw new CandyException("Mouvement interdit car pas de nouvelle combinaison");
				}

				timeline.play();
			} catch (CandyException e) {
				panneauFooter.afficherMessage(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Plateau getPlateau() {
		return partie.getPlateauCourant();
	}

	public PartieCandyCrush getPartie() {
		return partie;
	}

}
