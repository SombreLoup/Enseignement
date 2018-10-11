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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Bonbon;
import modele.CandyException;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3VerticalRaye;
import modele.combinaisons.detecteurs.DetecteurCombinaison;

public class CandyCrush extends Application {

	private static final double TEMPS_REMPLISSAGE = 0.1;
	private static final double TEMPS_AFFICHAGE_CASES_VIDES = 0.1;
	private static final int NOMBRE_DE_CANDIES = 10;
	private Canvas grillePane;
	private Image[] candies;
	private Plateau plateau;
	private Timeline timeline;
	private Pane root;
	private Scene scene;
	private int xd, yd, xf, yf;
	private	GraphicsContext gc;
	
	private	Combinaison	comboSpeciale = null;
	private	int			lBonbonSpecial;
	private	int			cBonbonSpecial;
	private Label lMessage;
	private FlowPane paneMessage;

	
	private void rangerComboSpeciale(Combinaison combo, int l, int c) {
		comboSpeciale = combo;
		lBonbonSpecial = l;
		cBonbonSpecial = c;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Candy Crush");

			initGrille(); // construction de grillePane
			
			
			initPaneAvecGrille();
			
			scene = new Scene(root);
			
	        initTimeline();
	        
			primaryStage.setScene(scene);
			primaryStage.show();
			
			timeline.play();

		} catch (Exception e) {
			if (lMessage != null)
				lMessage.setText(e.getMessage());
			else
				e.printStackTrace();
		}
	}

	private void initFooterPourMessages() {
		paneMessage = new FlowPane();
		lMessage = new Label("");
		paneMessage.getChildren().add(lMessage);
	}

	private void initPaneAvecGrille() {
		root = new BorderPane(grillePane);
		initFooterPourMessages();
		((BorderPane)root).setBottom(paneMessage);
		
		//root.getChildren().add(grillePane); // Pour un autre type de Pane
	}

	private void initTimeline() {
		final KeyFrame eliminerCombo = new KeyFrame(Duration.seconds(0), new EventEliminerCombo());
		final KeyFrame remplirCasesVides = new KeyFrame(Duration.seconds(TEMPS_AFFICHAGE_CASES_VIDES+TEMPS_REMPLISSAGE), new EventRemplirCasesVides());
		timeline = new Timeline(eliminerCombo, remplirCasesVides);
		timeline.setCycleCount(Animation.INDEFINITE);
	}

	private void initGrille() {

		initCandies();
		grillePane = new Canvas(640, 640);
		gc = grillePane.getGraphicsContext2D();


		grillePane.setOnDragDetected(new DragDetectedEvent());
		grillePane.setOnDragOver(new DragOverEvent());
		grillePane.setOnDragDropped(new DragDroppedEvent());

		plateau = new Plateau(10, 10);
		plateau.initPlateauAleatoire();

		dessinerGrille();
	}

	private void dessinerGrille() {
		 

		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				int indiceImage = plateau.getBonbon(l, c).getSorte().ordinal();

				gc.drawImage(candies[indiceImage], c * 64, l * 64);
			}
		}
	}

	private void initCandies() {
		candies = new Image[NOMBRE_DE_CANDIES];

		try {
			for (int i = 0; i < candies.length; i++) {
				candies[i] = new Image(getClass().getResourceAsStream("/Candy_" + i + ".png"));
			}
		} catch (Exception e) {
			if (lMessage != null)
				lMessage.setText(e.getMessage());
			else
				e.printStackTrace();
		}
	}

	private final class EventRemplirCasesVides implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			
			gc.clearRect(0, 0, 640, 640);
			plateau.eliminerCasesVides();
			dessinerGrille();
		}
	}

	private final class EventEliminerCombo implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			
			if (comboSpeciale != null) {
				comboSpeciale.viderCombinaison(plateau);
				plateau.placerBonbon(comboSpeciale.getBonbonSpecial(), lBonbonSpecial, cBonbonSpecial);
				dessinerGrille();		
				comboSpeciale = null;
			}
			else {
				Combinaison combo = DetecteurCombinaison.detecterCombinaison(plateau);
				if (combo != null) {
					combo.viderCombinaison(plateau);
					dessinerGrille();
				}
				else
					timeline.pause();
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

			db.setDragView(candies[plateau.getBonbon(l, c).getSorte().ordinal()]);

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
				plateau.echanger(ls, cs, lt, ct);
				Combinaison combo = DetecteurCombinaison.detecterCombinaison(plateau, lt,ct);
				if (combo==null) {
					combo = DetecteurCombinaison.detecterCombinaison(plateau, ls, cs);
					if (combo != null)
						rangerComboSpeciale(combo, ls, cs);
				}
				else
					rangerComboSpeciale(combo, lt, ct);
				
				if (combo==null ) {
					plateau.echanger(ls, cs, lt, ct);
					throw new CandyException("Mouvement interdit car pas de nouvelle combinaison");
				}
		
					
				timeline.play();
			} catch (CandyException e) {
				lMessage.setText(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
