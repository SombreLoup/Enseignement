package sport;

public class Championat {

		private	String	nom;
		private	boolean	individuel;
		
		public Championat(String nom, boolean individuel) {
			this.nom = nom;
			this.individuel = individuel;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public boolean isIndividuel() {
			return individuel;
		}

		public void setIndividuel(boolean individuel) {
			this.individuel = individuel;
		}

		@Override
		public String toString() {
			return "Championat [nom=" + nom + ", individuel=" + individuel + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Championat other = (Championat) obj;
			if (individuel != other.individuel)
				return false;
			if (nom == null) {
				if (other.nom != null)
					return false;
			} else if (!nom.equals(other.nom))
				return false;
			return true;
		}
		
		
}
