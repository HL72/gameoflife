package descripteur;

public class Descripteur {

	private String description;
	private String interfaceNom;
	private String classeNom;

	public Descripteur(String desc, String interfaceNom, String classeNom) {
		super();
		this.description = desc;
		this.interfaceNom = interfaceNom;
		this.classeNom = classeNom;
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
		return description + " (" + classeNom + " de type " + interfaceNom + ")" ;
	} 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
