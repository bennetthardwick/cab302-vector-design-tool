package renderer.errors;

public class InvalidActionTypeStringException extends Exception {
    public InvalidActionTypeStringException(String actionTypeString) {
        super("Invalid action type string: " + actionTypeString);
    }
}
