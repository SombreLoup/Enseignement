package edu.ul.yl.fr.patterntools.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;


import java.io.*;
import org.eclipse.ui.*;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the
 * provided container. If the container resource (a folder or a project) is
 * selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "java". If a
 * sample multi-page editor (also available as a template) is registered for the
 * same extension, it will be able to open it.
 */

public class PatternWizard extends Wizard implements INewWizard {
	private PatternWizardPage page;
	private ISelection selection;

	/**
	 * Constructor for PatternClasseWizard.
	 */
	public PatternWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new PatternWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We will
	 * create an operation and run it using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(monitor);
				} catch (CoreException e) {
					System.err.println("Exception ::::" + e);
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			System.err.println("Autre Exception :::::");
			e.printStackTrace();
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the file if missing or
	 * just replace its contents, and open the editor on the newly created file.
	 */

	private void doFinish(IProgressMonitor monitor) throws CoreException {
		
		String nomFichier = ResourcesPlugin.getWorkspace().getRoot().getLocation()+"/"+page.getChemin()+"/"+page.getClasse().getNom() + ".java";
		
		System.out.println("Nom du fichier : "+nomFichier);

		monitor.beginTask("Creating " + nomFichier, 2);


		final File file = new File(nomFichier);
		Path path = new Path(file.getPath());
		System.out.println("PATH = "+path);
		
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
		System.out.println("IFile = "+iFile);
		
		try {
				FileOutputStream out = new FileOutputStream(nomFichier, true);
				page.getClasse().genererFichier(new PrintStream(out));
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, monitor);
		
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		
		
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, iFile, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}

	
	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}