package modele.plateau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import modele.Bonbon;
import modele.CandyException;
import modele.Sortes;

public class PlateauFactory {
	
	private static final String SANS_OBJECTIF = "SANS_OBJECTIF";
	private static final String OBJECTIF_DEPLACEMENT_LIMITE = "DEPLACEMENT_LIMITE";
	private static final String OBJECTIF_TEMPS_LIMITE = "TEMPS_LIMITE";
	private static final String OBJECTIF_ELIMINER_MERINGUE = "ELIMINER_MERINGUE";
	private static final String NB_LIG = "NB_LIG";
	private static final String NB_COL = "NB_COL";
	private static final String NB_DEPL = "NB_DEPL";
	private static final String SCORE = "SCORE";
	private static final String TEMPS = "TEMPS";
	private static int nLig;
	private static int nCol;
	

	public static Plateau chargerPlateau(String nomFichier) throws CandyException {
		File fichier = new File(nomFichier);
		 FileReader fr = null;
		 BufferedReader br = null;
		
		try
		{
		    fr = new FileReader (fichier);
		    br = new BufferedReader (fr);
		    
		    String typePlateau = br.readLine().split(";")[0];
		    
		    if (typePlateau.equals(SANS_OBJECTIF))
		    	return chargerPlateauSansObjectif(br);	
		    
		    if (typePlateau.equals(OBJECTIF_DEPLACEMENT_LIMITE))
		    	return chargerPlateauScoreEnDeplacementLimite(br);		
		    
		    if (typePlateau.equals(OBJECTIF_TEMPS_LIMITE))
		    	return chargerPlateauScoreEnTempsLimite(br);	
		    
		    if (typePlateau.equals(OBJECTIF_ELIMINER_MERINGUE))
		    	return chargerPlateauEliminerMeringue(br);
		    
		    throw new CandyException("Type de plateau non reconnu");
		    
		}
		catch (FileNotFoundException exception)
		{
		    throw new CandyException("Le fichier "+nomFichier+" ne correspond à aucun plateau");
		} catch (IOException e) {
		    throw new CandyException("Impossible de lire dans le fichier "+nomFichier);
		}
		finally {
			try {
				if (br!=null) br.close();
				if (fr!=null) fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static Plateau chargerPlateauSansObjectif(BufferedReader br) throws CandyException {
		int	nLig = 0, nCol = 0;
		
		try {
			String s = br.readLine();
			String[] t = s.split(";"); 
			if (t[0].equals(NB_LIG))
				nLig = Integer.parseInt(t[1]);
			
			s = br.readLine();
			t = s.split(";");
			if (t[0].equals(NB_COL))
				nCol = Integer.parseInt(t[1]);
			
			Plateau p = new Plateau(nLig, nCol);
			
			lireGrille(br, nLig, nCol, p);
			
			return p;
			
		} catch (IOException e) {
			throw new CandyException("Erreur durant la lecture du fichier ");
		}
	}
	
	private static Plateau chargerPlateauScoreEnDeplacementLimite(BufferedReader br) throws CandyException {
		int	nLig = 0, nCol = 0, nbDepl = 0, score = 0;
		
		try {
			String s = br.readLine();
			String[] t = s.split(";"); 
			
			if (t[0].equals(NB_DEPL))
				nbDepl = Integer.parseInt(t[1]);
			
			s = br.readLine();
			t = s.split(";");
			if (t[0].equals(SCORE))
				score = Integer.parseInt(t[1]);
			
			s = br.readLine();
			t = s.split(";");
			if (t[0].equals(NB_LIG))
				nLig = Integer.parseInt(t[1]);
			
			s = br.readLine();
			t = s.split(";");
			if (t[0].equals(NB_COL))
				nCol = Integer.parseInt(t[1]);
			
			Plateau p = new PlateauNombreDeplacementsLimite(nLig, nCol, nbDepl, score);
			
			lireGrille(br, nLig, nCol, p);
			
			return p;
			
		} catch (IOException e) {
			throw new CandyException("Erreur durant la lecture du fichier ");
		}
	}

	private static Plateau chargerPlateauScoreEnTempsLimite(BufferedReader br) throws CandyException {
		nLig = 0;
		nCol = 0;
		int nbSec = 0, score = 0;
		try {
			String s = br.readLine();
			String[] t = s.split(";"); 
			
			if (t[0].equals(TEMPS))
				nbSec = Integer.parseInt(t[1]);
			
			s = br.readLine();
			t = s.split(";");
			if (t[0].equals(SCORE))
				score = Integer.parseInt(t[1]);
			
			lireDimensionsGrilles(br);
			
			Plateau p = new PlateauNombreDeplacementsLimite(nLig, nCol, nbSec, score);
			
			lireGrille(br, nLig, nCol, p);
			
			return p;
			
		} catch (IOException e) {
			throw new CandyException("Erreur durant la lecture du fichier ");
		}
	}

	private static Plateau chargerPlateauEliminerMeringue(BufferedReader br) throws CandyException {
		nLig = 0;
		nCol = 0;
		int nbSec = 0;
		try {
			String s = br.readLine();
			String[] t = s.split(";"); 
			
			if (t[0].equals(TEMPS))
				nbSec = Integer.parseInt(t[1]);

			
			lireDimensionsGrilles(br);
			
			Plateau p = new PlateauEliminerMeringue(nLig, nCol, nbSec);
			
			lireGrille(br, nLig, nCol, p);
			
			return p;
			
		} catch (IOException e) {
			throw new CandyException("Erreur durant la lecture du fichier ");
		}
	}

	private static void lireDimensionsGrilles(BufferedReader br) throws IOException {
		String s;
		String[] t;
		s = br.readLine();
		t = s.split(";");
		if (t[0].equals(NB_LIG))
			nLig = Integer.parseInt(t[1]);
		
		s = br.readLine();
		t = s.split(";");
		if (t[0].equals(NB_COL))
			nCol = Integer.parseInt(t[1]);
	}

	private static void lireGrille(BufferedReader br, int nLig, int nCol, Plateau p) throws IOException {
		String s;
		String[] t;
		for (int l = 0; l < nLig; l++) {
			s = br.readLine();
			t = s.split(";");
			for (int c=0; c<nCol; c++) {
				int couleur = Integer.parseInt(t[c]);
				p.initialiserBonbon(new Bonbon(Sortes.values()[couleur]), l, c);
			}
			
		}
	}
}
