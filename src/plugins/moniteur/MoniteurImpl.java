package plugins.moniteur;

import java.util.ArrayList;
import java.util.List;

import descripteur.Descripteur;

public class MoniteurImpl implements Moniteur{

	private List<Descripteur> descripteurs = new ArrayList<Descripteur>();

	public MoniteurImpl(List<Descripteur> descripteurs) {
		super();
		this.descripteurs = descripteurs;
	}

	public List<Descripteur> getDescripteurs() {
		return descripteurs;
	}

	public void setDescripteurs(List<Descripteur> descripteurs) {
		this.descripteurs = descripteurs;
	}
}
