package application;

import plateforme.Plateforme;

public class Application {

	private Plateforme p = Plateforme.getInstance();
	
	public Application() throws Exception {
	
	}
	
	public static void main(String[] args) throws Exception {

		new Application();

	}
}
