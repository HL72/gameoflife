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
		m[0][9] = 1;
		m[0][8] = 1;
		m[1][8] = 1;
		m[2][8] = 1;
		m[3][7] = 1;
		m[3][6] = 1;
		m[2][6] = 1;
		m[8][0] = 1;
		m[7][1] = 1;
		m[7][2] = 1;
		m[8][2] = 1;
		m[9][2] = 1;

		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
