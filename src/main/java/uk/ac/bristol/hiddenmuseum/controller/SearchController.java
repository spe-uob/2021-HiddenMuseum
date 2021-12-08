
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;
import uk.ac.bristol.hiddenmuseum.service.DemoService;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
import uk.ac.bristol.hiddenmuseum.service.FieldImpl;
import java.util.HashMap;
import java.util.*;

/**
 * Controller for searching the database
 */
@Controller
public class SearchController {

    @Autowired
    private DemoService demoService;

    /**
     * Request handler for search requests
     *
     * @param dataset
     * @param params
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String search(
            @RequestParam Map<String, String> params,
            Model model) {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        srq.setQuery(params.getOrDefault("q", ""));
        try{
            Integer nhitsToDisplay = Integer.parseInt(params.getOrDefault("nhits", "10"));
            if (nhitsToDisplay > 100){
                nhitsToDisplay = 100;
            }
            srq.setLimit(nhitsToDisplay);
        }finally{}
        var response = srq.sendRequest();
        model.addAttribute("prevSearch", params.getOrDefault("q", ""));
        model.addAttribute("response", response);
        return "search";
    }

}
