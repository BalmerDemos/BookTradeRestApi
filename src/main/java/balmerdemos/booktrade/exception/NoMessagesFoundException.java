package balmerdemos.booktrade.exception;

/**
 * Custom exception used to indicate that no messages were found.
 *
 * Creating your own exceptions improves clarity in error handling and makes
 * it easier to define specific scenarios for different types of errors.
 */
public class NoMessagesFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message The error message to be displayed when the exception is thrown.
     */
    public NoMessagesFoundException(String message) {
        super(message); // Pass the message to the superclass (RuntimeException)
    }

    /**
     * Constructor that accepts both a custom message and a cause.
     *
     * @param message The error message.
     * @param cause   The underlying reason or exception that caused this error.
     */
    public NoMessagesFoundException(String message, Throwable cause) {
        super(message, cause); // Pass message and cause to the superclass
    }
}
