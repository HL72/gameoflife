package plugins.producteurs;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Producteur;
import plugins.jeudelavie.MatriceModele;

/*
 * Producteur du jeu de la vie
 * Structure: le tube
 */
public class ProducteurImplTube extends Plugin implements Producteur {
	
	private MatriceModele matrice;

	public ProducteurImplTube(Plateforme plateforme, Descripteur descripteur) {
		super(plateforme, descripteur);
		initMatrice();
	}
	
	private void initMatrice() {
		int[][] m = new int[10][10];
		m[0][1] = 1;
		m[5][5] = 1;
		m[3][6] = 1;
		m[7][8] = 1;
		m[4][1] = 1;

		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
