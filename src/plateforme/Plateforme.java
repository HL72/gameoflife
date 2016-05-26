package plateforme;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import descripteur.Descripteur;
import plugins.afficheurs.Afficheur;

public class Plateforme {

	private static Map<String,List<Descripteur>> plugins = new HashMap<String,List<Descripteur>>();

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

	public static Map<String, List<Descripteur>> getPlugins() {
		return plugins;
	}

	private static void loadConfig(String filename) throws Exception {
		char[] buffer = new char[(int) new File(filename).length()];
		FileReader fr = new FileReader(filename);
		fr.read(buffer);
		fr.close();
		String bufferString = String.valueOf(buffer);
		String[] pluginsTxt = bufferString.split("\\*");

		for (int i = 0; i < pluginsTxt.length; i++) {
			Properties p = new Properties();
			p.load(new StringReader(pluginsTxt[i]));

			String type = (String) p.get("type");
			String desc = (String) p.get("desc");
			String classe = (String) p.get("class");
			List<Descripteur> descs = plugins.get(type);
			descs.add(new Descripteur(desc, classe));
			
		}
	}

}
