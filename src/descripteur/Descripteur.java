package descripteur;

public class Descripteur {

	private String description;
	private String interfaceNom;
	private String classeNom;
	private Boolean autorun;

	public Descripteur(String desc, String interfaceNom, String classeNom, Boolean autorun) {
		super();
		this.description = desc;
		this.interfaceNom = interfaceNom;
		this.classeNom = classeNom;
		this.autorun = autorun;
	}

	public Boolean getAutorun() {
		return autorun;
	}

	public void setAutorun(Boolean autorun) {
		this.autorun = autorun;
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
		return description + " (" + classeNom + ") de type (" + interfaceNom + ")" ;
	} 

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
