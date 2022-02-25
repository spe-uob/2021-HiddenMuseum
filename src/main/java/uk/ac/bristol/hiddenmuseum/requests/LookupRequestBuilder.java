package uk.ac.bristol.hiddenmuseum.requests;

import org.springframework.web.client.RestTemplate;

/**
 * Creates requests to lookup individual records
 */
public class LookupRequestBuilder implements RequestBuilder<SearchRecord> {

    private final String base;
    private final String dataset;
    private final String recordID;

    /**
     * Create a lookup request builder
     *
     * @param base URL where datasets can be found (do not include API endpoints)
     * @param dataset dataset to search
     * @param recordID record to look up
     */
    public LookupRequestBuilder(String base, String dataset, String recordID) {
        this.base = base;
        this.dataset = dataset;
        this.recordID = recordID;
    }

    @Override
    public String getUrl() {
        var output = new StringBuilder();
        output.append(this.base);
        output.append("/api/datasets/1.0/");
        output.append(this.dataset);
        output.append("/records/");
        output.append(this.recordID);
        return output.toString();
    }

    @Override
    public SearchRecord sendRequest() {
        RestTemplate restTemplate = new RestTemplate();
        SearchRecord response = restTemplate.getForObject(this.getUrl(), SearchRecord.class);
        return response;
    }
}
