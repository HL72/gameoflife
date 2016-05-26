package plugins.moniteur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import descripteur.Descripteur;

public class MoniteurImpl implements Moniteur{

	private List<Descripteur> descripteurs = new ArrayList<Descripteur>();

	public MoniteurImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		Set<String> ifacesPlugin = ls.keySet();
		for (String iface : ifacesPlugin) {
			System.out.println("Type plugin : " + iface);
			List<Descripteur> values = ls.get(iface);
			for (Descripteur descripteur : values) {
				System.out.println(descripteur.toString());
			}
		}
	}
}
