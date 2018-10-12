package ui;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	private Label lDeplacement;
	private Label lScore;
	private Label lTemps;
	
	private	int secondesEcoulees = 0;;
	private Label lResultat;
	private Button bArreter;
	private Button bContinuer;
	private Timeline timelineChrono;
	
	private	int	numeroPlateauCourant=0;
	private PartieCandyCrush partie;
	private Label lDescription;
	


	
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
			initBarreScore();
			
			scene = new Scene(root);
			
	        initTimelineJeu();
	        initTimelineChrono();
	        
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

	private void initTimelineChrono() {
		KeyFrame k = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

			   @Override
			   public void handle(ActionEvent event) {
				   int s,m;
				   secondesEcoulees++;
				   
				   plateau.setSecondesJouees(secondesEcoulees);
				   
				   m = secondesEcoulees / 60;
				   s = secondesEcoulees % 60;
			    
				   lTemps.setText(""+m+"m "+s+"s");
			   }   
		});
		
		
		timelineChrono = new Timeline(k);
		timelineChrono.setCycleCount(Animation.INDEFINITE);
		timelineChrono.play();
	}

	private void initBarreScore() {
		GridPane p = new GridPane();
		p.setHgap(5);
		
		
		
		HBox hbox = new HBox();
		lDescription = new Label(plateau.getDesciption());
		lDescription.setTextFill(Color.web("#C24E36"));
		lDescription.setFont(Font.font("Cambria", FontWeight.BOLD, 24));
		hbox.getChildren().add(lDescription);
		hbox.setAlignment(Pos.CENTER);
		((BorderPane)root).setTop(hbox);
		
		p.add(new Label("    "), 0, 0);

	
		Label l1 = new Label("Déplacements :");
		p.add(l1, 0, 1);
		GridPane.setHalignment(l1, HPos.RIGHT);
		
		lDeplacement = new Label("0");
		p.add(lDeplacement, 1, 1);

		
		Label l2 = new Label("Score :");
		p.add(l2, 0, 2);
		GridPane.setHalignment(l2, HPos.RIGHT);
		
		lScore = new Label("0");
		p.add(lScore, 1, 2);
		
		Label l3 = new Label("Temps :");
		p.add(l3, 0, 3);
		GridPane.setHalignment(l3, HPos.RIGHT);
		
		
		lTemps = new Label("0:0:0");
		p.add(lTemps, 1, 3);
		
		p.add(new Label(""), 0, 4);
		
		bArreter = new Button("Arrêter");
		p.add(bArreter, 0, 5);
		GridPane.setHalignment(bArreter, HPos.RIGHT);
		bArreter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				scene.getWindow().hide();
			}
		});

		bContinuer = new Button("Continuer");
		p.add(bContinuer, 1, 5);
		bContinuer.setDisable(true);
		bContinuer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (numeroPlateauCourant<partie.size()) {
					numeroPlateauCourant++;
					
					plateau = partie.get(numeroPlateauCourant);
					lDescription.setText(plateau.getDesciption());
					lMessage.setText("  ");
					secondesEcoulees=0;
					
					grillePane.setOnDragDetected(new DragDetectedEvent());
					timelineChrono.play();
					
					dessinerGrille();
				}
				else {
					lMessage.setText("La partie est finie !");
				}	
				bContinuer.setDisable(true);
			}
		});
		
		lResultat = new Label("    ");
		p.add(lResultat, 0, 6);
		
		
		((BorderPane)root).setLeft(p);
		
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

	private void initTimelineJeu() {
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

//		plateau = new PlateauNombreDeplacementsLimite(10, 10, 10);
//		plateau.initPlateauAleatoire();
		try {
			partie = new PartieCandyCrush();
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau2.csv"));
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau3.csv"));
			partie.add(PlateauFactory.chargerPlateau("plateaux/plateau4.csv"));
			
			
			plateau = partie.get(numeroPlateauCourant);
		} catch (CandyException e) {
			if (lMessage != null)
				lMessage.setText(e.getMessage());
			else
				e.printStackTrace();
		}

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
				plateau.incrementerDeplacements();
				mettreAJourPanneauScore(comboSpeciale);

				comboSpeciale = null;

			}
			else {
				Combinaison combo = DetecteurCombinaison.detecterCombinaison(plateau);
				if (combo != null) {
					combo.viderCombinaison(plateau);
					plateau.comptabiliser(combo.getNombrePoints());
					dessinerGrille();
					
					mettreAJourPanneauScore(combo);
				}
				else
					timeline.pause();
			}


		}

		private void mettreAJourPanneauScore(Combinaison combo) {
			plateau.comptabiliser(combo.getNombrePoints());
			lScore.setText(""+plateau.getNombrePoints());
			lDeplacement.setText(""+plateau.getNombreDeplacement());
			
			if (plateau.estTermine()) {
				grillePane.setOnDragDetected(null);
				timelineChrono.pause();
				
				if (plateau.objectifAtteint()) {
					lMessage.setText("Plateau terminé avec succès !");
					bContinuer.setDisable(false);
				}
				else {
					lMessage.setText("Plateau échoué !");					
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
