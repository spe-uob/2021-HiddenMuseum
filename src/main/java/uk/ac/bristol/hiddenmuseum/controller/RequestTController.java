package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.RequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

@RestController
public class RequestTController {

    @GetMapping("/hi")
    public String resquestT() {
        var lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters",
                "f67b626ecf21f59c6b319f90ea62cce75d27572c");
        var response = lrq.sendRequest();
        return response.datasetid + " " + response.recordid + " " + response.fields.toJSONString();
    }

}
