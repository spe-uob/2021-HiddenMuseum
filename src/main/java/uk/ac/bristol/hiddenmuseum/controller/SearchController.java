
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * Controller for search requests
 */
@Controller
public class SearchController {

    @Autowired
    private DemoService demoService;

    /**
     * Request handler for searches
     *
     * @param medium
     * @param objectType
     * @param artist
     * @param field
     * @param Input
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String test(
            @RequestParam(defaultValue = "Oil on canvas", required = false) String medium,
            @RequestParam(defaultValue = "Painting", required = false) String objectType,
            @RequestParam(defaultValue = "Lucien PISSARRO", required = false) String artist,
            @RequestParam(defaultValue = "medium", required = false) String field,
            @RequestParam(defaultValue = "", required = false) String Input,
            Model model) {
        //I don't know how to do this without hard coding
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("medium",medium);
        fieldMap.put("object_Type",objectType);
        fieldMap.put("artist",artist);
        for (Map.Entry<String,String> i : fieldMap.entrySet()){
            if(  i.getKey().equals(field) && Input != "" && Input != null){
                i.setValue(Input);
            }
        }

        medium = fieldMap.get("medium");
        objectType = fieldMap.get("object_Type");
        artist = fieldMap.get("artist");

        String results = demoService.getDemoInfo(medium, objectType, artist);
        JSONObject Fields = FieldImpl.returnJsonFields(results);
        JSONObject image = (JSONObject) Fields.get("image_of_object");

        // getting each of the values from field
        String title = (String) Fields.get("title_of_object");
        String artistName = (String) Fields.get("artist"); // some had to be renamed so they dont conflict with the ones
                                                           // from earlier
        String type = (String) Fields.get("object_type");
        String mediums = (String) Fields.get("medium");
        String body = (String) Fields.get("label");
        String source = (String) Fields.get("source");
        String year_of_creation = (String) Fields.get("year_of_creation");
        String current_display_status = (String) Fields.get("current_display_status");
        String imageID = (String) image.get("id");
        String imgSource = "https://opendata.bristol.gov.uk/explore/dataset/open-data-gallery-3-european-old-masters/files/"
                + imageID + "/300";

        // adding each var to the template "single"
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
