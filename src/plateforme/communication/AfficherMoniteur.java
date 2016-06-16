package plateforme.communication;

public class AfficherMoniteur implements Action{

	private ActionType type = ActionType.AFFICHER_MONITEUR;
	
	@Override
	public ActionType getActionType() {
		return type;
	}

	@Override
	public Object getData() {
		return new Object();
	}

}
