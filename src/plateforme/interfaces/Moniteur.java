package plateforme.interfaces;

import plateforme.EtatPlugin;
import descripteur.Descripteur;

public interface Moniteur {

	void afficher(EtatPlugin e);
	void notifier(Descripteur d, EtatPlugin e);
	
}
