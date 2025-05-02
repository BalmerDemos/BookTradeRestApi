package balmerdemos.booktrade.exception;

/**
 * Custom exception thrown when a requested resource (e.g., user, book, etc.)
 * cannot be found in the system.
 *
 * This helps in clearly distinguishing "not found" cases from other types of errors.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom message to describe the error.
     *
     * @param message A description of the missing resource (e.g., "User not found with ID 123").
     */
    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the RuntimeException superclass
    }
}
