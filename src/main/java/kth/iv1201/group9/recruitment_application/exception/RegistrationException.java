package kth.iv1201.group9.recruitment_application.exception;

/**
 * This exception is thrown when there is an error during the registration process.
 */
public class RegistrationException extends Exception {
    /**
     * Constructs a new RegistrationException with the specified detail message.
     *
     * @param message the detail message
     */
    public RegistrationException(String message) {
        super(message);
    }
}
