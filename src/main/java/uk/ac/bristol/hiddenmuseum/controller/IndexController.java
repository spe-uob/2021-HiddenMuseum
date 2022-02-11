package uk.ac.bristol.hiddenmuseum.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.service.DemoService;
import uk.ac.bristol.hiddenmuseum.service.FieldImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Controller for the index page of the program
 */
@Controller
public class IndexController {

    @Autowired
    private DemoService demoService;
    private JSONObject fields;

    /**
     * Request handler for the home page of the web app
     *
     * @param medium
     * @param objectType
     * @param artist
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/")
    @SuppressWarnings("unchecked")
    public String index(
            @RequestParam(defaultValue="Oil on canvas", required=false) String medium,
            @RequestParam(defaultValue="Painting", required=false) String objectType,
            @RequestParam(defaultValue="Lucien PISSARRO", required=false) String artist, String name, Model model)  {
        String results = demoService.getDemoInfo(medium,objectType,artist);
        setFields(FieldImpl.returnJsonFields(results));
        JSONObject Fields = getFields();
        Iterator<String> keys = (Iterator<String>) Fields.keySet().iterator();
        List<String> fieldList = new ArrayList<>();

        while(keys.hasNext()){
            String key = keys.next();
            fieldList.add(key);
        }

        model.addAttribute("fieldList", fieldList);
        model.addAttribute("name", name);
        return "index";
    }
    public void setFields(JSONObject field) {
        this.fields = field;
    }

    public JSONObject getFields()   {
        return this.fields;
    }

}


