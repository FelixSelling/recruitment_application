/**
 * This class represents the controller for handling login-related requests and views.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    // Post mapping handled by spring security
}
