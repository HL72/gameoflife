package plateforme.communication;

import descripteur.Descripteur;

public class ExecutionPlugin implements Action {

	private ActionType type = ActionType.EXECUTION_PLUGIN;
	private Descripteur desc;
	
	public ExecutionPlugin(Descripteur desc){
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
