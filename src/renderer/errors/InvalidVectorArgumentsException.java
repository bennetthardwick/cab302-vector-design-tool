package renderer.errors;

public class InvalidVectorArgumentsException extends Exception {
    public InvalidVectorArgumentsException(String arguments) {
        super("Invalid vector arguments: " + arguments);
    }

    public InvalidVectorArgumentsException() {
        super("Invalid vector arguments!");
    }
}
