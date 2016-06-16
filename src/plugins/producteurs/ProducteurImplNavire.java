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
public class ProducteurImplNavire extends Plugin implements Producteur {
	
	private MatriceModele matrice;

	public ProducteurImplNavire(Plateforme plateforme, Descripteur descripteur) {
		super(plateforme, descripteur);
		initMatrice();
	}
	
	private void initMatrice() {
		int[][] m = new int[10][10];
		m[1][1] = 1;
		m[1][2] = 1;
		m[2][1] = 1;
		m[2][3] = 1;
		m[3][2] = 1;
		m[3][3] = 1;

		matrice = new MatriceModele(m);
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
