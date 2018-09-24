package model;

import java.io.PrintStream;
import java.util.ArrayList;

public class Classe {

	private String nom;
	private	String paquet;
	private ArrayList<Champ> listeChamps = new ArrayList<Champ>();

	public Classe() {
		super();
		listeChamps.add(new Champ("public", "Truc", "int"));
	}

	public Classe(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int size() {
		return listeChamps.size();
	}

	public ArrayList<Champ> getListeChamps() {
		return listeChamps;
	}

	public boolean contains(Champ c) {
		return rechercherChamp(c.getNom()) != null;
	}

	public boolean add(Champ e) {
		return listeChamps.add(e);
	}

	public boolean remove(String nom) {
		Champ c = rechercherChamp(nom);
		if (c==null)
			return false;
		return listeChamps.remove(c);
	}

	private Champ rechercherChamp(String nom2) {
		for (Champ champ : listeChamps) {
			if (champ.getNom().equals(nom2))
				return champ;
		}
		return null;
	}

	public Object[] toArray() {
		return listeChamps.toArray();
	}

	@Override
	public String toString() {
		return "Classe [nom=" + nom + ", listeChamps=" + listeChamps + "]";
	}

	public void genererFichier(PrintStream out) {
		String ident1 = "\t";

		out.println("package "+paquet+";\n");
		out.println("public class " + nom + " {");
		
		genererChamps(out, ident1);
		genererConstructeurParDefaut(out, ident1);
		genererConstructeurParCopie(out, ident1);
		genererConstructeurParValeurs(out, ident1);
		genererGetter(out, ident1);
		genererSetter(out, ident1);
		genererEquals(out, ident1);
		genererToString(out, ident1);

		out.println("}");
	}

	private void genererToString(PrintStream out, String ident1) {
		String ident2 = ident1 + "\t";
		out.println(ident1 + "public String toString() {\n");
		String eq = ident2 + "\"" + nom + "[";

		if (!listeChamps.isEmpty()) {
			if (listeChamps.get(0).isAffichage())
				eq += listeChamps.get(0).getNom() + "=\"+" + listeChamps.get(0).getNom();

			for (Champ champ : listeChamps.subList(1, listeChamps.size())) {
				if (champ.isAffichage()) {
					eq += "+\", " + champ.getNom() + "=\"+" + champ.getNom();
				}
			}
		}
		eq += "+\"]\"";
		
		out.println(ident2+"return "+eq+";");
		out.println(ident1+"}\n");
	}

	private void genererEquals(PrintStream out, String ident1) {
		String ident2 = ident1 + "\t";
		String ident3 = ident2 + "\t";
		String eq = ident1 + "public boolean equals(Object obj) {\n";

		eq += ident2 + "if (obj==null)\n";
		eq += ident3 + "return false;\n\n";

		eq += ident2 + "if (this==obj)\n";
		eq += ident3 + "return true;\n\n";

		eq += ident2 + "if (obj.getClass() != this.getClass())\n";
		eq += ident3 + "return false;\n\n";

		eq += ident2 + nom + " myObj = (" + nom + ")obj;\n\n";

		for (Champ champ : listeChamps) {
			if (champ.isEgalite() && champ.getType().equals("String")) {
				eq += ident2 + "if (!this." + champ.getNom() + ".equals(myObj." + champ.getNom() + "))\n";
			} else if (champ.isEgalite() && !champ.getType().equals("String")) {
				eq += ident2 + "if (this." + champ.getNom() + " != myObj." + champ.getNom() + ")\n";
			}

			if (champ.isEgalite())
				eq += ident3 + "return false;\n\n";
		}

		eq += ident2 + "return true;\n";
		eq += ident1 + "}\n";
		out.println(eq);
	}

	private void genererGetter(PrintStream out, String ident1) {
		for (Champ champ : listeChamps) {
			if (champ.isGetter()) {
				String nomGetter = "get";
				if (champ.getType().equals("boolean"))
					nomGetter = "is";

				nomGetter += Character.toUpperCase(champ.getNom().charAt(0));
				nomGetter += champ.getNom().substring(1, champ.getNom().length());
				out.println(ident1 + "public " + champ.getType() + " " + nomGetter + "() {");
				out.println(ident1 + "\t" + "return " + champ.getNom() + ";");
				out.println(ident1 + "}\n");
			}
		}
	}

	private void genererSetter(PrintStream out, String ident1) {
		for (Champ champ : listeChamps) {
			if (champ.isSetter()) {
				String nomSetter = "set";

				nomSetter += Character.toUpperCase(champ.getNom().charAt(0));
				nomSetter += champ.getNom().substring(1, champ.getNom().length());
				out.println(
						ident1 + "public void " + nomSetter + "(" + champ.getType() + " _" + champ.getNom() + ") {");
				out.println(ident1 + "\t" + champ.getNom() + " = _" + champ.getNom() + ";");
				out.println(ident1 + "}\n");
			}
		}
	}

	private void genererConstructeurParDefaut(PrintStream out, String ident1) {
		out.println(ident1 + "public \t"+nom + "() {");
		out.println(ident1 + "}\n");
	}

	private void genererConstructeurParCopie(PrintStream out, String ident1) {
		out.println(ident1 + "public \t"+nom + "(" + nom + " obj" + ") {");

		for (Champ champ : listeChamps) {
			out.println(ident1 + "\tthis." + champ.getNom() + " = obj." + champ.getNom() + ";");
		}

		out.println(ident1 + "}\n");
	}

	private void genererConstructeurParValeurs(PrintStream out, String ident1) {
		if (listeChamps.isEmpty())
			return;
		
		out.print(ident1 + "public \t"+ nom + "(");

		if (!listeChamps.isEmpty()) {
			if (listeChamps.get(0).isConstruction())
				out.print(listeChamps.get(0).getType() + " _" + listeChamps.get(0).getNom());
			for (Champ champ : listeChamps.subList(1, listeChamps.size())) {
				if (champ.isConstruction())
					out.print(", " + champ.getType() + " _" + champ.getNom());
			}
		}

		out.println(") {");

		for (Champ champ : listeChamps) {
			if (champ.isConstruction())
				out.println(ident1 + "\tthis." + champ.getNom() + " = _" + champ.getNom() + ";");
		}

		out.println(ident1 + "}\n");
	}

	private void genererChamps(PrintStream out, String ident1) {
		for (Champ champ : listeChamps) {
			out.print(ident1 + champ.getVisibilite() + " \t" + champ.getType() + " \t" + champ.getNom());
			if (champ.getValeurParDefaut().isEmpty())
				out.println(";\n");
			else
				out.println(" = " + champ.getValeurParDefaut() + ";\n");
		}
	}

	public static void main(String[] args) {
		Classe classe = new Classe("Personne");
		Champ nom = new Champ("private", "nom", "String");
		Champ age = new Champ("private", "age", "int");
		Champ marrié = new Champ("private", "marrié", "boolean");

		age.setConstruction(false);
		age.setValeurParDefaut("20");

		classe.add(nom);
		classe.add(age);
		classe.add(marrié);

		classe.genererFichier(System.out);
		
		
		String sElement = "MonPackage [in src [in TestWizard]]";
		System.out.println("Element = "+sElement);
		String delimiteurs = "\\[in";
		String[] elements = sElement.split(delimiteurs);
		System.out.println("Il y a "+elements.length+" éléments : ");
		String chemin = "";
		for (int i = elements.length-1; i>0; i--) {
			chemin += elements[i].replaceAll("\\]", " ").trim()+"/";
		}
		try {
		chemin += elements[0].replaceAll("\\]", " ").trim();
		}
		catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		System.out.println("Le chemin = "+chemin);

	}

	public String getPaquet() {
		return paquet;
	}

	public void setPaquet(String paquet) {
		this.paquet = paquet;
	}

}
