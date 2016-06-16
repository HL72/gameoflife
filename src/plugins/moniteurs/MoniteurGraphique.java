package plugins.moniteurs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import plateforme.EtatPlugin;
import plateforme.Plateforme;
import plateforme.Plugin;
import plateforme.communication.Action;
import plateforme.communication.ActionType;
import plateforme.communication.Observer;
import plateforme.interfaces.Moniteur;
import descripteur.Descripteur;

public class MoniteurGraphique extends Plugin implements Moniteur, Observer {

	private List<Descripteur> pluginsNonCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsCharges = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsEnCours = new ArrayList<Descripteur>();
	private List<Descripteur> pluginsArretes = new ArrayList<Descripteur>();

	private JFrame frame = new JFrame("Moniteur de plugin");
	private JPanel pane = new JPanel();

	public MoniteurGraphique(Plateforme p, Descripteur d) {
		super(p,d);
		p.register(this);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	private void createAndShowGUI() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		frame.setContentPane(pane);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void afficher() {

		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(Color.WHITE);
		frame.setContentPane(pane);

		if(!pluginsCharges.isEmpty()){
			addLabel("Plugins charges ", pane, true);                                                        
			for (Descripteur d : pluginsCharges) {
				addLabel(d.toString(), pane, false);
			}
		}
		if(!pluginsEnCours.isEmpty()){
			addLabel("Plugins en cours d'execution ", pane, true);                                 
			for (Descripteur d : pluginsEnCours) {
				addLabel(d.toString(), pane, false);
			}
		}
		if(!pluginsArretes.isEmpty()){
			addLabel("Plugins arretes ", pane, true);                                             
			for (Descripteur d : pluginsArretes) {
				addLabel(d.toString(), pane, false);
			}
		}
		refreshGUI();
	}

	private void refreshGUI() {	
		frame.pack();
		frame.revalidate(); 
		frame.repaint();
	}

	private void addLabel(String text, Container container,boolean isTitle) {
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.LEFT_ALIGNMENT);
		if(isTitle){
			JPanel p = new JPanel();
			p.setAlignmentX(Component.LEFT_ALIGNMENT);
			p.add(label, BorderLayout.NORTH);
			label.setForeground(Color.WHITE);
			p.setBackground(new Color(59, 89, 152));
			container.add(p);
		}
		else{
			label.setForeground(new Color(135, 135, 150));
			container.add(label);
		}
	}

	@Override
	public void notify(Action a) {
		ActionType t = a.getActionType();

		if(ActionType.CHARGEMENT_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsCharges.add(d);
		}
		else if(ActionType.EXECUTION_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsEnCours.add(d);
		}
		else if(ActionType.ARRET_PLUGIN.equals(t)){
			Descripteur d = (Descripteur) a.getData();
			pluginsEnCours.remove(d);
			pluginsArretes.add(d);
		}
		else if(ActionType.AFFICHER_MONITEUR.equals(t)){
			afficher();
		}
	}
}
