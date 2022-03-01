package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.ac.bristol.hiddenmuseum.requests.SchemaRequestBuilder;
import uk.ac.bristol.hiddenmuseum.service.SelectionImpl;

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
    @Autowired
    private SelectionImpl selection;
    @GetMapping("/")
    public String index(String name, Model model)  {
        var srq = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        var response = srq.sendRequest();
        var fieldList = response.fields.stream().map(f -> f.name).toArray();
        model.addAttribute("artists",selection.getKey("src/main/resources/docs/ArtistMapper.txt"));
        model.addAttribute("medium",selection.getKey("src/main/resources/docs/medium.txt"));
        model.addAttribute("objectType",selection.getKey("src/main/resources/docs/objectType.txt"));
        model.addAttribute("fieldList", fieldList);
        model.addAttribute("name", name);
        return "index";
    }

}


