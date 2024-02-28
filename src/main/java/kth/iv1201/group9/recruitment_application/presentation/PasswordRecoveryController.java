/**
 * This class represents the controller for handling login-related requests and views.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kth.iv1201.group9.recruitment_application.application.PasswordRecoveryService;

/**
 * This class is a controller responsible for handling password recovery
 * requests.
 * It provides methods for displaying the password recovery view and handling
 * the email submission.
 */
@Controller
@Scope("session")
public class PasswordRecoveryController {
    @Autowired
    PasswordRecoveryService passwordRecoveryService;

    /**
     * Displays the password recovery view.
     * 
     * @return the name of the password recovery view
     */
    @GetMapping("/passwordRecovery")
    public String showPasswordRecoveryView(@ModelAttribute("email") String email) {
        return "passwordRecoveryView";
    }

    /**
     * Handles the email submission for password recovery.
     * 
     * @param email the email address of the user
     * @return the login view if the email is sent successfully, otherwise the name
     *         of the password recovery view
     */
    @PostMapping("/passwordRecovery")
    public String handleEmail(String email) {

        try {
            passwordRecoveryService.handlePasswordRecovery(email);
            return "redirect:/login?emailSent";
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return "passwordRecoveryView";
        }

    }
}
