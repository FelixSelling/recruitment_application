/**
 * This class represents the controller for the recruiter view in the recruitment application.
 */
package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kth.iv1201.group9.recruitment_application.application.RecruiterService;

@Controller
@Scope("session")
public class RecruiterController {
    @Autowired
    RecruiterService RecruiterService;

    /**
     * Retrieves the list of all applicants and displays the recruiter view.
     * 
     * @param model the model used to pass data to the view
     * @return the name of the view to be displayed
     */
    @GetMapping("/recruiter")
    public String showRecruiterView(Model model) {
        model.addAttribute("personList", RecruiterService.getAllApplicantsList());
        return "recruiterView";
    }

    /**
     * Handles the post request from the recruiter view.
     * 
     * @return the name of the view to be displayed
     */
    @PostMapping("/recruiter")
    public String recruiterPost() {
        return "recruiterView";
    }
}
