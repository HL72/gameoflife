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
	List<Descripteur> producteurs = new ArrayList<Descripteur>();
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
		container.setPreferredSize(new Dimension(300, 300));
		// Enregistrement de l'Observer sur la plateforme
		p.register(this);
		// Initialisation du producteur par défaut
		producteurCourant = new ProducteurImplDefaut(p, d);
		modele = producteurCourant.getMatrice();
		executer();
	}

	@Override
	public void executer() throws Exception {
		int[][] current = modele.getMatrice();
		for (int k = 0; k < 100; k++) {
			afficher();
			Thread.sleep(500);
			current = evoluer(current);
			modele.setMatrice(current);
			}
	}

	private int[][] evoluer(int[][] m) {
		int[][] next = new int[m.length][m[0].length];

		for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m.length; j++) {
                //gestion des borts de la grille
                if(i == 0 || j == 0 || i == m.length-1 || j == m.length-1) {
                    //si on est dans le coint supérieur gauche
                    if(i == 0 && j == 0) {  
                        if(m[i][j] == 0) { //si une cellule est morte
                            if(((m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) == 3){
                                next[i][j] = 1;
                            }
                        }
                        else{ //si une cellules est donc vivante
                            if((((m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) != 3) && ((((m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }
                    }
                    //si on est dans la ligne du haut
                    if(j == 0 && i > 0 && i < m.length-1 ) {
                        if(m[i][j] == 0) {
                            if(((m[i-1][j]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1]) + (m[i-1][j+1])) == 3) {
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i-1][j]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1]) + (m[i-1][j])) != 3) && ((((m[i-1][j]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1]) + (m[i-1][j])) != 2))){
                                next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }  
                    }
                    //si on est dans le coin supérieur droit
                    if(j == 0 && i == m.length-1) {
                        if(m[i][j] == 0){
                            if(((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1]) + (m[i][j])) == 3) {
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1])) != 3) && ((((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1])) != 2))) {
                            	next[i][j] = 0; 
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }  
                    }      
                    //si on est dans la ligne de droite
                    if(i == m.length-1 && j > 0 && j < m.length-1){
                        if(m[i][j] == 0){
                            if(((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1]) + (m[i-1][j-1]) + (m[i][j-1])) == 3){
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1]) + (m[i-1][j-1]) + (m[i][j-1])) != 3) && ((((m[i-1][j]) + (m[i][j+1]) + (m[i-1][j+1]) + (m[i-1][j-1]) + (m[i][j-1])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }  
                    }              
                    //si on est dans le coint inférieur droit
                    if(i == m.length-1 && j == m.length-1){
                        if(m[i][j] == 0){
                            if(((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1])) == 3){
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1])) != 3) && ((((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }                      
                    }
                    //si on est dans la ligne du bat
                    if(j == m.length-1 && i > 0 && i < m.length-1){
                        if(m[i][j] == 0){
                            if(((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) == 3){
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) != 3) && ((((m[i-1][j]) + (m[i][j-1]) + (m[i-1][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }                  
                    }              
                    //si on est dans le coint inférieur gauche
                    if(i == 0 && j == m.length-1){
                        if(m[i][j] == 0){
                            if(((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) == 3){
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) != 3) && ((((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }                      
                    }
                    //enfin, si on est dans la ligne de gauche
                    if(i == 0 && j > 0 && j < m.length-1){
                        if(m[i][j] == 0){
                            if(((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) == 3){
                            	next[i][j] = 1;
                            }
                        }
                        else{
                            if((((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) != 3) && ((((m[i][j-1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1]) + (m[i][j+1])) != 2))){
                            	next[i][j] = 0;
                            }
                            else {
                            	next[i][j] = 1;
                            }
                        }                  
                    }                  
                }
                else {  //régles du jeu en pratique hort borts de la grille
                    if(m[i][j] == 0) {        
                        if(((m[i-1][j-1]) + (m[i-1][j]) + (m[i-1][j+1]) + (m[i][j-1]) + (m[i][j]) + (m[i][j+1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1])) == 3){
                        	next[i][j] = 1;
                        }
                    }
                    else{              
                        if((((m[i-1][j-1]) + (m[i-1][j]) + (m[i-1][j+1]) + (m[i][j-1]) + (m[i][j+1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1])) != 3)  && (((m[i-1][j-1]) + (m[i-1][j]) + (m[i-1][j+1]) + (m[i][j-1]) + (m[i][j+1]) + (m[i+1][j-1]) + (m[i+1][j]) + (m[i+1][j+1])) != 2)) {
                        	next[i][j] = 0;
                        }
                        else {
                        	next[i][j] = 1;
                        }
                    }
                }
            }
        }
		return next;
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

	private void afficher() throws Exception {
		container.removeAll();
		int width = 30;
		int height = 30;
		int[][] m = modele.getMatrice();
		for(int i = 0 ; i< m.length ; i ++) {
			for(int j = 0 ; j < m[0].length ; j++){
				JLabel cell = new JLabel();
				cell.setOpaque(true);
				cell.setBounds(i*width, j*height, width, height);
				cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if(m[i][j] == 0){
					cell.setBackground(Color.WHITE);
				}
				if(m[i][j] == 1){
					cell.setBackground(Color.BLACK);
				}
				container.add(cell);
			}
		}
		container.repaint();
		frame.add(container);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}

	@Override
	public void notify(Action a) {
		ActionType t = a.getActionType();
		if (ActionType.CHARGEMENT_PLUGIN.equals(t)) {
			Descripteur d = (Descripteur) a.getData();
			if (d.getInterfaceNom().equals(Producteur.class.getName())) {
				producteurs.add(d);
			}
		}
	}

}
