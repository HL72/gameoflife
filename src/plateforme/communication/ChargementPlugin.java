package plateforme.communication;

import descripteur.Descripteur;

public class ChargementPlugin implements Action {

	private ActionType type = ActionType.CHARGEMENT_PLUGIN;
	private Descripteur desc;
	
	public ChargementPlugin(Descripteur desc){
		this.desc = desc;
	}
	
	@Override
	public ActionType getActionType() {
		return type;
	}

	@Override			
	public Object getData() {
		return desc;
	}

}
