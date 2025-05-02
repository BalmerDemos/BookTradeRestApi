package balmerdemos.booktrade.exception;

/**
 * Custom exception thrown when a specific user is not found in the system.
 *
 * Using custom exceptions like this helps to make your code more descriptive
 * and easier to debug or handle globally.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message Description of the error (e.g., "User with ID 123 not found").
     */
    public UserNotFoundException(String message) {
        super(message); // Pass the message to the parent RuntimeException class
    }

    /**
     * Constructor that accepts a custom message and a root cause.
     *
     * @param message Description of the error.
     * @param cause   The underlying exception that caused this error (optional).
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause); // Pass both to the parent class
    }
}
