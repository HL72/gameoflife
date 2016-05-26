package plateforme;

import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import descripteur.Descripteur;
import plugins.afficheurs.Afficheur;

public class Plateforme {

	private static List<Descripteur> afficheurs = new ArrayList<Descripteur>();

	private static Plateforme instance = null;

	private Plateforme() throws Exception {
		loadConfig("config.txt");
	}

	public static Plateforme getInstance() throws Exception {

		if (instance == null) {
			instance = new Plateforme();
		}
		return instance;
	}

	public Afficheur getAfficheur(Descripteur desc) throws Exception {
		String classNom = desc.getClasseNom();
		return (Afficheur) Class.forName(classNom).newInstance();
	}

	public List<Descripteur> getDescripteurs() {
		return afficheurs;
	}

	private static void loadConfig(String filename) throws Exception {
		char[] buffer = new char[500];
		FileReader fr = new FileReader(filename);
		fr.read(buffer);
		String bufferString = String.valueOf(buffer);
		String[] pluginsTxt = bufferString.split("\\*");

		for (int i = 0; i < pluginsTxt.length; i++) {
			Properties p = new Properties();
			p.load(new StringReader(pluginsTxt[i]));
			String classNom = null;
			String desc = null;
			Set<String> keys = (Set<String>) p.keySet();
			String type = (String) ((Hashtable<Object, Object>) p.keySet()).get("type");
			for (Object key : p.keySet()) {
				if (key.equals("class")) {
					classNom = (String) p.get(key);
				} else {
					desc = (String) p.get(key);
				}
				if (classNom != null && desc != null) {
					afficheurs.add(new Descripteur(desc, classNom));
					classNom = null;
					desc = null;
				}
			}
		}
	}

}
