package descripteur;

public class Descripteur {

	private String description;
	private String interfaceNom;
	private String classeNom;
	private String etat;

	public Descripteur(String nom, String interfaceNom, String classeNom) {
		super();
		this.description = nom;
		this.interfaceNom = interfaceNom;
		this.classeNom = classeNom;
		this.etat = "cree";
	}

	public String getInterfaceNom() {
		return interfaceNom;
	}

	public void setInterfaceNom(String interfaceNom) {
		this.interfaceNom = interfaceNom;
	}

	public String getClasseNom() {
		return classeNom;
	}

	public void setClasseNom(String classeNom) {
		this.classeNom = classeNom;
	}

	@Override
	public String toString() {
		return "Description : " + description + "\nEtat : " + etat;
	} 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
