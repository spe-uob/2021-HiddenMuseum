package uk.ac.bristol.hiddenmuseum;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchResponse;


@SpringBootTest
public class SearchRequestBuilderTest {


    @Test
    public void requestBuilderBuildsAsExpectedOnNormalInput()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);

        searchRequestBuilder.setQuery("Lucien");
        String url = searchRequestBuilder.getUrl();
        assert url.equals(base + "/api/records/1.0/search/?dataset=" + dataset + "&q=Lucien" + "&rows=10" + "&start=0");
    }

    @Test
    public void requestBuilderBuildsAsExpectedOnDefaultInput()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);

        String url = searchRequestBuilder.getUrl();
        assert url.equals(base + "/api/records/1.0/search/?dataset=" + dataset + "&q=" + "&rows=10" + "&start=0");
    }

    @Test
    public void requestBuilderBuildsCorrectlyAfterUsingAllSetters()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);

        searchRequestBuilder.setQuery("bird");
        searchRequestBuilder.setLimit(18);
        searchRequestBuilder.setOffset(4);
        String url = searchRequestBuilder.getUrl();
        assert url.equals(base + "/api/records/1.0/search/?dataset=" + dataset + "&q=bird" + "&rows=18" + "&start=4");
    }

    @Test
    public void requestBuilderExcludeWorksAsExpected()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);
        searchRequestBuilder.exclude("object", "Oil on canvas");
        String url = searchRequestBuilder.getUrl();
        assert url.equals(base + "/api/records/1.0/search/?dataset=" + dataset + "&q=" + "&rows=10" + "&start=0" + "&exclude.object=Oil on canvas");
    }

    @Test
    public void requestBuilderRefineWorksAsExpected()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);
        searchRequestBuilder.refineBy("object", "painting");
        String url = searchRequestBuilder.getUrl();
        assert url.equals(base + "/api/records/1.0/search/?dataset=" + dataset + "&q=" + "&rows=10" + "&start=0" + "&refine.object=painting");
    }

    @Test
    public void requestBuilderGetsExpectedResponse()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);
        searchRequestBuilder.setQuery("bird");
        SearchResponse response = searchRequestBuilder.sendRequest();
        assert response.nhits == 1;
    }
    @Test
    public void badRequestGetsBadResponse()   {
        String base = "https://opendata.bristol.gov.uk";
        String dataset = "open-data-gallery-3-european-old-masters";
        SearchRequestBuilder searchRequestBuilder = new SearchRequestBuilder(base, dataset);
        searchRequestBuilder.setQuery("tyrannosaurus");
        SearchResponse response = searchRequestBuilder.sendRequest();
        assert response.nhits == 0;
    }

}
