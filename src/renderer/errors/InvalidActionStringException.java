package renderer.errors;

public class InvalidActionStringException extends Exception {
    public InvalidActionStringException(String actionString) {
        super("Invalid action string: " + actionString);
    }
}
