package plugins.moniteur;

import java.util.List;
import java.util.Map;

import descripteur.Descripteur;
import plateforme.Type;

public interface Moniteur {

	void afficher(Map<Type, List<Descripteur>> ls);
	
}
