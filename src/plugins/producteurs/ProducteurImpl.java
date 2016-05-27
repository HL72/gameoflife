package plugins.producteurs;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Modele;
import plateforme.interfaces.Producteur;
import plugins.modeles.ModeleImpl;

public class ProducteurImpl extends Plugin implements Producteur {

	private ModeleImpl modele = new ModeleImpl(plateforme,descripteur);

	public ProducteurImpl(Plateforme p, Descripteur d) {
		super(p,d);
	}

	@Override
	public Modele getModele() {
		int[][] matrice = new int[20][20]; 
		modele.setMatrice(matrice);
		return modele;
	}

}
