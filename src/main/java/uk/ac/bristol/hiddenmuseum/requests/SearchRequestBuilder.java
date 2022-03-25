package uk.ac.bristol.hiddenmuseum.requests;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

/**
 * Creates requests to search the database
 */
public class SearchRequestBuilder implements RequestBuilder<SearchResponse> {

    private final String base;
    private final String dataset;

    private final HashMap<String, String> refine = new HashMap<>();
    private final HashMap<String, String> excluded = new HashMap<>();

    private String query = "";
    private Integer limit = 10;
    private Integer offset = 0;

    /**
     * Create a search request builder
     *
     * @param base URL where datasets can be found (do not include API endpoints)
     * @param dataset dataset to search
     */
    public SearchRequestBuilder(String base, String dataset) {
        this.base = base;
        this.dataset = dataset;
    }

    /**
     * Set the long query string<br>
     * Sets the `q` parameter when sending a request to the API
     *
     * @param query long query string
     * @return this object, for chaining
     */
    public SearchRequestBuilder setQuery(String query) {
        System.out.println(query);
        this.query = query;
        return this;
    }

    /**
     * Sets the maximum number of search results to show<br>
     * Sets the `row` parameter when sending a request to the API
     *
     * @param n maximum number of search results to show
     * @return this object, for chaining
     */
    public SearchRequestBuilder setLimit(int n) {
        this.limit = n;
        return this;
    }

    /**
     * Sets how many results to skip (e.g. for pagination)<br>
     * Sets the `start` parameter when sending a request to the API
     *
     * @param n how many to results to offset by
     * @return this object, for chaining
     */
    public SearchRequestBuilder setOffset(int n) {
        this.offset = n;
        return this;
    }

    /**
     * Sets a value to filter by, only including matches<br>
     * Sets the `refine.{field}` parameter when sending a request to the API
     *
     * @param field field to filter by
     * @param value value to match
     * @return this object, for chaining
     */
    public SearchRequestBuilder refineBy(String field, String value) {
        this.refine.put(field, value);
        return this;
    }

    /**
     * Sets a value to filter results by, excluding matches<br>
     * Sets the `exclude.{field}` parameter when sending a request to the API
     *
     * @param field field to filter by
     * @param value value to match
     * @return this object, for chaining
     */
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
            //System.out.println("refine enter");
            output.append("&refine.");
            output.append(key);
            output.append("=");
            output.append(this.refine.get(key));
        }

        for (String key : this.excluded.keySet()) {
            //System.out.println("exclude enter");
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
