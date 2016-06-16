package plateforme;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import plateforme.communication.Action;
import plateforme.communication.AfficherMoniteur;
import plateforme.communication.ChargementPlugin;
import plateforme.communication.ExecutionPlugin;
import plateforme.communication.Observer;
import plateforme.communication.Subject;
import plateforme.interfaces.Application;
import plateforme.interfaces.Moniteur;
import plateforme.interfaces.Producteur;
import descripteur.Descripteur;

public final class Plateforme implements Subject {

	private static List<String> interfacesAutorisees = Arrays.asList(Application.class.getName(),
			Moniteur.class.getName(), Producteur.class.getName());

	private static Map<String, List<Descripteur>> plugins = new HashMap<String, List<Descripteur>>();
	private static List<Descripteur> nonCharges = new ArrayList<Descripteur>();

	private static Plateforme instance = null;
	
	private static Set<Observer> obs = new HashSet<>();

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
			Boolean autorun = Boolean.valueOf(p.getProperty("autorun"));
			Descripteur d = new Descripteur(desc, interfaceNom, classeNom, autorun);
			if (interfacesAutorisees.contains(interfaceNom)) {
				plugins.get(interfaceNom).add(d);
				if(autorun){
					getPlugin(d);
				}
			} else {
				nonCharges.add(d);
			}
		}
	}

	public static void demarrer(String fichier) throws Exception {

		Plateforme.getInstance();
		loadConfig(fichier);

	//TODO s�parer les notions de chargements et d'execution via un attribut autorun dans le descripteur
	//TODO tester si il est possible de prendre en compte les plugins non charg�s (cas du fichier mal form� ou de l'interface introuvable)
	for (Entry<String, List<Descripteur>> entry : plugins.entrySet()) {
		for (Descripteur d : entry.getValue()) {
			instance.notifyObservers(new ChargementPlugin(d));
			if(d.getAutorun()){
				instance.notifyObservers(new ExecutionPlugin(d));
			}
		}
	}
	instance.notifyObservers(new AfficherMoniteur());
		// TODO initialiser l'application comme un plugin
//		List<Descripteur> apps = plugins.get(Application.class.getName());
//		for (Descripteur app : apps) {
//			Plugin p = getPlugin(app);
//			notifierMoniteurs(app, EtatPlugin.DEMARRE);
//			((Application) p).executer();
//		}

	}

	public static void main(String[] args) throws Exception {
		Plateforme.demarrer("config.txt");
	}

	@Override
	public void register(Observer o) {
		obs.add(o);
	}

	@Override
	public void unregister(Observer o) {
		obs.remove(o);
	}

	@Override
	public void notifyObservers(Action a) {
		for (Observer o : obs) {
			o.notify(a);
		}
	}
}
