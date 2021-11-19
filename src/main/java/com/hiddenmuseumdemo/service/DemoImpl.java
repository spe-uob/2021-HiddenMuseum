package com.hiddenmuseumdemo.service;

import com.hiddenmuseumdemo.config.RestTemplateConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class DemoImpl implements  DemoService{
    @Override
    public String getDemoInfo(String medium,String objectType,String artist) {
        try{
            //SEND REQUEST
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
            ResponseEntity<String> results = restTemplate.getForEntity("https://opendata.bristol.gov.uk/api/records/1.0/search/?dataset=open-data-gallery-3-european-old-masters&q=&facet=medium&facet=object_type&facet=artist&refine.medium="+medium+"&refine.object_type="+objectType+"&refine.artist="+artist, String.class);
            return results.getBody();
        }catch (Exception e){
            return e.getMessage();
        }

    }
}
