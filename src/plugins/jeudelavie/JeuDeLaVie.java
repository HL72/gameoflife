package plugins.jeudelavie;

import java.util.List;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.interfaces.Application;
import plateforme.interfaces.Producteur;
import plugins.producteurs.ProducteurImpl;

public class JeuDeLaVie extends Plugin implements Application {
	
	MatriceModele modele;
	
	public JeuDeLaVie(Plateforme p, Descripteur d) throws Exception {
		super(p,d);
	}

	@Override
	public void executer() throws Exception {
		initialiserMatriceModele();
		
		//Une cellule morte poss�dant exactement trois voisines vivantes devient vivante (elle na�t).
		//Une cellule vivante poss�dant deux ou trois voisines vivantes le reste, sinon elle meurt
		int[][] m = modele.getMatrice();
		for (int x = 0; x < m.length; x++) {
			for (int y = 0; y < m.length; y++) {
				int nbVoisins = compterVoisins(x, y, m);
				if(nbVoisins == 3){
					m[x][y] = 1;
				}
				else if(nbVoisins != 2 && nbVoisins != 3){
					m[x][y] = 0;
				}
			}
		}
	}

	private int compterVoisins(int x, int y, int[][] m) {
		
		int nbVoisins = 0;
		//milieu gauche
		if(x-1 >= 0){
			if(m[x-1][y] != 0){
				nbVoisins++;
			}
			//bas gauche
			if(y+1 < m[0].length){
				if(m[x-1][y+1] != 0){
					nbVoisins++;
				}
			}
		}
		
		//milieu droit
		if(x+1 <= m.length){
			if(m[x+1][y] != 0){
				nbVoisins++;
			}
			//haut droit
			if(y-1 > 0){
				if(m[x+1][y-1] != 0){
					nbVoisins++;
				}
			}
		}
		
		//milieu haut
		if(y-1 > 0){
			if(m[x][y-1] != 0){
				nbVoisins++;
			}
			//haut gauche
			if(x-1 > 0){
				if(m[x+1][y-1] != 0){
					nbVoisins++;
				}
			}
		}
		
		//milieu bas
		if(y+1 <= m[0].length){
			if(m[x][y+1] != 0){
				nbVoisins++;
			}
			//bas droite
			if(x+1 < m.length){
				if(m[x+1][y+1] != 0){
					nbVoisins++;
				}
			}
		}
		
		return nbVoisins;
	}

	private void initialiserMatriceModele() throws Exception {
		// TODO A am�liorer
		List<Descripteur> producteursDesc = Plateforme.getPlugins(Producteur.class.getName());
		Descripteur producteurDesc = producteursDesc.get(0);
		ProducteurImpl producteur = (ProducteurImpl) Plateforme.getPlugin(producteurDesc);
		modele = producteur.getMatrice();
	}

}
