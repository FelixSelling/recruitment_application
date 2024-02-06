/**
 * This class represents the controller for handling login-related requests and views.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kth.iv1201.group9.recruitment_application.domain.DTO.PersonDTO;
import kth.iv1201.group9.recruitment_application.application.LoginService;

@Controller
@Scope("session")
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * Displays the login view.
     * 
     * @return the name of the login view template
     */
    @GetMapping("/login")
    public String showLoginView() {
        return "loginView";
    }

    /**
     * Handles the login form submission.
     * 
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @param model    the model to be used for rendering the view
     * @return the name of the view to redirect to based on the user's role, or the
     *         login view with an error message
     */
    @PostMapping("/login")
    public String loginPost(@RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password,
            Model model) {
        PersonDTO person = loginService.findUser(username, password);
        if (person != null) {
            if (person.getRole().getName().equals("applicant")) {
                // TODO: Handle applicant login
                // return "redirect:/applicant";
            } else if (person.getRole().getName().equals("recruiter")) {
                // Redirect to recruiter view
                return "redirect:/recruiter";
            }
            // TODO: Remove test redirect once applicant login is created
            return "redirect:/test";
        }
        // Display login view with error message
        model.addAttribute("loginError", "Invalid username or password");
        return "loginView";
    }
}
