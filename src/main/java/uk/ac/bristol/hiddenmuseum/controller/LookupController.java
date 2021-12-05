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

        System.out.println(response.toString());
        System.out.println(response.fields.toJSONString());

        var image = (LinkedHashMap) response.fields.get("image_of_object");

        // getting each of the values from field
        String title = (String) response.fields.get("title_of_object");
        String artistName = (String) response.fields.get("artist"); // some had to be renamed so they dont conflict with the ones
        // from earlier
        String type = (String) response.fields.get("object_type");
        String mediums = (String) response.fields.get("medium");
        String body = (String) response.fields.get("label");
        String source = (String) response.fields.get("source");
        String year_of_creation = (String) response.fields.get("year_of_creation");
        String current_display_status = (String) response.fields.get("current_display_status");
        String imageID = (String) image.get("id");
        String imgSource = "https://opendata.bristol.gov.uk/explore/dataset/open-data-gallery-3-european-old-masters/files/"
                + imageID + "/300";

        model.addAttribute("imgSource", imgSource);
        model.addAttribute("title", title);
        model.addAttribute("artistName", artistName);
        model.addAttribute("type", type);
        model.addAttribute("mediums", mediums);
        model.addAttribute("body", body);
        model.addAttribute("source", source);
        model.addAttribute("year_of_creation", year_of_creation);
        model.addAttribute("current_display_status", current_display_status);

        return "search";
    }

}
