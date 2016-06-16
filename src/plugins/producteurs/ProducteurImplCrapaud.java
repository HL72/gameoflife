package plugins.producteurs;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Producteur;
import plugins.jeudelavie.MatriceModele;

/*
 * Producteur du jeu de la vie
 * Structure: le navire
 */
public class ProducteurImplCrapaud extends Plugin implements Producteur {
	
	private MatriceModele matrice;

	public ProducteurImplCrapaud(Plateforme plateforme, Descripteur descripteur) {
		super(plateforme, descripteur);
		initMatrice();
	}
	
	private void initMatrice() {
		int[][] m = new int[10][10];
		m[3][5] = 1;
		m[4][5] = 1;
		m[5][5] = 1;
		m[4][4] = 1;
		m[5][4] = 1;
		m[6][4] = 1;

		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
