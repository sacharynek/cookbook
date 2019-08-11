package javastart.cookbook.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public abstract class AbstractController {

    public List<String> produceBreadcrumbs(HttpServletRequest request) {
        List<String> output = new ArrayList<String>();

        String url = request.getRequestURL().toString().replaceAll("http://localhost:", "");

        var index = url.indexOf("/");
        var cleanedUrl = url.substring(index);
        String[] urlElements = cleanedUrl.split("/");

        output = Arrays.asList(urlElements).stream().map(a -> a + "/").collect(Collectors.toList());

        for (int i = 1; i < output.size(); i++) {
            output.set(i, output.get(i - 1) + output.get(i));
        }

        return output;
    }

}
