package uk.ac.bristol.hiddenmuseum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.client.RestTemplate;
import uk.ac.bristol.hiddenmuseum.config.RestTemplateConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert.*;


@SpringBootTest
public class HttpRequestTest {
    private final static int EXPECTED_PORT = 8080;

    @Autowired
    private ServletWebServerApplicationContext webServer;

    @Test
    public void given0AsServerPort_whenReadWebAppCtxt_thenGetThePort() {
        int port = webServer.getWebServer().getPort();
        Assertions.assertEquals(EXPECTED_PORT, port);
    }
}