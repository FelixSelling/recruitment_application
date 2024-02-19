package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/**
 * This class is a controller that handles custom error pages for different HTTP status codes.
 */
@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Handles the error page based on the HTTP status code.
     * 
     * @param request the HTTP servlet request
     * @return the view name of the error page
     */
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/error-400";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/error-401";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/error-403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error-500";
            } else {
                return "error/error";
            }
        }
        return "error/error";
    }
}
