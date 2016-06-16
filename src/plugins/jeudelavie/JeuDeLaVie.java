package plugins.jeudelavie;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import descripteur.Descripteur;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.communication.Action;
import plateforme.communication.ActionType;
import plateforme.communication.Observer;
import plateforme.interfaces.Application;
import plateforme.interfaces.Producteur;

public class JeuDeLaVie extends Plugin implements Application, Observer {

	MatriceModele modele;
	List<Descripteur> producteurs;
	Producteur producteurCourant;
	JFrame frame;
	JPanel container;

	public JeuDeLaVie(Plateforme p, Descripteur d) throws Exception {
		super(p, d);
		// Création de l'IHM
		frame = new JFrame("Jeu de la Vie");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = new JPanel();
		container.setBackground(Color.white);
		container.setLayout(null);
		container.setPreferredSize(new Dimension(400, 400));
		// Enregistrement de l'Observer sur la plateforme
		p.register(this);
		// Initialisation du producteur par défaut
		producteurCourant = new ProducteurImplDefaut(p, d);
		modele = producteurCourant.getMatrice();
		executer();
	}

	@Override
	public void executer() throws Exception {

		// Une cellule morte poss�dant exactement trois voisines vivantes
		// devient vivante (elle na�t).
		// Une cellule vivante poss�dant deux ou trois voisines vivantes le
		// reste, sinon elle meurt
		int[][] m = modele.getMatrice();
		for (int x = 0; x < m.length; x++) {
			for (int y = 0; y < m.length; y++) {
				int nbVoisins = compterVoisins(x, y, m);
				if (nbVoisins == 3) {
					m[x][y] = 1;
				} else if (nbVoisins != 2 && nbVoisins != 3) {
					m[x][y] = 0;
				}
			}
		}

		afficher();
	}

	/*
	 * Récupération des producteurs disponibles et initialisation du producteur
	 * courant avec le producteur par défaut
	 */
	private void initProducteurs() throws Exception {
		producteurs = new ArrayList<Descripteur>();
		List<Descripteur> producteursDesc = Plateforme.getPlugins(Producteur.class.getName());
		for (Descripteur producteurDesc : producteursDesc) {
			producteurs.add(producteurDesc);
			if (producteurDesc.getClasseNom().equals(ProducteurImplDefaut.class.getName())) {
				producteurCourant = (Producteur) Plateforme.getPlugin(producteurDesc);
			}
		}
	}

	private int compterVoisins(int x, int y, int[][] m) {

		int nbVoisins = 0;
		// milieu gauche
		if (x - 1 >= 0) {
			if (m[x - 1][y] != 0) {
				nbVoisins++;
			}
			// bas gauche
			if (y + 1 < m[0].length) {
				if (m[x - 1][y + 1] != 0) {
					nbVoisins++;
				}
			}
		}

		// milieu droit
		if (x + 1 < m.length) {
			if (m[x + 1][y] != 0) {
				nbVoisins++;
			}
			// haut droit
			if (y - 1 > 0) {
				if (m[x + 1][y - 1] != 0) {
					nbVoisins++;
				}
			}
		}

		// milieu haut
		if (y - 1 > 0) {
			if (m[x][y - 1] != 0) {
				nbVoisins++;
			}
			// haut gauche
			if (x - 1 > 0) {
				if (m[x - 1][y - 1] != 0) {
					nbVoisins++;
				}
			}
		}

		// milieu bas
		if (y + 1 < m[0].length) {
			if (m[x][y + 1] != 0) {
				nbVoisins++;
			}
			// bas droite
			if (x + 1 < m.length) {
				if (m[x + 1][y + 1] != 0) {
					nbVoisins++;
				}
			}
		}

		return nbVoisins;
	}

	private void afficher() throws Exception {
		int width = 10;
		int height = 10;
		int[][] m = modele.getMatrice();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				JLabel cell = new JLabel();
				cell.setOpaque(true);
				cell.setBounds(i * width, j * height, width, height);
				cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if (m[i][j] == 0) {
					cell.setBackground(Color.WHITE);
				}
				if (m[i][j] == 1) {
					cell.setBackground(Color.BLACK);
				}
				container.add(cell);
			}
		}
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void notify(Action a) {
		ActionType t = a.getActionType();

		if (ActionType.CHARGEMENT_PLUGIN.equals(t)) {
			Descripteur d = (Descripteur) a.getData();
			producteurs.add(d);
		}
	}

}
