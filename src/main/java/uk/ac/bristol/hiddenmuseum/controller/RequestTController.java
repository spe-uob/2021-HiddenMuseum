package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bristol.hiddenmuseum.requests.RequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

@RestController
public class RequestTController {

    @GetMapping("/hi")
    public String resquestT() {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        return srq.sendRequest();
    }

}
