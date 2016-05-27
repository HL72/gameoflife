package plateforme;

import descripteur.Descripteur;

public abstract class Plugin {

	protected Plateforme plateforme;
	protected Descripteur descripteur;
	
	public Plugin(Plateforme plateforme, Descripteur descripteur) {
		super();
		this.plateforme = plateforme;
		this.descripteur = descripteur;
	}
	
	public Plateforme getPlateforme() {
		return plateforme;
	}
	public void setPlateforme(Plateforme plateforme) {
		this.plateforme = plateforme;
	}
	public Descripteur getDescripteur() {
		return descripteur;
	}
	public void setDescripteur(Descripteur descripteur) {
		this.descripteur = descripteur;
	}

}
