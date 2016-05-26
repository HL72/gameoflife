package descripteur;

public class Descripteur {

	private String nom;
	private String classeNom;
	private String etat;

	public Descripteur(String nom, String classeNom) {
		super();
		this.nom = nom;
		this.classeNom = classeNom;
		this.etat = "cree";
	}

	public String getClasseNom() {
		return classeNom;
	}

	public void setClasseNom(String classeNom) {
		this.classeNom = classeNom;
	}

	@Override
	public String toString() {
		return "Description : " + nom + "\nEtat : " + etat;
	} 

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
