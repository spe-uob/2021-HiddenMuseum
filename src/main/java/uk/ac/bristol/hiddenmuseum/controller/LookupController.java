package uk.ac.bristol.hiddenmuseum.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.RequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;
import uk.ac.bristol.hiddenmuseum.service.FieldImpl;

import java.util.LinkedHashMap;

@Controller
public class LookupController {

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
