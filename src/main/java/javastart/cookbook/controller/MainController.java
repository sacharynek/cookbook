package javastart.cookbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController extends AbstractController {


    @GetMapping("/")
    public String getMainPage(HttpServletRequest request, Model model) {

        List<String> breadcrumbs = produceBreadcrumbs(request);

        model.addAttribute("breadcrumbs", breadcrumbs);


        return "index";
    }
}
