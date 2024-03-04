package kth.iv1201.group9.recruitment_application.exception;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(RegistrationException ex) {
        // registration error handling
        return new ResponseEntity<>(new HashMap<String, String>() {
            {
                put("error", ex.getMessage());
                put("status", HttpStatus.BAD_REQUEST.toString());
                put("date", java.time.LocalDateTime.now().toString());
            }
        }, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        // validation error handling
        return new ResponseEntity<>(new HashMap<String, String>() {
            {
                put("error", ex.getMessage());
                put("status", HttpStatus.BAD_REQUEST.toString());
                put("date", java.time.LocalDateTime.now().toString());
            }
        }, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // generic error handling
        return new ResponseEntity<>(new HashMap<String, String>() {
            {
                put("error", ex.getMessage());
                put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
                put("date", java.time.LocalDateTime.now().toString());
            }
        }, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
