package uk.ac.bristol.hiddenmuseum.controller;

import uk.ac.bristol.hiddenmuseum.requests.SchemaRequestBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the index page of the program
 */
@Controller
public class IndexController {

    /**
     * Request handler for the home page of the web app
     *
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/")

    public String index(String name, Model model)  {
        var srq = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        var response = srq.sendRequest();
        var fieldList = response.fields.stream().map(f -> f.name).toArray();

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


