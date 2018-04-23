package fenetres;

import application.Partie;
import fenetres.FenetrePrincipale.EvtNouveau;
import fenetres.FenetrePrincipale.EvtQuitter;
import fenetres.FenetrePrincipale.EvtRecord;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetrePrincipale {

	public class EvtQuitter implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}

	}


	public class EvtRecord implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			fenetreRecord.getStage().show();
		}

	}


	public class EvtNouveau implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			initPartie();
			
			tfProposition.setDisable(false);
			bValider.setDisable(false);
		}

	}


	public class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			int proposition = Integer.parseInt(tfProposition.getText());
			partie.testerProposition(proposition);

			lNombreTentatives.setText(MSG_NB_TENTATIVES+partie.getNombreTentatives());

			if (partie.isGagne()) {
				if (partie.isNouveauRecord())
					lResultat.setText("Gagné ! Et en plus record battu");
				else
					lResultat.setText("Gagné !");
				tfProposition.setDisable(true);
				bValider.setDisable(true);
				
			}
			else if (partie.isInferieur())
				lResultat.setText(""+proposition+" est trop petit");
			else
				lResultat.setText(""+proposition+" est trop grand");

			tfProposition.setText("");
		}

	}


	private static final String MSG_DEBUT_PARTIE = "La partie est commencée. C'est à vous de jouer !";
	private static final String MSG_NB_TENTATIVES = "Nombre de tentatives : ";
	
	private VBox root = new VBox();
	private VBox form;
	private TextField tfProposition;
	private Label lNombreTentatives;
	private Label lResultat;
	
	private	Partie	partie;
	private Button bValider;
	
	private Stage	stage;
	private FenetreRecord fenetreRecord;

	public FenetrePrincipale(Stage stage) {
		this.stage = stage;
		root = new VBox();

		initMenu();

		form = new VBox();
		initForm();
		root.getChildren().add(form);

		Scene scene = new Scene(root,400,200);
		stage.setTitle("Jeu du nombre mystérieux");		
		
		initProposition();
		initBoutons();
		initResultat();
		
		initPartie();
		

		stage.setScene(scene);
		stage.show();

		fenetreRecord = new FenetreRecord(this);
	}


	private void initMenu() {
		MenuBar	menuBar = new MenuBar();
		Menu	menuFichier = new Menu("Jeu");
		MenuItem	menuNouveau = new MenuItem("Nouvelle partie");
		MenuItem	menuQuitter = new MenuItem("Quitter");
		Menu	menuScore = new Menu("Score");
		MenuItem	menuRecord = new MenuItem("Record");
		
		menuBar.getMenus().add(menuFichier);
		menuFichier.getItems().add(menuNouveau);
		menuFichier.getItems().add(menuQuitter);
		menuBar.getMenus().add(menuScore);
		menuScore.getItems().add(menuRecord);
		
		root.getChildren().add(menuBar);
		
		
		menuNouveau.setOnAction(new EvtNouveau());
		menuRecord.setOnAction(new EvtRecord());
		menuQuitter.setOnAction(new EvtQuitter());
	}


	private void initPartie() {
		partie = new Partie();
		lNombreTentatives.setText(MSG_NB_TENTATIVES+partie.getNombreTentatives());
		lResultat.setText(MSG_DEBUT_PARTIE);
	}


	private void initResultat() {
		lNombreTentatives = new Label(MSG_NB_TENTATIVES+ 0);
		lResultat = new Label(MSG_DEBUT_PARTIE);	
		form.getChildren().add(lNombreTentatives);
		form.getChildren().add(lResultat);
	}


	private void initBoutons() {
		HBox paneBouton = new HBox();
		bValider = new Button("Valider");
		paneBouton.getChildren().add(bValider);
		paneBouton.setAlignment(Pos.CENTER); // centrage
		form.getChildren().add(paneBouton);
		
		bValider.setOnAction(new EvtValider());
	}


	private void initForm() {
		form.setPadding(new Insets(5,5,5,5));
		form.setSpacing(5);
	}


	private void initProposition() {
		HBox paneProposition = new HBox();
		Label lProposition = new Label("Votre proposition : ");
		tfProposition = new TextField();
		paneProposition.getChildren().add(lProposition);
		paneProposition.getChildren().add(tfProposition);
		paneProposition.setAlignment(Pos.CENTER); // centrage
		form.getChildren().add(paneProposition);
	}


	public Stage getStage() {
		return stage;
	}


	public Partie getPartie() {
		return partie;
	}

}
