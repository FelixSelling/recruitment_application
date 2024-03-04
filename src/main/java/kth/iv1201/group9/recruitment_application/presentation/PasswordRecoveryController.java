/**
 * This class represents the controller for handling login-related requests and views.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kth.iv1201.group9.recruitment_application.application.PasswordRecoveryService;
import kth.iv1201.group9.recruitment_application.application.ValidationService;
import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.exception.RegistrationException;
import kth.iv1201.group9.recruitment_application.exception.ValidationException;

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

    @Autowired
    ValidationService validationService;

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
            passwordRecoveryService.requestPasswordRecovery(email);
            return "redirect:/login?recoveryEmailSent";
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return "passwordRecoveryView";
        }

    }

    @GetMapping("/changePassword")
    public String showChangePasswordView(@ModelAttribute("person") PersonDTO personDTO,
            @RequestParam String token) {

        if (token == null || passwordRecoveryService.verifyToken(token) == false) {
            return "redirect:/login";
        }
        return "changePasswordView";

    }

    @PostMapping("/changePassword")
    public String handlePassword(PersonDTO personDTO, String token, Model model) {

        try {
            passwordRecoveryService.changePassword(token, personDTO);
            return "redirect:/login?passwordChanged";
        } catch (ValidationException ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", ex.getMessage());
            return "changePasswordView";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "changePasswordView";
        }

    }
}
