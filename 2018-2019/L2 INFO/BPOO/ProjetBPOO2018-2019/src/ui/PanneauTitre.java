package ui;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class PanneauTitre extends HBox {
	
	private static final int TAILLE_TITRE = 24;
	private static final String COULEUR_TITRE = "C24E36";
	private static final String FONTE = "Cambria";
	private Label lDescription;


	public PanneauTitre() {
		super();
		this.setAlignment(Pos.CENTER);
		
		getChildren().add(getLabelDescription());
	}
	
	private Node getLabelDescription() {
		lDescription = new Label("");	lDescription.setVisible(false);

		lDescription.setTextFill(Color.web(COULEUR_TITRE));
		lDescription.setFont(Font.font(FONTE, FontWeight.BOLD, TAILLE_TITRE));

		return lDescription;
	}


	public void afficherTitre(String message) {
		if (lDescription == null) {
			System.err.println(message);
			return;
		}
		
		lDescription.setVisible(true);
		
		lDescription.setText(message); 
	}


	public void effacer() {
		lDescription.setVisible(false);
	}

}
