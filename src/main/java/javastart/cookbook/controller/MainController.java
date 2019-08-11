package javastart.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends AbstractController {


    @GetMapping("/")
    public String getMainPage(Model model) {

        return "index";
    }
}
