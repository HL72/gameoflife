package plugins.modeles;

import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Modele;
import descripteur.Descripteur;

public class ModeleImpl extends Plugin implements Modele {

	private int[][] matrice = new int[0][0];
	
	public ModeleImpl(Plateforme p, Descripteur d) {
		super(p,d);
	}

	public ModeleImpl(Plateforme p, Descripteur d, int[][] matrice) {
		super(p,d);
		this.matrice = matrice;
	}

	public int[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
	
	
}
