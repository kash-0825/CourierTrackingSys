package exceptions;

/**
 * Thrown when a parcel is moved to an invalid status.
 */
public class InvalidStatusTransitionException extends Exception {

    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}