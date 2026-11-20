package exceptions;

/**
 * Thrown when no delivery agent is available.
 */
public class NoAgentAvailableException extends Exception {

    public NoAgentAvailableException(String message) {
        super(message);
    }
}