/**
 * This class represents the controller for the recruiter view in the recruitment application.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kth.iv1201.group9.recruitment_application.application.RecruiterService;

@Controller
@Scope("session")
public class RecruiterController {
    @Autowired
    RecruiterService recruiterService;

    /**
     * Retrieves the list of all applicants and displays the recruiter view.
     * 
     * @param model the model used to pass data to the view
     * @return the name of the view to be displayed
     */
    @GetMapping("/recruiter")
    public String showRecruiterView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", authentication.getName());
        model.addAttribute("applications", recruiterService.getApplicationsList());
        return "recruiterView";
    }

    // Logout post mapping handled by spring

}
