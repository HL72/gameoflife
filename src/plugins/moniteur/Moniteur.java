package plugins.moniteur;

import java.util.List;
import java.util.Map;

import descripteur.Descripteur;

public interface Moniteur {

	void afficher(Map<String, List<Descripteur>> ls);
	
}
