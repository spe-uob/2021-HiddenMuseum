package uk.ac.bristol.hiddenmuseum.requests;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class SearchRequestBuilder implements RequestBuilder {

    private final String base;
    private final String dataset;

    private final HashMap<String, String> refine = new HashMap<>();
    private final HashMap<String, String> excluded = new HashMap<>();

    private String query = "";
    private Integer limit = 10;
    private Integer offset = 0;

    public SearchRequestBuilder(String base, String dataset) {
        this.base = base;
        this.dataset = dataset;
    }

    public SearchRequestBuilder setQuery(String query) {
        this.query = query;
        return this;
    }

    public SearchRequestBuilder setLimit(int n) {
        this.limit = n;
        return this;
    }

    public SearchRequestBuilder setOffset(int n) {
        this.offset = n;
        return this;
    }

    public SearchRequestBuilder refineBy(String field, String value) {
        this.refine.put(field, value);
        return this;
    }

    public SearchRequestBuilder exclude(String field, String value) {
        this.excluded.put(field, value);
        return this;
    }

    @Override
    public String getUrl() {
        var output = new StringBuilder();
        output.append(this.base);
        output.append("/api/records/1.0/search/?dataset=");
        output.append(this.dataset);

        output.append("&q=");
        output.append(this.query);
        output.append("&rows=");
        output.append(this.limit);
        output.append("&start=");
        output.append(this.offset);

        for (String key : this.refine.keySet()) {
            output.append("&refine.");
            output.append(key);
            output.append("=");
            output.append(this.refine.get(key));
        }

        for (String key : this.excluded.keySet()) {
            output.append("&exclude.");
            output.append(key);
            output.append("=");
            output.append(this.excluded.get(key));
        }

        return output.toString();
    }

    @Override
    public SearchResponse sendRequest() {
        RestTemplate restTemplate = new RestTemplate();
        SearchResponse response = restTemplate.getForObject(this.getUrl(), SearchResponse.class);
        return response;
    }
}
