package pluginpattern.wizards;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import model.Champ;
import model.Classe;



/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (java).
 */

public class PatternClasseWizardPage extends WizardPage {

	final public static Device device = Display.getCurrent();
	final public static Color pourpre = new Color(device, 127, 0, 85);
	final public static Color bleu = new Color(device, 0, 0, 192);
	final public static Font fontGras = new Font(device, "Menlo", 12, SWT.BOLD);

	private static final String[] LISTE_VISIBILITE = new String[] { "private", "public", "protected" };
	private static final String[] LISTE_TYPE = new String[] { "int", "double", "boolean", "String" };
	private Classe classe = new Classe("MaClasse");
	private Button btnAjouter;
	private Composite compositeChamps;
	private CCombo cbVisibilite;
	private Text tNomChamp;
	private CCombo cbType;
	private Button btnReset;
	private BoutonsListener listenerBoutons;


	private String chemin;
	private Composite compositePage;
	private Text tNomClass;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public PatternClasseWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Création d'une nouvelle classe");
		setDescription(
				"Cet assistant vous facilite la création d'une classe en générant automatiquement les fonctions appartenant au modèle standard CPOOA");

		chemin = getFileLocation();
	}

	@Override
	public void createControl(Composite parent) {
		compositePage = new Composite(parent, SWT.NONE);
		compositePage.setLayout(new GridLayout(1, false));

		initCadreClasse(compositePage);
		initCadreNouveauChamp(compositeChamps);

		// Pour le plugin
		setControl(compositePage);
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
		tNomClass = new Text(compositeNom, SWT.NONE);
		tNomClass.setText(classe.getNom());
		tNomClass.addModifyListener(new TextModificationListener());
		
		
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

	public class TextModificationListener implements ModifyListener {
	
		@Override
		public void modifyText(ModifyEvent e) {
			classe.setNom(tNomClass.getText());
		}
	
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
			
			if (((Button)e.widget).getText().equals("-")) {
				traiterSuppressionChamp((String)e.widget.getData());
			}
		}

		private void traiterSuppressionChamp(String data) {
			classe.remove(data);
			
			refreshCompositeChamps();
			
			compositePage.pack();
			
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
				MessageDialog.openError(compositeChamps.getShell(), "Error", "Un champ avec ce nom existe déjà !");
			else {
				classe.add(champ);
	
				refreshCompositeChamps();
				
				compositePage.pack();
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}

	}

	public Classe getClasse() {
		return classe;
	}
	
	

	public String getFileLocation() {
		IProject project = null;

		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window != null) {
				ISelection iselection = window.getSelectionService().getSelection();
				IStructuredSelection selection = (IStructuredSelection) iselection;
				if (selection == null) {
					System.err.println("La sélection est nulle");
					return null;
				}
				

				Object firstElement = selection.getFirstElement();

				chemin = "";

				String sElement = firstElement.toString();
				System.out.println("Element = "+sElement);
				String delimiteurs = "\\[in";
				String[] elements = sElement.split(delimiteurs);
				System.out.println("Il y a "+elements.length+" éléments : ");
				for (int i = 0; i < elements.length; i++) {
					elements[i] = elements[i].replaceAll("\\]", " ").trim();
					elements[i] = elements[i].split(" ")[0];
					System.out.println("Element["+i+"]="+elements[i]);
				}
				
				chemin = elements[2]+"/"+elements[1];
				
				System.out.println("Nom du projet : "+elements[elements.length-1]);
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IProject projet = root.getProject(elements[elements.length-1]);
				if (projet == null)
					System.out.println(("Le projet "+elements[elements.length-1]+" n'existe pas !"));
				else if (! elements[1].equals("src")){
					System.out.println(("La classe doit être placée au moins dans le dossier 'src' du projet"));
				}
				else if (elements.length ==2)
					classe.setPaquet("");
				else {
					classe.setPaquet(elements[0]);
					String[] paquets = elements[0].split("\\.");
					
					for (int i = 0; i < paquets.length; i++) {
						chemin += "/"+paquets[i];
					}
				}
				
				System.out.println("Chemin final : "+chemin);
				
				return chemin;
			}

		} catch (Exception e) {
			System.err.println("Quelque chose s'est mal passé....");
			e.printStackTrace();
		}
		
		System.out.println("Aucune exception...");

		return null;
	}

	public String getChemin() {
		return chemin;
	}

}