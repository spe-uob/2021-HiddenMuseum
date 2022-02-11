package uk.ac.bristol.hiddenmuseum.requests;

import org.springframework.web.client.RestTemplate;

public class SchemaRequestBuilder implements RequestBuilder<SchemaResponse> {

    private final String base;
    private final String dataset;

    /**
     * Create a schema request builder
     *
     * @param base URL where datasets can be found (do not include API endpoints)
     * @param dataset dataset to search
     */
    public SchemaRequestBuilder(String base, String dataset) {
        this.base = base;
        this.dataset = dataset;
    }

    @Override
    public String getUrl() {
        var output = new StringBuilder();
        output.append(this.base);
        output.append("/api/datasets/1.0/");
        output.append(this.dataset);
        return output.toString();
    }

    @Override
    public SchemaResponse sendRequest() {
        RestTemplate restTemplate = new RestTemplate();
        SchemaResponse response = restTemplate.getForObject(this.getUrl(), SchemaResponse.class);
        return response;
    }
    
}
