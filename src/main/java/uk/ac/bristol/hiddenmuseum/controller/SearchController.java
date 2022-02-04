
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONValue;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for searching the database
 */
@Controller
public class SearchController {

    /**
     * Request handler for search requests
     *
     * @param q string to search for
     * @param nhits number of records to show
     * @param model Spring MVC model
     * @return view to use
     */
    @GetMapping("/search")
    public String search(
            @RequestParam(defaultValue = "", required = false) String q,
            @RequestParam(defaultValue = "25", required = false) int nhits,
            Model model) {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        srq.setQuery(q);
        srq.setLimit(nhits);
        var response = srq.sendRequest();
        model.addAttribute("response", response);
        String url = srq.getUrl();
        model.addAttribute("exportJSON", url );
        return "search";
    }

}
