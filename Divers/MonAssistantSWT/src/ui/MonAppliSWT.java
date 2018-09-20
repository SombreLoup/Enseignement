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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;





import model.Classe;
import ui.MonAppliSWT.BoutonsListener;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MonAppliSWT {

	public class BoutonsListener implements SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.widget == btnAjouter) {
				String	strChamp = 	"\t"+
									cbVisibilite.getItem(cbVisibilite.getSelectionIndex())+" \t"+
									cbType.getItem(cbType.getSelectionIndex())+" \t"+
									tNomChamp.getText()+";";
				Label lChamp = new Label(compositeChamps, SWT.NONE);
				lChamp.setText(strChamp);
				shell.pack();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private static final String[] LISTE_VISIBILITE = new String[] {"private", "public", "protected"};
	private static final String[] LISTE_TYPE = new String[] {"int", "double", "boolean", "String"};
	protected Shell shell;
	
	private	Classe	classe = new Classe("MaClasse");
	private Button btnAjouter;
	private Composite compositeChamps;
	private CCombo cbVisibilite;
	private Text tNomChamp;
	private CCombo cbType;

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
		shell.setLayout(new GridLayout());

		initCadreClasse();
		initCadreNouveauChamp();

	}

	private void initCadreNouveauChamp() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new RowLayout());
		
		Group group = new Group(composite, SWT.SHADOW_ETCHED_IN);
		group.setText("Nouveau champ");
		
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
		
		
		Button btnReset = new Button(group, SWT.NONE);
		btnReset.setText("Annuler");
		
		group.pack();
		
	}

	private void initCadreClasse() {
		Composite principal = new Composite(shell, SWT.NONE);
		principal.setLayout(new FillLayout(SWT.VERTICAL));
		
		
		Composite compositeNom = new Composite(principal, SWT.NONE);
		compositeNom.setLayout(new RowLayout());
		
		Label lClass = new Label(compositeNom, SWT.NONE);
		lClass.setText("class ");
		Text tNomClass = new Text(compositeNom, SWT.NONE);
		tNomClass.setText(classe.getNom());
		Label lOuvrant = new Label(compositeNom, SWT.NONE);
		lOuvrant.setText(" {");
		
		compositeChamps = new Composite(principal, SWT.NONE);
		compositeChamps.setLayout(new GridLayout(1,false));
		
		Composite compositeFin = new Composite(principal, SWT.NONE);
		compositeFin.setLayout(new RowLayout());
		Label lFin = new Label(compositeFin, SWT.NONE);
		lFin.setText("} ");
	}



}
