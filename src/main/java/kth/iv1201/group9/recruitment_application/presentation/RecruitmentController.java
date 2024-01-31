package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kth.iv1201.group9.recruitment_application.application.TestService;

@Controller
@Scope("session")
public class RecruitmentController {
    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String showTestView(Model model) {
        model.addAttribute("name", "World");
        model.addAttribute("personList", testService.getAllPersonList());
        return "testView";
    }

    @PostMapping("/test")
    public String testPost(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("personList", testService.getAllPersonList());
        return "testView";
    }
}
