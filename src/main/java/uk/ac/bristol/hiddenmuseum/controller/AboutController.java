package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the about page of the program
 */
@Controller
public class AboutController {

    /**
     * Request handler for the about page of the web app
     *
     * @return
     */
    @GetMapping("/about")
    public String about()  {
       return "about";
    }

}