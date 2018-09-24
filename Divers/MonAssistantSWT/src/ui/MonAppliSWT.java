package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

import model.Champ;
import model.Classe;
import ui.MonAppliSWT.BoutonsListener;
import ui.MonAppliSWT.GenererListener;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MonAppliSWT {
	final public static Device device = Display.getCurrent();
	final public static Color pourpre = new Color(device, 127, 0, 85);
	final public static Color bleu = new Color(device, 0, 0, 192);
	final public static Font fontGras = new Font(device, "Menlo", 12, SWT.BOLD);

	private static final String[] LISTE_VISIBILITE = new String[] { "private", "public", "protected" };
	private static final String[] LISTE_TYPE = new String[] { "int", "double", "boolean", "String", "ArrayList" };
	protected Shell shell;

	private Classe classe = new Classe("MaClasse");
	private Button btnAjouter;
	private Composite compositeChamps;
	private CCombo cbVisibilite;
	private Text tNomChamp;
	private CCombo cbType;
	private Button btnReset;
	private BoutonsListener listenerBoutons;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MonAppliSWT window = new MonAppliSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setText("Pattern d'une nouvelle classe");
		GridLayout gridLayout = new GridLayout(1,false);
		shell.setLayout(gridLayout);

		createControl(shell);
		
		Button btnGenerer = new Button(shell, SWT.NONE);
		btnGenerer.setText("Generer");
		btnGenerer.addSelectionListener(new GenererListener());

		shell.pack();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		initCadreClasse(composite);
		initCadreNouveauChamp(compositeChamps);

		// Pour le plugin
		// setControl(composite);
	}

	private void initCadreNouveauChamp(Composite parent) {
		Composite compositeGauche = new Composite(parent, SWT.NONE);
		GridLayout grid = new GridLayout(5, false);
		compositeGauche.setLayout(grid);


		cbVisibilite = new CCombo(compositeGauche, SWT.NONE);
		cbVisibilite.setItems(LISTE_VISIBILITE);
		cbVisibilite.select(0);

		cbType = new CCombo(compositeGauche, SWT.NONE);
		cbType.setItems(LISTE_TYPE);
		cbType.select(0);

		tNomChamp = new Text(compositeGauche, SWT.NONE);
		tNomChamp.setText("monChamp");
		
		Label lPointVirgule = new Label(compositeGauche,SWT.NONE);
		lPointVirgule.setText(";");
		
		Composite compositeDroit = new Composite(compositeChamps, SWT.NONE);
		compositeDroit.setLayout(new RowLayout());
	
		btnAjouter = new Button(compositeDroit, SWT.NONE);
		btnAjouter.setText("+");
		listenerBoutons = new BoutonsListener();
		btnAjouter.addSelectionListener(listenerBoutons);
	}

	private void ajouterChampDansComposite(Champ champ) {
		Composite compositeGauche = new Composite(compositeChamps, SWT.NONE);
		GridLayout grid = new GridLayout(5, false);
		compositeGauche.setLayout(grid);
	
		
		Label lEspace = new Label(compositeGauche,SWT.NONE);
		lEspace.setText("  ");
		
		Label lVisibiliteChamp = new Label(compositeGauche, SWT.NONE);
		lVisibiliteChamp.setText(champ.getVisibilite());
		lVisibiliteChamp.setFont(fontGras);
		lVisibiliteChamp.setForeground(pourpre);
	
		Label lTypeChamp = new Label(compositeGauche, SWT.NONE);
		lTypeChamp.setText(champ.getType());
		lTypeChamp.setForeground(bleu);
	
		Label lNomChamp = new Label(compositeGauche, SWT.NONE);
		lNomChamp.setText(champ.getNom());

		Label lPointVirgule = new Label(compositeGauche,SWT.NONE);
		lPointVirgule.setText(";");

		Composite compositeDroit = new Composite(compositeChamps, SWT.NONE);
		compositeDroit.setLayout(new RowLayout());
		
		Button btnMoins = new Button(compositeDroit, SWT.NONE);
		btnMoins.setText("-");
		btnMoins.setData(champ.getNom());
		btnMoins.addSelectionListener(listenerBoutons);
		
		if (! champ.getType().equals("ArrayList")) {
			Button btnCons = new Button(compositeDroit, SWT.TOGGLE);
			btnCons.setText("C");
			btnCons.setSelection(champ.isConstruction());
			btnCons.setData(champ.getNom());
			btnCons.addSelectionListener(listenerBoutons);			
		}

		

		Button btnGetter = new Button(compositeDroit, SWT.TOGGLE);
		btnGetter.setText("G");
		btnGetter.setSelection(champ.isGetter());
		btnGetter.setData(champ.getNom());
		btnGetter.addSelectionListener(listenerBoutons);

		if (! champ.getType().equals("ArrayList")) {
			Button btnSetter = new Button(compositeDroit, SWT.TOGGLE);
			btnSetter.setText("S");
			btnSetter.setSelection(champ.isSetter());
			btnSetter.setData(champ.getNom());
			btnSetter.addSelectionListener(listenerBoutons);		
		}

		Button btnEquals = new Button(compositeDroit, SWT.TOGGLE);
		btnEquals.setText("E");
		btnEquals.setSelection(champ.isEgalite());

		btnEquals.setData(champ.getNom());
		btnEquals.addSelectionListener(listenerBoutons);

		Button btnToString = new Button(compositeDroit, SWT.TOGGLE);
		btnToString.setText("T");
		btnToString.setSelection(champ.isAffichage());

		btnToString.setData(champ.getNom());
		btnToString.addSelectionListener(listenerBoutons);
	}



	private void initCadreClasse(Composite parent) {
		Composite principal = new Composite(parent, SWT.NONE);
		principal.setLayout(new GridLayout(1, false));

		Group group = new Group(principal, SWT.SHADOW_ETCHED_IN);
		group.setText("Nouvelle classe");
		group.setLayout(new GridLayout(1, false));

		Composite compositeNom = new Composite(group, SWT.NONE);
		compositeNom.setLayout(new RowLayout());

		createClassLabel(compositeNom);
		Text tNomClass = new Text(compositeNom, SWT.NONE);
		tNomClass.setText(classe.getNom());
		Label lOuvrant = new Label(compositeNom, SWT.NONE);
		lOuvrant.setText(" {");

		compositeChamps = new Composite(group, SWT.NONE);
		compositeChamps.setLayout(new GridLayout(2, false));
		
		Composite compositeFin = new Composite(group, SWT.NONE);
		compositeFin.setLayout(new RowLayout());
		Label lFin = new Label(compositeFin, SWT.NONE);
		lFin.setText("} ");
	}

	private void createClassLabel(Composite compositeNom) {
		Label lClass = new Label(compositeNom, SWT.NONE);
		lClass.setText("class ");

		lClass.setFont(fontGras);
		lClass.setForeground(pourpre);
	}

	public class BoutonsListener implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.widget == btnAjouter) {
				traiterBoutonAjouter();
				return;
			}

			if (e.widget == btnReset) {
				traiterBoutonReset();
				return;
			}
			
			String nomChamp = (String)e.widget.getData();
			Champ champ = classe.getChamp(nomChamp);
			boolean etat = ((Button)e.widget).getSelection();
			if (((Button)e.widget).getText().equals("-")) {
				traiterSuppressionChamp(nomChamp);
			}
			
			if (((Button)e.widget).getText().equals("E")) {
				champ.setEgalite(etat);
			}
			
			if (((Button)e.widget).getText().equals("T")) {
				champ.setAffichage(etat);
			}			
			
			if (((Button)e.widget).getText().equals("G")) {
				champ.setGetter(etat);
			}			
			
			if (((Button)e.widget).getText().equals("S")) {
				champ.setSetter(etat);
			}	
			
			if (((Button)e.widget).getText().equals("C")) {
				champ.setConstruction(etat);
			}
		}

		private void traiterSuppressionChamp(String data) {
			classe.remove(data);
			
			refreshCompositeChamps();
			
			shell.pack();
			
		}

		private void refreshCompositeChamps() {
			for (Control control : compositeChamps.getChildren()) {
				control.dispose();
			}
			
			for (Champ champ :  classe.getListeChamps()) {
				ajouterChampDansComposite(champ);
			}
			
			initCadreNouveauChamp(compositeChamps);
		}

		private void traiterBoutonReset() {
			cbVisibilite.select(0);
			tNomChamp.setText("MonChamp");
			cbType.select(0);
		}

		private void traiterBoutonAjouter() {
			Champ champ = new Champ(cbVisibilite.getItem(cbVisibilite.getSelectionIndex()), tNomChamp.getText(), cbType.getItem(cbType.getSelectionIndex()));
			
			if (classe.contains(champ))
				MessageDialog.openError(shell, "Error", "Un champ avec ce nom existe déjà !");
			else {
				classe.add(champ);
	
				refreshCompositeChamps();
				
				shell.pack();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public Classe getClasse() {
		return classe;
	}

	public class GenererListener implements SelectionListener {
	
		@Override
		public void widgetSelected(SelectionEvent e) {
			classe.genererFichier(System.out);
		}
	
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
	
		}
	
	}



}
