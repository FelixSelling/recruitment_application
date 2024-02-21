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

import kth.iv1201.group9.recruitment_application.application.RegistrationService;
import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;

@Controller
@Scope("session")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    /**
     * Displays the login view.
     * 
     * @return the name of the login view template
     */
    @GetMapping("/registration")
    public String showLoginView(@ModelAttribute("person") PersonDTO user) {
        return "registrationView";
    }

    @PostMapping("/registration")
    public String handleSignup(PersonDTO user) {
        try {
            registrationService.handleRegisteredUser(user);
            return "redirect:/login?registered";
        } catch (Exception e) {
            e.printStackTrace();
            return "registrationView";
        }
    }
}
