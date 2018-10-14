package ui;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class PanneauFooter extends HBox {
	
	private static final String COULEUR_RESULTAT = "25B5C3";
	private static final String FONTE = "Cambria";
	private static final String COULEUR_MESSAGE = "#EB0E0E";
	private Label lResultat;
	private Label lMessage;

	public PanneauFooter() {
		super();
		this.setAlignment(Pos.CENTER);
		
		
		
		getChildren().add(getLabelMessage());
		getChildren().add(getLabelResultat());
	}
	
	private Node getLabelResultat() {
		lResultat = new Label("");	lResultat.setVisible(false);

		lResultat.setTextFill(Color.web(COULEUR_RESULTAT));
		lResultat.setFont(Font.font(FONTE, FontWeight.BOLD, 14));

		return lResultat;
	}

	private Node getLabelMessage() {
		lMessage = new Label("");	lMessage.setVisible(false);
		
		lMessage.setTextFill(Color.web(COULEUR_MESSAGE));
		lMessage.setFont(Font.font(FONTE, FontWeight.BOLD, 14));
		
		return lMessage;
	}

	public void afficherMessage(String message) {
		if (lMessage == null) {
			System.err.println(message);
			return;
		}
		
		lMessage.setVisible(true);
		lResultat.setVisible(false);
		
		lMessage.setText(message); 
	}

	public void afficherResultat(String message) {
		if (lMessage == null) {
			System.err.println(message);
			return;
		}

		lMessage.setVisible(false);
		lResultat.setVisible(true);
		
		lResultat.setText(message); 
	}
	
	public void effacer() {
		lMessage.setVisible(false);
		lResultat.setVisible(false);
	}

}
