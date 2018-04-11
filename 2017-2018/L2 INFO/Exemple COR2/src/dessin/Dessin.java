package dessin;

import java.util.ArrayList;

import forme.IForme;

public class Dessin {
	private	ArrayList<IForme>	listeForme = new ArrayList<IForme>();

	private	String	nom;
	
	public Dessin(String nom) {
		this.nom = nom;
	}
	
	public boolean add(IForme e) {
		return listeForme.add(e);
	}

	public String getNom() {
		return nom;
	}
	
	public String toXml() {
		String s = "<Dessin nom=\""+nom+"\">\n";
		
		for (IForme f : listeForme)
			s += "\t"+f.toXML()+"\n";
		
		s += "</Dessin>\n";

		return s;
	}
}
