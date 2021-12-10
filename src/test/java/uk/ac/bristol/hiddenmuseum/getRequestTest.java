package uk.ac.bristol.hiddenmuseum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import uk.ac.bristol.hiddenmuseum.controller.SearchController;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchResponse;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
@SpringBootTest
@AutoConfigureMockMvc
public class getRequestTest {
    @Autowired
    private SearchController searchController;
    @Autowired
    private MockMvc mvc;

    @Test
    public void nonEnglishWordReturnsNoResults()    {
        Model model;
        HashMap<String, String> param = new HashMap<>();
        param.put("q" , "disponibilidade");
        searchController.search(param, model);
        SearchResponse response = (SearchResponse) model.getAttribute("response");
        assert (Objects.requireNonNull(response).nhits == 0);
    }

    @Test
    public void artistNameReturnsCorrectResults()   {

    }

}

 */
