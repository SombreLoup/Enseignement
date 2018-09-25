package pluginpattern.wizards;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
	
	private Classe classe = new Classe("MaClasse");
	private ToolItem btnAjouter;
	private Composite compositeChamps;
	private CCombo cbVisibilite;
	private Text tNomChamp;
	private CCombo cbType;
	private Button btnReset;
	private BoutonsListener listenerBoutons;
	private Text tTemplateList;
	private Composite compositeTemplate;
	private ToolItem btnMoins;
	private ToolItem btnCons;
	private ToolItem btnGetter;
	private ToolItem btnSetter;
	private ToolItem btnEquals;
	private ToolItem btnToString;

	private String chemin;
	private Composite compositePage;

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


		cbVisibilite = new CCombo(compositeGauche, SWT.READ_ONLY);
		cbVisibilite.setItems(Classe.LISTE_VISIBILITE);
		cbVisibilite.select(0);

		cbType = new CCombo(compositeGauche, SWT.NONE);
		cbType.setItems(Classe.LISTE_TYPE);
		cbType.select(0);
		cbType.addModifyListener(new ModificationTypeListener());
		
		createCompositeTemplate(compositeGauche);


		tNomChamp = new Text(compositeGauche, SWT.NONE);
		tNomChamp.setText("monChamp                         ");

		
		Label lPointVirgule = new Label(compositeGauche,SWT.NONE);
		lPointVirgule.setText(";");
		
		Composite compositeDroit = new Composite(compositeChamps, SWT.NONE);
		compositeDroit.setLayout(new RowLayout());
	
		ToolBar toolbar = new ToolBar(compositeDroit, SWT.NONE);
		btnAjouter = new ToolItem(toolbar, SWT.PUSH);
		Image imageBtnAjouter = new Image(compositeDroit.getDisplay(), "icones/plus-icon32.png");
		btnAjouter.setImage(imageBtnAjouter);
		btnAjouter.setToolTipText("Ajouter le champ");

		listenerBoutons = new BoutonsListener();
		btnAjouter.addSelectionListener(listenerBoutons);
	}

	private void createCompositeTemplate(Composite compositeGauche) {
		compositeTemplate = new Composite(compositeGauche, SWT.NONE);
		compositeTemplate.setLayout(new RowLayout());
		Label chevronGauche = new Label(compositeTemplate, SWT.NONE);
		chevronGauche.setText("<");
		tTemplateList = new Text(compositeTemplate, SWT.NONE);
		Label chevronDroit = new Label(compositeTemplate, SWT.NONE);
		chevronDroit.setText(">");
		compositeTemplate.setVisible(false);
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
		if (champ.getType().equals(Classe.TYPE_COLLECTION))
			lTypeChamp.setText(champ.getType()+"<"+champ.getTemplateCollection()+">");
		else
			lTypeChamp.setText(champ.getType());
		lTypeChamp.setForeground(bleu);
	
		Label lNomChamp = new Label(compositeGauche, SWT.NONE);
		lNomChamp.setText(champ.getNom()+";");

//		Label lPointVirgule = new Label(compositeGauche,SWT.NONE);
//		lPointVirgule.setText(";");

		Composite compositeDroit = new Composite(compositeChamps, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		compositeDroit.setLayout(rowLayout);
		
		ToolBar toolbar = new ToolBar(compositeDroit, SWT.FLAT | SWT.WRAP | SWT.RIGHT);		 
		
		btnMoins = new ToolItem(toolbar, SWT.PUSH);
		Image imageBtnMoins = new Image(compositeDroit.getDisplay(), "icones/minus-icon32.png");
		btnMoins.setData(champ.getNom());
		btnMoins.setImage(imageBtnMoins);		    
		btnMoins.addSelectionListener(listenerBoutons);
		btnMoins.setToolTipText("Supprime le champ");
		
		if (! champ.getType().equals("ArrayList")) {
			btnCons = new ToolItem(toolbar, SWT.CHECK);
			Image imageBtnCons = new Image(compositeDroit.getDisplay(), "icones/hash-icon32.png");
			btnCons.setData(champ.getNom());
			btnCons.setImage(imageBtnCons);		    
			btnCons.setSelection(champ.isConstruction());
			btnCons.addSelectionListener(listenerBoutons);
			btnCons.setToolTipText("Active/désactive le champ dans le constructeur");
		}	

		

		btnGetter = new ToolItem(toolbar, SWT.CHECK);
		Image imageBtnGet = new Image(compositeDroit.getDisplay(), "icones/letter-uppercase-G-icon32.png");
		btnGetter.setData(champ.getNom());
		btnGetter.setImage(imageBtnGet);		    
		btnGetter.setSelection(champ.isGetter());
		btnGetter.addSelectionListener(listenerBoutons);
		btnGetter.setToolTipText("Active/désactive le getter pour ce champ");

		if (! champ.getType().equals("ArrayList")) {
			btnSetter = new ToolItem(toolbar, SWT.CHECK);
			Image imageBtnSet = new Image(compositeDroit.getDisplay(), "icones/letter-uppercase-S-icon32.png");
			btnSetter.setData(champ.getNom());
			btnSetter.setImage(imageBtnSet);		    
			btnSetter.setSelection(champ.isSetter());
			btnSetter.addSelectionListener(listenerBoutons);
			btnSetter.setToolTipText("Active/désactive le setter pour ce champ");
		}

		btnEquals = new ToolItem(toolbar, SWT.CHECK);
		Image imageBtnEquals = new Image(compositeDroit.getDisplay(), "icones/equal-icon32.png");
		btnEquals.setData(champ.getNom());
		btnEquals.setImage(imageBtnEquals);		    
		btnEquals.setSelection(champ.isEgalite());
		btnEquals.addSelectionListener(listenerBoutons);
		btnEquals.setToolTipText("Active/désactive le champ dans la fonction equals()");

		btnToString = new ToolItem(toolbar, SWT.CHECK);
		Image imageBtnToString = new Image(compositeDroit.getDisplay(), "icones/at-icon32.png");
		btnToString.setData(champ.getNom());
		btnToString.setImage(imageBtnToString);		    
		btnToString.setSelection(champ.isAffichage());
		btnToString.addSelectionListener(listenerBoutons);
		btnToString.setToolTipText("Active/désactive le champ dans la fonction toString()");
	}



	private void initCadreClasse(Composite parent) {
		Composite principal = new Composite(parent, SWT.NONE);
		principal.setLayout(new GridLayout(1, false));

		Composite compositeNom = new Composite(principal, SWT.NONE);
		compositeNom.setLayout(new RowLayout());

		createClassLabel(compositeNom);
		Text tNomClass = new Text(compositeNom, SWT.NONE);
		tNomClass.setText(classe.getNom());
		Label lOuvrant = new Label(compositeNom, SWT.NONE);
		lOuvrant.setText(" {");

		compositeChamps = new Composite(principal, SWT.NONE);
		compositeChamps.setLayout(new GridLayout(2, false));
		
		Composite compositeFin = new Composite(principal, SWT.NONE);
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
			boolean etat = ((ToolItem)e.widget).getSelection();
			if (e.widget==btnMoins) {
				traiterSuppressionChamp(nomChamp);
			}
			
			if (e.widget==btnEquals) {
				champ.setEgalite(etat);
			}
			
			if (e.widget==btnToString) {
				champ.setAffichage(etat);
			}			
			
			if (e.widget==btnGetter) {
				champ.setGetter(etat);
			}			
			
			if (e.widget==btnSetter) {
				champ.setSetter(etat);
			}	
			
			if (e.widget==btnCons) {
				champ.setConstruction(etat);
			}
		}

		private void traiterSuppressionChamp(String data) {
			classe.remove(data);
			
			refreshCompositeChamps();
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
			Champ champ = new Champ(cbVisibilite.getText(), tNomChamp.getText(), cbType.getText());
			if (cbType.getText().equals(Classe.TYPE_COLLECTION))
				champ.setTemplateCollection(tTemplateList.getText());
			
			if (classe.contains(champ))
				MessageDialog.openError(compositePage.getShell(), "Error", "Un champ avec ce nom existe déjà !");
			else {
				classe.add(champ);
	
				refreshCompositeChamps();
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

	public class ModificationTypeListener implements ModifyListener {
	
		@Override
		public void modifyText(ModifyEvent e) {
			if (((CCombo)e.widget).getText().equals(Classe.TYPE_COLLECTION))
				compositeTemplate.setVisible(true);
			else
				compositeTemplate.setVisible(false);
		}
	
	}
	
	public String getFileLocation() {
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