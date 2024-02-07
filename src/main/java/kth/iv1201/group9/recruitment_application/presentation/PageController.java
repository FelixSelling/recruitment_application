package kth.iv1201.group9.recruitment_application.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}
