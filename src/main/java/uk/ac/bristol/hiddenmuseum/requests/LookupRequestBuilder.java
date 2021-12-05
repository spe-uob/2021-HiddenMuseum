package uk.ac.bristol.hiddenmuseum.requests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LookupRequestBuilder implements RequestBuilder {

    private final String base;
    private final String dataset;
    private final String recordId;

    public LookupRequestBuilder(String base, String dataset, String recordId) {
        this.base = base;
        this.dataset = dataset;
        this.recordId = recordId;
    }

    @Override
    public String getUrl() {
        var output = new StringBuilder();
        output.append(this.base);
        output.append("/api/datasets/1.0/");
        output.append(this.dataset);
        output.append("/records/");
        output.append(this.recordId);
        return output.toString();
    }

    @Override
    public SearchRecord sendRequest() {
        RestTemplate restTemplate = new RestTemplate();
        SearchRecord response = restTemplate.getForObject(this.getUrl(), SearchRecord.class);
        return response;
    }
}
