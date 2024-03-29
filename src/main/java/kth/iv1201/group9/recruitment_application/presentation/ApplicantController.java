/**
 * This class represents the controller for the recruiter view in the recruitment application.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kth.iv1201.group9.recruitment_application.application.RecruiterService;

@Controller
@Scope("session")
public class ApplicantController {
    @Autowired
    RecruiterService RecruiterService;

    /**
     * Retrieves the list of all applicants and displays the recruiter view.
     * 
     * @param model the model used to pass data to the view
     * @return the name of the view to be displayed
     */
    @GetMapping("/applicant")
    public String showRecruiterView(Model model) {
        return "applicantView";
    }

    // Logout post mapping handled by spring

}
