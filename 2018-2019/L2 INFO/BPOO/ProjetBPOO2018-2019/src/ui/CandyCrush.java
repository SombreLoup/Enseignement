package ui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CandyCrush extends Application {



	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Candy Crush");

			BorderPane root = new BorderPane();

			initGrille(root);

			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGrille(Pane panneau) {
		GridPane grille = new GridPane();
		grille.setStyle("-fx-background-color: #D2691E");


		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {
				Button bouton = new Button("B");
				if ((l+c) % 2 == 0)
					bouton.setStyle("-fx-background-color: #D2691E");
				else
					bouton.setStyle("-fx-background-color: #FFD700");
					
				GridPane.setConstraints(bouton, c, l);
				grille.getChildren().add(bouton);

				
				
				bouton.setOnDragDetected(new EventHandler <MouseEvent>() {
		            public void handle(MouseEvent event) {
		                /* drag was detected, start drag-and-drop gesture*/
		                System.out.println("onDragDetected");
		                
		                /* allow any transfer mode */
		                Dragboard db = bouton.startDragAndDrop(TransferMode.ANY);

		                // This is where the magic happens, you take a snapshot of the HBox.
		                db.setDragView(bouton.snapshot(null, null));
		                
		                /* put a string on dragboard */
		                ClipboardContent content = new ClipboardContent();
		                content.putString(bouton.getText());
		                db.setContent(content);
		                
		                event.consume();
		            }
		        });

				bouton.setOnDragOver(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                /* data is dragged over the target */
		                System.out.println("onDragOver");
		                
		                /* accept it only if it is  not dragged from the same node 
		                 * and if it has a string data */
		                if (event.getGestureSource() != bouton &&
		                        event.getDragboard().hasString()) {
		                    /* allow for both copying and moving, whatever user chooses */
		                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
		                }
		                
		                event.consume();
		            }
		        });

				bouton.setOnDragEntered(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                /* the drag-and-drop gesture entered the target */
		                System.out.println("onDragEntered");
		                /* show to the user that it is an actual gesture target */
		                if (event.getGestureSource() != bouton &&
		                        event.getDragboard().hasString()) {
	
		                }
		                
		                event.consume();
		            }
		        });

				bouton.setOnDragExited(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                /* mouse moved away, remove the graphical cues */
	
		                
		                event.consume();
		            }
		        });
		        
		        bouton.setOnDragDropped(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                /* data dropped */
		                System.out.println("onDragDropped");
		                /* if there is a string data on dragboard, read it and use it */
		                Dragboard db = event.getDragboard();
		                boolean success = false;
		                if (db.hasString()) {
		                	bouton.setText(db.getString());
		                    success = true;
		                }
		                /* let the source know whether the string was successfully 
		                 * transferred and used */
		                event.setDropCompleted(success);
		                
		                event.consume();
		            }
		        });

		        bouton.setOnDragDone(new EventHandler <DragEvent>() {
		            public void handle(DragEvent event) {
		                /* the drag-and-drop gesture ended */
		                System.out.println("onDragDone");
		                /* if the data was successfully moved, clear it */
		                if (event.getTransferMode() == TransferMode.MOVE) {
		                	bouton.setText("");
		                }
		                
		                event.consume();
		            }
		        });

				
				
			}
		}
		
		panneau.getChildren().add(grille);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
