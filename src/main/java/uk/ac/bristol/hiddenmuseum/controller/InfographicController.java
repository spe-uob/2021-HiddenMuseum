
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;


@Controller
public class InfographicController {
    @GetMapping("/infographics")
    public String Info(){
        return "infographics";
    }
}