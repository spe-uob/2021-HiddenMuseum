
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.SchemaRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.List;

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
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam (defaultValue = "" ,required = false) List<String> values,
            Model model) {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        srq.setQuery(q);
        srq.setLimit(nhits);
        srq.setOffset(nhits * page);

        //iterate through values string here and set the srq.
        int len = values.size();
        for(int i = 0; ((3*i)) < len; i++) {
            if (values.get((3 * i) + 1).equals("refineBy")) {
                if(!values.get(3 * i).equals("") && !values.get((3 * i) + 2).equals(""))    {
                    srq.refineBy(values.get(3 * i), values.get((3 * i) + 2));
                }
            } else {
                if(!values.get(3 * i).equals("") && !values.get((3 * i) + 2).equals(""))    {
                    srq.exclude(values.get(3 * i), values.get((3 * i) + 2));
                }
            }
        }

        var response = srq.sendRequest();
        model.addAttribute("response", response);
        var scrq = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        var schResponse = scrq.sendRequest();
        var fieldList = schResponse.fields.stream().map(f -> f.name).toArray();

        model.addAttribute("fieldList", fieldList);

        //setting up to export as JSON
        String exportJSON = srq.getUrl();
        model.addAttribute("exportJSON", exportJSON);

        //setting up to export as CSV
        String exportCSV = "/export?q=" + q;
        model.addAttribute("exportCSV", exportCSV);

        int pages = (response.nhits / nhits) + (response.nhits % nhits == 0 ? 0 : 1);
        model.addAttribute("pages", pages);

        model.addAttribute("query", q);

        model.addAttribute("pageNumber", page);

        return "search";
    }

}
