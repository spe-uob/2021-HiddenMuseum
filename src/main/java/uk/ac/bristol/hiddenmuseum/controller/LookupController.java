package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;

import java.util.List;

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
            @RequestParam(defaultValue = "" ,required = false) String q,
            @RequestParam(defaultValue = "" ,required = false) List<String> values,
            Model model) {
        var lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/", dataset, recordID);
        var response = lrq.sendRequest();
        model.addAttribute("response", response);

        //add the JSON url
        String url = lrq.getUrl();
        model.addAttribute("exportJSON", url);

        //add url params
        model.addAttribute("q", q);
        String newValues = "";
        for (String value : values) {
            newValues += "&values=";
            newValues += value;
        }

        model.addAttribute("values", newValues);

        //add the CSV url
        String exportCSV = "/exportItem?q=" + response.recordid;
        model.addAttribute("exportCSV", exportCSV);

        return "item";
    }

}
