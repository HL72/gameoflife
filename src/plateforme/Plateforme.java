package plateforme;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import descripteur.Descripteur;
import plugins.moniteur.Moniteur;

public class Plateforme {

	private static Map<Type, List<Descripteur>> plugins = new HashMap<Type, List<Descripteur>>();

	private static Plateforme instance = null;
	private Moniteur moniteur = null;

	private Plateforme() throws Exception {
		loadConfig("config.txt");
		moniteur = (Moniteur) getPlugin(plugins.get(Type.MONITEUR).get(0));
		moniteur.afficher(plugins);
	}

	public static Plateforme getInstance() throws Exception {

		if (instance == null) {
			instance = new Plateforme();
		}
		return instance;
	}

	public Object getPlugin(Descripteur desc) throws Exception {
		String classNom = desc.getClasseNom();
		return Class.forName(classNom).newInstance();
	}

	public static Map<Type, List<Descripteur>> getPlugins() {
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
			Type type = Type.valueOf((String) p.get("type"));
			String desc = (String) p.get("desc");
			String classe = (String) p.get("class");
			List<Descripteur> descs = plugins.get(type);
			if (descs == null) {
				descs = new ArrayList<Descripteur>();
				plugins.put(type, descs);
			}
			descs.add(new Descripteur(desc, classe));
		}
	}

}
