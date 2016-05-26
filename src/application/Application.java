package application;

import java.util.List;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plugins.afficheurs.Afficheur;

public class Application {

	private Plateforme p = Plateforme.getInstance();
	private Afficheur afficheur;
	
	public Application() throws Exception {
	    String name = "Afficheur Olivier";
		List<Descripteur> descripteurs = p.getDescripteurs();
		System.out.println("Liste des afficheurs disponibles :");
		for (Descripteur descripteur : descripteurs) {
			System.out.println(descripteur);
			if (descripteur.getNom().equals(name)) {
				afficheur = p.getAfficheur(descripteur);
			}
		}
		String s = "\nCoucou";
		afficher(s);
	}

	public void afficher(Object o) {
		afficheur.afficher(o);
	}
	
	public static void main(String[] args) throws Exception {

		new Application();

	}
}
