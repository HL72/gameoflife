package plugins.jeudelavie;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Producteur;

/*
 * Producteur par défaut du jeu de la vie
 * Structure: le bloc
 */
public class ProducteurImplDefaut extends Plugin implements Producteur {

	private MatriceModele matrice;
	
	public ProducteurImplDefaut(Plateforme plateforme, Descripteur descripteur) {
		super(plateforme, descripteur);
		initMatrice();
	}
	
	private void initMatrice() {
		int[][] m = new int[10][10];
		m[1][1] = 1;
		m[1][2] = 1;
		m[2][1] = 1;
		m[2][2] = 1;
		
		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
