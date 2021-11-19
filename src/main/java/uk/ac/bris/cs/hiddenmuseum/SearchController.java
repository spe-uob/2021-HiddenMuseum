package uk.ac.bris.cs.hiddenmuseum;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String searchTest(@RequestParam(defaultValue="test", required=false) String query, Model  model)  {
        model.addAttribute("query", query);
        return "search";
    }
	
}
