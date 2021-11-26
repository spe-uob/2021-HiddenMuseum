package com.hiddenmuseumdemo.controller;

import com.hiddenmuseumdemo.service.DemoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
//need to use @RestController not @Controller for some reason,
//it doesnt work otherwise.
//to get over this use the modelAndView to return the template
public class SearchController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/search")
    public ModelAndView test(
            @RequestParam(defaultValue="Oil on canvas", required=false) String medium,
            @RequestParam(defaultValue="Painting", required=false) String objectType,
            @RequestParam(defaultValue="Lucien PISSARRO", required=false) String artist)
    {
        System.out.println("hello world");
        System.out.println(medium);
        System.out.println(objectType);
        System.out.println(artist);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        String results = demoService.getDemoInfo(medium,objectType,artist);
        Object data = JSONValue.parse(results);
        JSONObject jsonData = (JSONObject)data;
        JSONArray AllRecords = (JSONArray)jsonData.get("records");
        System.out.println("hu");
        JSONObject ArtPiece = (JSONObject)AllRecords.get(0);
        System.out.println("hi");
        JSONObject Fields = (JSONObject)ArtPiece.get("fields");
        System.out.println("HI");
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
        System.out.println("HELLO");
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

