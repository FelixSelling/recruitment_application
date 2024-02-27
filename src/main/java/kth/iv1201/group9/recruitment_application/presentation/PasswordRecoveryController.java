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

@Controller
@Scope("session")
public class PasswordRecoveryController {
    @Autowired
    PasswordRecoveryService passwordRecoveryService;

    /**
     * Displays the password recovery view.
     * 
     * @return the name of the password recovery view template
     */
    @GetMapping("/passwordRecovery")
    public String showPasswordRecoveryView(@ModelAttribute("email") String email) {
        return "passwordRecoveryView";
    }

    @PostMapping("/passwordRecovery")
    public String handleEmail(String email) {

        try {
            passwordRecoveryService.handleEmail(email);
            return "redirect:/login?emailSent";
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            return "passwordRecoveryView";
        }

    }
}
