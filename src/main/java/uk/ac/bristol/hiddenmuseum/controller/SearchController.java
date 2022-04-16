
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.SchemaRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            @RequestParam(defaultValue = "" ,required = false) List<String> values,
            @RequestParam(defaultValue = "open-data-gallery-3-european-old-masters", required = false) String dataset,
            Model model) {
        String base = "https://opendata.bristol.gov.uk/";
        String datasetLink = dataset.replace(base, "");
        datasetLink = datasetLink.replace("explore/dataset/", "");
        datasetLink = datasetLink.replace("information", "");
        datasetLink = datasetLink.replace("/", "");

        var srq = new SearchRequestBuilder(base, datasetLink);
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
        var scrq = new SchemaRequestBuilder(base, datasetLink);
        var schResponse = scrq.sendRequest();
        var fieldList = schResponse.fields.stream().map(f -> f.name).toArray();

        String titleField = "";
        String imageField = "";
        Boolean titleFound = false;
        Boolean imageFound = false;


        for(Object field : fieldList)  {
            System.out.println(field);
            String regex = "title.|.title.|title|Title.|.Title.|Title";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher((String) field);
            if(matcher.find()) {
                titleField = (String) field;
                titleFound = true;
            }
            regex = "image.|.image.|image|Image.|.Image.|Image";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher((String) field);
            if(matcher.find()) {
                imageField = (String) field;
                imageFound = true;
            }
            if(imageFound && titleFound)    {
                continue;
            }
        }

        model.addAttribute("imageField", imageField);

        model.addAttribute("titleField", titleField);

        model.addAttribute("fieldList", fieldList);

        //setting up to export as JSON
        String exportJSON = srq.getUrl();
        model.addAttribute("exportJSON", exportJSON);

        //setting up to export as CSV
        String exportCSV = "/export";
        model.addAttribute("exportCSV", exportCSV);

        int pages = (response.nhits / nhits) + (response.nhits % nhits == 0 ? 0 : 1);
        model.addAttribute("pages", pages);

        //format values so that we can get the back to search working properly
        String newValues = "";
        for (String value : values) {
            newValues += "&values=";
            newValues += value;
        }

        model.addAttribute("values", newValues);

        model.addAttribute("query", q);

        model.addAttribute("pageNumber", page);

        model.addAttribute("dataset", datasetLink);

        return "search";
    }

}
