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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CandyCrush extends Application {

	private Button[][] grille;
	private GridPane grillePane;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Candy Crush");
	
			initGrille(); // construction de grillePane
			BorderPane root = new BorderPane();
			
			root.getChildren().add(grillePane);
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGrille() {
		
		Image[] Candy = new Image[4];
		try {
			Candy[0] = new Image(getClass().getResourceAsStream("/Candy_1.png"));
			Candy[1] = new Image(getClass().getResourceAsStream("/Candy_2.png"));
			Candy[2] = new Image(getClass().getResourceAsStream("/Candy_3.png"));
			Candy[3] = new Image(getClass().getResourceAsStream("/Candy_4.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		grillePane = new GridPane();

		grille = new Button[10][10];
	
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				int indiceImage = (int)(Math.random()*4);
				Button bouton = new Button("",new ImageView(Candy[indiceImage]));
				
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
			
			grille[ls][cs] = (Button)target;
			grille[lt][ct] = (Button)source;
			
			
			recreerGridPane();
		}
	}

	private void recreerGridPane() {
		grillePane.getChildren().clear();
		
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				grillePane.add(grille[l][c], c, l);
			}
		}
		
		grillePane.requestLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
