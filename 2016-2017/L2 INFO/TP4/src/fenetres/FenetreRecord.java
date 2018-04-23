package fenetres;

import fenetres.FenetreRecord.EvtFenetre;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreRecord {

	public class EvtFenetre implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			lRecord.setText("Nouveau record : "+fenetrePrincipale.getPartie().getRecord());
		}


	}

	private FenetrePrincipale	fenetrePrincipale;
	private Stage stage;
	private Label lRecord;
	
	public FenetreRecord(FenetrePrincipale f) {
		this.fenetrePrincipale = f;
		
		this.stage = new Stage();
		this.stage.setTitle("Record");
	
		this.stage.initModality(Modality.WINDOW_MODAL);
		this.stage.initOwner(fenetrePrincipale.getStage());

		VBox panneauPrincipal = new VBox();
		panneauPrincipal.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(panneauPrincipal, 150, 40);
		
		lRecord = new Label("Record :");
		panneauPrincipal.getChildren().add(lRecord);

		this.stage.setScene(scene);

		this.stage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EvtFenetre());
	}

	public Stage getStage() {
		return stage;
	}

}
