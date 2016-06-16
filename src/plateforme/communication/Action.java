package plateforme.communication;

public interface Action {
	ActionType getActionType();
	Object getData();
}
