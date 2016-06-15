package plugins.jeudelavie;

public class MatriceModeleImpl implements MatriceModele {

	private int[][] matrice;

	public MatriceModeleImpl(int[][] matrice) {
		this.matrice = matrice;
	}

	public int[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}

}
