package exceptions;

/**
 * Thrown when an operation is attempted on an invalid
 * or nonexistent parcel.
 */
public class InvalidParcelException extends Exception {

    public InvalidParcelException(String message) {
        super(message);
    }
}