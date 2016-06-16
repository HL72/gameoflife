package plugins.moniteurs;

import java.util.ArrayList;
import java.util.List;

import plateforme.EtatPlugin;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.communication.Action;
import plateforme.communication.ActionType;
import plateforme.communication.Observer;
import plateforme.interfaces.Moniteur;
import descripteur.Descripteur;

public class MoniteurImpl extends Plugin implements Moniteur, Observer {

	private List<Descripteur> pluginsNonCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsEnCours = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsArretes = new ArrayList<Descripteur>();

	public MoniteurImpl(Plateforme p, Descripteur d) {
		super(p,d);
		p.register(this);
	}

	public void afficherConsole(EtatPlugin e) {

		if (EtatPlugin.NON_CHARGE.equals(e)) {
			System.out.println("----------------------------------------------------- " + EtatPlugin.NON_CHARGE
					+ "--------------------------------------------------");
			System.out.println(
					"| Plugins non charges :                                                                                          |");
			for (Descripteur d : pluginsNonCharges) {
				System.out.println("|	 - " + d.toString());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
		}

		if (EtatPlugin.CHARGE.equals(e)) {
			System.out.println("----------------------------------------------------- " + EtatPlugin.CHARGE
					+ "------------------------------------------------------");
			System.out.println(
					"| Plugins charges :                                                                                              |");
			for (Descripteur d : pluginsCharges) {
				System.out.println("|	 - " + d.toString());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
		}

		if (EtatPlugin.DEMARRE.equals(e)) {
			System.out.println("----------------------------------------------------- " + EtatPlugin.DEMARRE
					+ "-----------------------------------------------------");
			System.out.println(
					"| Plugins en cours d'execution :                                                                                 |");
			for (Descripteur d : pluginsEnCours) {
				System.out.println("|	 - " + d.toString());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
		}

		if (EtatPlugin.ARRETE.equals(e)) {
			System.out.println("----------------------------------------------------- " + EtatPlugin.ARRETE
					+ "-----------------------------------------------------");
			System.out.println(
					"| Plugins arretes :                                                                                              |");
			for (Descripteur d : pluginsArretes) {
				System.out.println("|	 - " + d.toString());
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
		}

	}


	@Override
	public void notify(Action a) {
		ActionType t = a.getActionType();
		
		if(ActionType.CHARGEMENT_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsCharges.add(d);
		}
		else if(ActionType.EXECUTION_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsEnCours.add(d);
		}
		else if(ActionType.ARRET_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsEnCours.remove(d);
			pluginsArretes.add(d);
		}
		else if(ActionType.AFFICHER_MONITEUR.equals(t)){
			afficherConsole(EtatPlugin.CHARGE);
			afficherConsole(EtatPlugin.DEMARRE);
			afficherConsole(EtatPlugin.ARRETE);
		}
	}
}
