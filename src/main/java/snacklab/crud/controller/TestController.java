package snacklab.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("name", "SnackLab");
        return "index";
    }
}