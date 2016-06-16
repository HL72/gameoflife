package plugins.jeudelavie;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.interfaces.Producteur;

/*
 * Producteur par défaut du jeu de la vie
 */
public class ProducteurImplDefaut implements Producteur {

	private MatriceModele matrice;
	
	public ProducteurImplDefaut(Plateforme plateforme, Descripteur descripteur) {
		initMatrice();
	}
	
	private void initMatrice() {
		int[][] m = new int[10][10];
		m[1][0] = 1;
		m[1][1] = 1;
		m[1][2] = 1;
		
		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
