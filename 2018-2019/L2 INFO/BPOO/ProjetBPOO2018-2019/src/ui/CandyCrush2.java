package ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import modele.Plateau;

public class CandyCrush2 extends Application {

	private static final int NOMBRE_DE_CANDIES = 5;
	private Button[][] grille;
	private Canvas grillePane;
	private Image[] candies;
	private Plateau plateau;
	private int etatAnimation = 0;
	private Timeline timeline;
	private Stage stage;
	private VBox root;
	private Scene scene;
	private int xd, yd, xf, yf;

	public final class EventVidage implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("Vidage");

		}
	}

	public final class EventRemplissage implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("Remplissage");
			plateau.debugConsole();
			plateau.eliminerCasesVides();
			plateau.debugConsole();

		}

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			this.stage = primaryStage;
			primaryStage.setTitle("Candy Crush");

			initGrille(); // construction de grillePane
			root = new VBox();
			root.getChildren().add(grillePane);

			scene = new Scene(root);
			
	        KeyFrame ViderCases = new KeyFrame(Duration.seconds(1),
	                new EventHandler<ActionEvent>() {
	        			public void handle(ActionEvent event) {
	        				grillePane.getGraphicsContext2D().clearRect(0, 0, 640, 640);
	        				plateau.eliminerCombos();
	        				dessinerGrille();
	        				System.out.println("Combos éliminés");

	                    }
	                });
			
	        KeyFrame RemplirCasesVides = new KeyFrame(Duration.seconds(1),
	                new EventHandler<ActionEvent>() {
	        			public void handle(ActionEvent event) {
//	        				grillePane.getGraphicsContext2D().clearRect(0, 0, 640, 640);
	        				plateau.eliminerCasesVides();
	        				//dessinerGrille();
	        				System.out.println("Remplissage fait");
	        				timeline.stop();

	                    }
	                });
	        
	        timeline = new Timeline(ViderCases, RemplirCasesVides);

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGrille() {

		initCandies();
		grillePane = new Canvas(640, 640);

		grillePane.setOnDragDetected(new DragDetectedEvent());
		grillePane.setOnDragOver(new DragOverEvent());
		grillePane.setOnDragDropped(new DragDroppedEvent());

		grille = new Button[10][10];

		plateau = new Plateau(10, 10);
		plateau.initPlateauAleatoire();

		dessinerGrille();
	}

	private void dessinerGrille() {
		GraphicsContext gc = grillePane.getGraphicsContext2D();

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
			e.printStackTrace();
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
//			db.setDragView(grillePane.snapshot(null,null));

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

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

			event.consume();
		}
	}

	private final class DragDroppedEvent implements EventHandler<DragEvent> {
		private DragDroppedEvent() {
		}

		public void handle(DragEvent event) {

			xf = (int) event.getX();
			yf = (int) event.getY();

			Platform.runLater(() -> {
				echangerSourceTarget();
			});

			event.consume();
			System.out.println("Drag fini");
		}

		private void echangerSourceTarget() {
			int ls = 0, cs = 0, lt = 0, ct = 0;

			ls = yd / 64;
			cs = xd / 64;
			lt = yf / 64;
			ct = xf / 64;

			System.out.println("ls=" + ls + " cs=" + cs + " lt=" + lt + " ct=" + ct);

			plateau.echanger(ls, cs, lt, ct);

			System.out.println("Echange fait");
			
			timeline.play();

//			Platform.runLater(() -> {
//				plateau.eliminerCombos();
//				dessinerGrille();
//				System.out.println("Cases vides ?");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			});
//
//			Platform.runLater(() -> {
//				System.out.println("pause finie");
//				plateau.eliminerCasesVides();
//				//dessinerGrille();
//			});
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
