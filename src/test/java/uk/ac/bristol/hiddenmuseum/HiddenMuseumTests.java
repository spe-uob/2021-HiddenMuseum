
package uk.ac.bristol.hiddenmuseum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import uk.ac.bristol.hiddenmuseum.config.RestTemplateConfig;
import uk.ac.bristol.hiddenmuseum.controller.SearchController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HiddenMuseumTests {

    @Test
    void contextLoads() {
    }

}