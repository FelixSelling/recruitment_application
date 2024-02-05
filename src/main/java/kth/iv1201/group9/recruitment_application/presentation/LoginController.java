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

    @GetMapping("/login")
    public String showLoginView(Model model) {
        model.addAttribute("username", "THISISUSERNAME");
        model.addAttribute("password", "THISISPASSWORD");
        return "loginView";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password,
            Model model) {
        PersonDTO person = loginService.findUser(username, password);
        if (person != null) {
            // if (person.getRole().equals("applicant")) {
            // // return "redirect:/applicant";
            // } else if (person.getRole().equals("recruiter")) {
            // // return "redirect:/recruiter";
            // }
            return "redirect:/test";
        }
        model.addAttribute("loginError", "Invalid username or password");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "loginView";
    }
}
