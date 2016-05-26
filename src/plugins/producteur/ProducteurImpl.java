package plugins.producteur;

import application.modele.Modele;
import application.modele.ModeleImpl;

public class ProducteurImpl implements Producteur {

	private Modele modele = new ModeleImpl();
	
	
	
	public ProducteurImpl() {
		super();
		
	}



	@Override
	public Modele getModele() {
		return modele;
	}

}
