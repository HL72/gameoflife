package plateforme;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import plateforme.interfaces.Application;
import plateforme.interfaces.Moniteur;
import plateforme.interfaces.Producteur;
import descripteur.Descripteur;

public final class Plateforme {

	private static List<String> interfacesAutorisees = Arrays.asList(Application.class.getName(),
			Moniteur.class.getName(), Producteur.class.getName());

	private static Map<String, List<Descripteur>> plugins = new HashMap<String, List<Descripteur>>();
	private static List<Descripteur> nonCharges = new ArrayList<Descripteur>();

	private static List<Moniteur> moniteurs = new ArrayList<Moniteur>();

	private static Plateforme instance = null;

	private Plateforme() {
		super();
		for (String i : interfacesAutorisees) {
			plugins.put(i, new ArrayList<>());
		}
	}

	public static Plateforme getInstance() throws Exception {

		if (instance == null) {
			instance = new Plateforme();
		}
		return instance;
	}

	public static Plugin getPlugin(Descripteur desc) throws Exception {

		String classNom = desc.getClasseNom();
		String ifaceNom = desc.getInterfaceNom();
		if (Class.forName(ifaceNom).isAssignableFrom(Class.forName(classNom))) {
			Constructor<?> constructeur = Class.forName(classNom).getDeclaredConstructor(Plateforme.class,
					Descripteur.class);
			return (Plugin) constructeur.newInstance(instance, desc);
		} else {
			throw new Exception("Echec instanciation plugin :" + classNom + " n'est pas un " + ifaceNom);
		}

	}

	public static Map<String, List<Descripteur>> getPlugins() {
		return plugins;
	}

	public static List<Descripteur> getPlugins(String interfaceNom) {
		List<Descripteur> desc = new ArrayList<Descripteur>();
		if (interfacesAutorisees.contains(interfaceNom)) {
			desc = plugins.get(interfaceNom);
		}
		return desc;
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
			String interfaceNom = (String) p.get("interface");
			String desc = (String) p.get("desc");
			String classeNom = (String) p.get("class");
			Descripteur d = new Descripteur(desc, interfaceNom, classeNom);
			if (interfacesAutorisees.contains(interfaceNom)) {
				plugins.get(interfaceNom).add(d);
			} else {
				nonCharges.add(d);
			}
		}
	}

	public static void demarrer(String fichier) throws Exception {

		Plateforme.getInstance();
		loadConfig(fichier);

		List<Descripteur> moniteursDesc = plugins.get(Moniteur.class.getName());

		// TODO traiter le moniteur comme un plugin à part entière
		for (Descripteur mDesc : moniteursDesc) {
			Plugin p = getPlugin(mDesc);
			Moniteur m = (Moniteur) p;
			m.notifier(mDesc, EtatPlugin.DEMARRE);
			moniteurs.add(m);
		}

		// TODO initialiser l'application comme un plugin
		List<Descripteur> apps = plugins.get(Application.class.getName());
		for (Descripteur app : apps) {
			Plugin p = getPlugin(app);
			notifierMoniteurs(app, EtatPlugin.DEMARRE);
			((Application) p).executer();
		}

		for (Entry<String, List<Descripteur>> entry : plugins.entrySet()) {
			for (Descripteur d : entry.getValue()) {
				notifierMoniteurs(d, EtatPlugin.CHARGE);
			}
		}

		afficherPlugins(EtatPlugin.DEMARRE);
		afficherPlugins(EtatPlugin.CHARGE);
		afficherPlugins(EtatPlugin.NON_CHARGE);

		// TODO coder puis initialiser l'afficheur

	}

	private static void afficherPlugins(EtatPlugin e) {
		for (Moniteur m : moniteurs) {
			m.afficher(e);
		}
	}

	private static void notifierMoniteurs(Descripteur d, EtatPlugin e) {
		for (Moniteur m : moniteurs) {
			m.notifier(d, e);
		}
	}

	public static void main(String[] args) throws Exception {
		Plateforme.demarrer("config.txt");
	}
}
