package kth.iv1201.group9.recruitment_application.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.http.HttpStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * This class is a controller advice that handles global exceptions thrown by the application.
 * It provides exception handling methods for specific exception types, such as RegistrationException,
 * ValidationException, and NoHandlerFoundException. It also provides a generic exception handling method
 * for any other unhandled exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler method for RegistrationException.
     * Handles the exception and returns an error page for bad request (400) status code.
     * @param ex The RegistrationException that was thrown.
     * @param request The HttpServletRequest object.
     * @return The view name of the error page for bad request.
     */
    @ExceptionHandler(RegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRegistrationException(RegistrationException ex, HttpServletRequest request) {
        // registration error handling
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST);
        return "error/error-400";
    }

    /**
     * Exception handler method for ValidationException.
     * Handles the exception and returns an error page for bad request (400) status code.
     * @param ex The ValidationException that was thrown.
     * @param request The HttpServletRequest object.
     * @return The view name of the error page for bad request.
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(ValidationException ex, HttpServletRequest request) {
        // validation error handling
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST);
        return "error/error-400";
    }

    /**
     * Exception handler method for NoHandlerFoundException.
     * Handles the exception and returns an error page for not found (404) status code.
     * @param ex The NoHandlerFoundException that was thrown.
     * @param request The HttpServletRequest object.
     * @return The view name of the error page for not found.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        // 404 error handling
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.toString());
        return "error/error-404";
    }

    /**
     * Generic exception handler method for any other unhandled exceptions.
     * Handles the exception and returns an error page for internal server error (500) status code.
     * @param ex The Exception that was thrown.
     * @param request The HttpServletRequest object.
     * @return The view name of the error page for internal server error.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, HttpServletRequest request) {
        // generic error handling
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return "error/error-500";
    }
}
