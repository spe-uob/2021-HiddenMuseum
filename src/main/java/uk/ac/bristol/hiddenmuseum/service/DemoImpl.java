package uk.ac.bristol.hiddenmuseum.service;

import uk.ac.bristol.hiddenmuseum.config.RestTemplateConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

/**
 * @deprecated
 * Use the {@link SearchRequestBuilder} and {@link SearchRequestBuilder#sendRequest} instead
 */
@Deprecated
@Service
public class DemoImpl implements  DemoService{

    @Override
    public String getDemoInfo(String medium,String objectType,String artist) {
        try {
            //SEND REQUEST
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
            ResponseEntity<String> results = restTemplate.getForEntity("https://opendata.bristol.gov.uk/api/records/1.0/search/?dataset=open-data-gallery-3-european-old-masters&q=&facet=medium&facet=object_type&facet=artist&refine.medium="+medium+"&refine.object_type="+objectType+"&refine.artist="+artist, String.class);
            return results.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
