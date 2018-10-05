package ui;

import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Plateau;

public class CandyCrush extends Application {

	private static final int NOMBRE_DE_CANDIES = 5;
	private Button[][] grille;
	private GridPane grillePane;
	private Image[] candies;
	private Plateau plateau;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Candy Crush");
	
			initGrille(); // construction de grillePane
			VBox root = new VBox();
			root.getChildren().add(grillePane);		
			
			Scene scene = new Scene(root);
		
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGrille() {
		
		initCandies();	
		grillePane = new GridPane();

		grille = new Button[10][10];
		
		plateau = new Plateau(10, 10);
		plateau.initPlateauAleatoire();
		
	
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				int indiceImage = plateau.getBonbon(l,c).getSorte().ordinal();
				Button bouton = new Button("",new ImageView(candies[indiceImage]));
				
				bouton.setStyle("-fx-background-color: #FFFFFF");
				grille[l][c] = bouton;
	
				GridPane.setConstraints(bouton, c, l);
				grillePane.getChildren().add(bouton);
	
				bouton.setOnDragDetected(new DragDetectedEvent(bouton));
				bouton.setOnDragOver(new DragOverEvent(bouton));
				bouton.setOnDragDropped(new DragDroppedEvent(bouton));
	
			}
		}
	}

	private void recreerGridPane() {
		grillePane.getChildren().clear();
		
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				grillePane.add(grille[l][c], c, l);
			}
		}
		
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				grille[l][c].setGraphic(new ImageView(candies[plateau.getBonbon(l, c).getSorte().ordinal()]));
			}
		}
	
		
		grillePane.requestLayout();
	}

	private void initCandies() {
		candies = new Image[NOMBRE_DE_CANDIES];

		try {
			for (int i = 0; i < candies.length; i++) {
				candies[i] = new Image(getClass().getResourceAsStream("/Candy_"+i+".png"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final class DragDetectedEvent implements EventHandler<MouseEvent> {
		private final Button bouton;

		private DragDetectedEvent(Button bouton) {
			this.bouton = bouton;
		}

		public void handle(MouseEvent event) {
			Dragboard db = bouton.startDragAndDrop(TransferMode.ANY);

			db.setDragView(bouton.snapshot(null, null));

			ClipboardContent content = new ClipboardContent();
			content.putString("");
			db.setContent(content);

			event.consume();
		}
	}

	private final class DragOverEvent implements EventHandler<DragEvent> {
		private final Button bouton;

		private DragOverEvent(Button bouton) {
			this.bouton = bouton;
		}

		public void handle(DragEvent event) {
			if (event.getGestureSource() != bouton) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
			}

			event.consume();
		}
	}

	private final class DragDroppedEvent implements EventHandler<DragEvent> {
		private final Button bouton;

		private DragDroppedEvent(Button bouton) {
			this.bouton = bouton;
		}

		public void handle(DragEvent event) {
			echangerSourceTarget(event.getGestureSource(), event.getGestureTarget());
			event.consume();
		}

		private void echangerSourceTarget(Object source, Object target) {
			int ls=0, cs=0, lt=0, ct=0;

			for (int l = 0; l < 10; l++) {
				for (int c = 0; c < 10; c++) {
					if (grille[l][c] == source) {
						ls = l;
						cs = c;
					} else if (grille[l][c] == target) {
						lt = l;
						ct = c;
					}

				}
			}
			
			plateau.echanger(ls,cs,lt,ct);
			
			grille[ls][cs] = (Button)target;
			grille[lt][ct] = (Button)source;
			
			plateau.eliminerCombos();
			plateau.eliminerCasesVides();
			recreerGridPane();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
