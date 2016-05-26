package plugins.moniteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	@Override
	public void afficher(Map<String, List<Descripteur>> ls) {
		// TODO Auto-generated method stub
		Set<String> typesPlugin = ls.keySet();
		for (String type : typesPlugin) {
			System.out.println("Type plugin : " + type);
			List<Descripteur> values = ls.get(type);
			for (Descripteur descripteur : values) {
				System.out.println(descripteur.toString());
			}
		}
	}
}
