package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;

/**
 * Controller for looking up individual items in the database
 */
@Controller
public class LookupController {

    /**
     * Request handler for item lookups
     *
     * @param dataset id of the dataset being searched
     * @param recordID id of the specific record being looked up
     * @param model spring model
     * @return view to use
     */
    @GetMapping("/item/{dataset}/{id}")
    public String itemLookup(
            @PathVariable("dataset") String dataset,
            @PathVariable("id") String recordID,
            Model model) {
        var lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/", dataset, recordID);
        var response = lrq.sendRequest();
        model.addAttribute("response", response);
        return "item";
    }

}
