package uk.ac.bristol.hiddenmuseum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.bristol.hiddenmuseum.requests.LookupRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRecord;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchResponse;


@SpringBootTest
public class LookupRequestBuilderTest {

    @Test
    public void requestBuilderBuildsAsExpectedOnNormalInput()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        String record = "f67b626ecf21f59c6b319f90ea62cce75d27572c";
        LookupRequestBuilder lookupRequestBuilder = new LookupRequestBuilder(base, dataset, record);

        lookupRequestBuilder.getUrl();
        String url = lookupRequestBuilder.getUrl();
        assert url.equals(base + "/api/datasets/1.0/" + dataset + "/records/" + record);
    }

    @Test
    public void requestBuilderBuildsAsExpectedOnNoInput()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        String record = "";
        LookupRequestBuilder lookupRequestBuilder = new LookupRequestBuilder(base, dataset, record);

        lookupRequestBuilder.getUrl();
        String url = lookupRequestBuilder.getUrl();
        assert url.equals(base + "/api/datasets/1.0/" + dataset + "/records/" + record);
    }

    /*@Test
    public void requestBuilderReceivesCorrectRecord()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        String record = "f67b626ecf21f59c6b319f90ea62cce75d27572c";
        LookupRequestBuilder lookupRequestBuilder = new LookupRequestBuilder(base, dataset, record);

        lookupRequestBuilder.getUrl();
        SearchRecord response = lookupRequestBuilder.sendRequest();
        assert response.recordid.equals(record);
    }

    @Test
    public void requestBuilderReceivesCorrectNumberOfFields()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        String record = "f67b626ecf21f59c6b319f90ea62cce75d27572c";
        LookupRequestBuilder lookupRequestBuilder = new LookupRequestBuilder(base, dataset, record);

        lookupRequestBuilder.getUrl();
        SearchRecord response = lookupRequestBuilder.sendRequest();
        assert response.fields.size() == 14;
    }*/

}

