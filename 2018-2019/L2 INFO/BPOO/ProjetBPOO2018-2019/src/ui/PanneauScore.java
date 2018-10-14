package ui;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modele.PartieCandyCrush;



public class PanneauScore extends GridPane {
	
	private PartieCandyCrush partie;
	private CandyCrush candyCrush;
	private Label lDeplacement;
	private Label lScorePartie;
	private Label lScorePlateau;
	private Label lTemps;
	private Button bArreter;
	private Button bContinuer;

	public PanneauScore(CandyCrush candyCrush) {
		super();
		this.partie = candyCrush.getPartie();
		this.setHgap(5);
		
		

		Label lsp = new Label("Score de la partie :");
		add(lsp, 0, 0);
		GridPane.setHalignment(lsp, HPos.RIGHT);

		lScorePartie = new Label("0");
		add(lScorePartie, 1, 0);

		Label ld = new Label("Déplacements :");
		add(ld, 0, 1);
		GridPane.setHalignment(ld, HPos.RIGHT);

		lDeplacement = new Label("0");
		add(lDeplacement, 1, 1);

		Label ls = new Label("Score du plateau :");
		add(ls, 0, 2);
		GridPane.setHalignment(lsp, HPos.RIGHT);

		lScorePlateau = new Label("0");
		add(lScorePlateau, 1, 2);

		Label lt = new Label("Temps :");
		add(lt, 0, 3);
		GridPane.setHalignment(lt, HPos.RIGHT);

		lTemps = new Label("0:0:0");
		add(lTemps, 1, 3);

		add(new Label(""), 0, 4);

		bArreter = new Button("Arrêter");
		add(bArreter, 0, 5);
		GridPane.setHalignment(bArreter, HPos.RIGHT);
		bArreter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});

		bContinuer = new Button("Continuer");
		add(bContinuer, 1, 5);
		bContinuer.setDisable(true);
		bContinuer.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				partie.changerPlateau();
				bContinuer.setDisable(true);
				candyCrush.changerPlateau();
			}
		});
	}
	

	public void mettreAJourNombreDeplacements() {
		lDeplacement.setText(""+partie.getPlateauCourant().getNombreDeplacement());
	}
	
	public void mettreAJourTemps() {
		int s, m;
		int secondesEcoulees = partie.getPlateauCourant().getSecondesJouees() + 1;

		partie.getPlateauCourant().setSecondesJouees(secondesEcoulees);

		m = secondesEcoulees / 60;
		s = secondesEcoulees % 60;

		lTemps.setText("" + m + "m " + s + "s");
	}


	public void mettreAJourScore() {
		lScorePartie.setText(""+partie.getScore());
		lScorePlateau.setText(""+partie.getPlateauCourant().getNombrePoints());
	}


	public void initialiser() {
		mettreAJourNombreDeplacements();
		mettreAJourTemps();
		mettreAJourScore();
	}


	public void autoriserContinuer() {
		bContinuer.setDisable(false);
	}

}
