package plugins.producteurs;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Producteur;
import plugins.jeudelavie.MatriceModele;
import plugins.jeudelavie.MatriceModeleImpl;

public class ProducteurImpl extends Plugin implements Producteur {
	
	private MatriceModele matrice;

	public ProducteurImpl(Plateforme plateforme, Descripteur descripteur) {
		super(plateforme, descripteur);
		initMatrice();
	}
	
	private void initMatrice() {
		matrice = new MatriceModeleImpl(new int[10][10]);
		matrice.getMatrice()[0][1] = 1;
		matrice.getMatrice()[5][5] = 1;
		matrice.getMatrice()[3][6] = 1;
		matrice.getMatrice()[7][8] = 1;
		matrice.getMatrice()[4][1] = 1;
	}

	@Override
	public MatriceModele getMatrice() {
		return matrice;
	}
	
}
