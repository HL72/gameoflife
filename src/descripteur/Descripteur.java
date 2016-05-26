package descripteur;

public class Descripteur {

	private String nom;
	private String classeNom;

	public Descripteur(String nom, String classeNom) {
		super();
		this.nom = nom;
		this.classeNom = classeNom;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public void setClasseNom(String classeNom) {
		this.classeNom = classeNom;
	}

	@Override
	public String toString() {
		return classeNom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
	
}
