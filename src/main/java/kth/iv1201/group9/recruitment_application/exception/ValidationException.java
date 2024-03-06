package kth.iv1201.group9.recruitment_application.exception;

/**
 * This exception is thrown when a validation error occurs.
 */
public class ValidationException extends Exception {
    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message the detail message
     */
    public ValidationException(String message) {
        super(message);
    }
}
