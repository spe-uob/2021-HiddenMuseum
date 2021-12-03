package uk.ac.bristol.hiddenmuseum.controller;

import uk.ac.bristol.hiddenmuseum.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.*;
import org.json.simple.JSONObject;

@RestController 
//need to use @RestController not @Controller for some reason, 
//it doesnt work otherwise.
//to get over this use the modelAndView to return the template
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/demo")
    public ModelAndView test(@RequestParam String medium,@RequestParam String objectType,@RequestParam String artist){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("single");
        String results = demoService.getDemoInfo(medium,objectType,artist);
        Object data = JSONValue.parse(results);
        JSONObject jsonData = (JSONObject)data;
        JSONArray AllRecords = (JSONArray)jsonData.get("records");
        JSONObject ArtPiece = (JSONObject)AllRecords.get(0);
        JSONObject Fields = (JSONObject)ArtPiece.get("fields");
        //getting each of the values from field
        String title = (String)Fields.get("title_of_object");
        String artistName = (String)Fields.get("artist"); //some had to be renamed so they dont conflict with the ones from earlier
        String type = (String)Fields.get("object_type");
        String mediums = (String)Fields.get("medium");
        String body = (String)Fields.get("label");
        String source = (String)Fields.get("source");
        String year_of_creation = (String)Fields.get("year_of_creation");
        String current_display_status = (String)Fields.get("current_display_status");
        JSONObject image = (JSONObject)Fields.get("image_of_object");
        String imageID = (String)image.get("id");
        String imgSource = "https://opendata.bristol.gov.uk/explore/dataset/open-data-gallery-3-european-old-masters/files/" + imageID + "/300";
        //adding each var to the template "single"
        modelAndView.addObject("imgSource",imgSource);
        modelAndView.addObject("title",title);
        modelAndView.addObject("artistName",artistName);
        modelAndView.addObject("type",type);
        modelAndView.addObject("mediums",mediums);
        modelAndView.addObject("body",body);
        modelAndView.addObject("source",source);
        modelAndView.addObject("year_of_creation",year_of_creation);
        modelAndView.addObject("current_display_status",current_display_status);
        return modelAndView;
    }
}