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

	private static Map<String, List<Descripteur>> plugins = new HashMap<String, List<Descripteur>>();

	private static Plateforme instance = null;
	private Moniteur moniteur = null;

	private Plateforme() throws Exception {
		loadConfig("config.txt");
		//TODO initialiser l'application comme un plugin
		//TODO traiter le moniteur comme un plugin à part entière
		//TODO coder puis initialiser l'afficheur
		moniteur = (Moniteur) getPlugin(plugins.get(Moniteur.class.getName()).get(0));
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
		String ifaceNom = desc.getInterfaceNom();
		if (Class.forName(ifaceNom).isAssignableFrom(Class.forName(classNom))) {
			return Class.forName(classNom).newInstance();
		}
		return new Object();
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
			String iface = (String) p.get("interface");
			String desc = (String) p.get("desc");
			String classe = (String) p.get("class");
			List<Descripteur> descs = plugins.get(iface);
			if (descs == null) {
				descs = new ArrayList<Descripteur>();
				plugins.put(iface, descs);
			}
			descs.add(new Descripteur(desc, iface,classe));
		}
	}

}
