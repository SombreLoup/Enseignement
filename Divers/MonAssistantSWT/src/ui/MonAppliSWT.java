package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
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

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MonAppliSWT {

	final public static Device device = Display.getCurrent ();
	final public static Color pourpre = new Color(device, 127, 0, 85);
	final public static Color bleu = new Color(device, 0, 0, 192);
	final public static Font fontGras = new Font(device, "Menlo", 12, SWT.BOLD);


	private static final String[] LISTE_VISIBILITE = new String[] {"private", "public", "protected"};
	private static final String[] LISTE_TYPE = new String[] {"int", "double", "boolean", "String"};
	protected Shell shell;
	
	private	Classe	classe = new Classe("MaClasse");
	private Button btnAjouter;
	private Composite compositeChamps;
	private CCombo cbVisibilite;
	private Text tNomChamp;
	private CCombo cbType;
	private Button btnReset;
	private Button btnGenerer;
	private Button btnAnnuler;

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

		createContents();

		shell.pack();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setText("Pattern d'une nouvelle classe");
		GridLayout gridLayout = new GridLayout(1,false);
		shell.setLayout(gridLayout);

		initCadreClasse();
		initCadreNouveauChamp();
		initCadreBoutons();

	}

	private void initCadreBoutons() {
		Composite composite = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		composite.setLayout(rowLayout);
		rowLayout.justify = true;

		btnGenerer = new Button(composite, SWT.NONE);
		btnGenerer.setText("Générer");
		btnGenerer.addSelectionListener(new BoutonsListener());

		
		btnAnnuler = new Button(composite, SWT.NONE);
		btnAnnuler.setText("Annuler");
		btnAnnuler.addSelectionListener(new BoutonsListener());

	}

	private void initCadreNouveauChamp() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new RowLayout());
		
		Group group = new Group(composite, SWT.SHADOW_ETCHED_IN);
		group.setText("Ajouter un champ");
		
		group.setLayout(new GridLayout(3,false));
		Label lVisibilite = new Label(group, SWT.NONE);
		lVisibilite.setText("Visibilité");
		Label lNom = new Label(group, SWT.NONE);
		lNom.setText("Nom");
		Label lType = new Label(group, SWT.NONE);
		lType.setText("Type");
		
		cbVisibilite = new CCombo(group, SWT.NONE);
		cbVisibilite.setItems(LISTE_VISIBILITE);
		cbVisibilite.select(0);
		
		tNomChamp = new Text(group, SWT.NONE);
		tNomChamp.setText("monChamp");
		
		cbType = new CCombo(group, SWT.NONE);
		cbType.setItems(LISTE_TYPE);
		cbType.select(0);
		
		btnAjouter = new Button(group, SWT.NONE);
		btnAjouter.setText("Ajouter");
		btnAjouter.addSelectionListener(new BoutonsListener());
		
		
		btnReset = new Button(group, SWT.NONE);
		btnReset.setText("Annuler");
		
		group.pack();
		
	}

	private void initCadreClasse() {
		Composite principal = new Composite(shell, SWT.NONE);
		principal.setLayout(new GridLayout(1,false));
		
		Group group = new Group(principal, SWT.SHADOW_ETCHED_IN);
		group.setText("Nouvelle classe");
		group.setLayout(new GridLayout(1,false));

		
		Composite compositeNom = new Composite(group, SWT.NONE);
		compositeNom.setLayout(new RowLayout());
		
		createClassLabel(compositeNom);
		Text tNomClass = new Text(compositeNom, SWT.NONE);
		tNomClass.setText(classe.getNom());
		Label lOuvrant = new Label(compositeNom, SWT.NONE);
		lOuvrant.setText(" {");
		
		compositeChamps = new Composite(group, SWT.NONE);
		compositeChamps.setLayout(new GridLayout(1,false));
		
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
			
			if (e.widget == btnAnnuler) {
				shell.dispose();
				return;
			}
			
			if (e.widget == btnGenerer) {
				classe.genererFichier(System.out);
				return;
			}
		}

		private void traiterBoutonReset() {
			cbVisibilite.select(0);
			tNomChamp.setText("MonChamp");
			cbType.select(0);
		}

		private void traiterBoutonAjouter() {
			Composite composite = new Composite(compositeChamps, SWT.NONE);
			composite.setLayout(new RowLayout());
			
			Label lVisibiliteChamp = new Label(composite, SWT.NONE);
			lVisibiliteChamp.setText(" \t"+cbVisibilite.getItem(cbVisibilite.getSelectionIndex()));
			lVisibiliteChamp.setFont(fontGras);
			lVisibiliteChamp.setForeground(pourpre);
			
			Label lTypeChamp = new Label(composite, SWT.NONE);
			lTypeChamp.setText(" \t"+cbType.getItem(cbType.getSelectionIndex()));
			lTypeChamp.setForeground(bleu);

			Label lNomChamp = new Label(composite, SWT.NONE);
			lNomChamp.setText(" \t"+tNomChamp.getText()+";");
							
			shell.pack();
			
			classe.add(new Champ(cbVisibilite.getItem(cbVisibilite.getSelectionIndex()), tNomChamp.getText(), cbType.getItem(cbType.getSelectionIndex())));
		}
	
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
	
		}
	
	}



}
