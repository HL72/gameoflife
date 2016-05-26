package application.modele;

import java.util.List;

public class ModeleImpl implements Modele {

	private int hauteur;
	private int largeur;
	private List<Coordonnee> coordonnees;
	
	
	
	public ModeleImpl(int hauteur, int largeur, List<Coordonnee> coordonnees) {
		super();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.coordonnees = coordonnees;
	}

	@Override
	public int getHauteur() {
		return hauteur;
	}

	@Override
	public int getLargeur() {
		return largeur;
	}

	@Override
	public List<Coordonnee> getCoordonnees() {
		return coordonnees;
	}

}
