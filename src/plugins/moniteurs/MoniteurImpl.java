package plugins.moniteurs;

import java.util.ArrayList;
import java.util.List;

import plateforme.EtatPlugin;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Moniteur;
import descripteur.Descripteur;

public class MoniteurImpl extends Plugin implements Moniteur {

	private List<Descripteur> pluginsNonCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsEnCours = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsArretes = new ArrayList<Descripteur>();

	public MoniteurImpl(Plateforme p, Descripteur d) {
		super(p,d);
	}

	@Override
	public void afficher(EtatPlugin e) {

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
	public void notifier(Descripteur d, EtatPlugin e) {

		if (EtatPlugin.NON_CHARGE.equals(e)) {
			pluginsNonCharges.add(d);
			pluginsCharges.remove(d);
		}
		if (EtatPlugin.CHARGE.equals(e)) {
			pluginsCharges.add(d);
			pluginsNonCharges.remove(d);
		} else if (EtatPlugin.DEMARRE.equals(e)) {
			pluginsEnCours.add(d);
			pluginsArretes.remove(d);
		} else if (EtatPlugin.ARRETE.equals(e)) {
			pluginsArretes.add(d);
			pluginsEnCours.remove(d);
		}

	}

}
