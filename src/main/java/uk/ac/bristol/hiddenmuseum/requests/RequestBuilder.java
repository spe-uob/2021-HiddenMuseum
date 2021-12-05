package uk.ac.bristol.hiddenmuseum.requests;

import jdk.jshell.spi.ExecutionControl;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import uk.ac.bristol.hiddenmuseum.config.RestTemplateConfig;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Request building interface
 *
 * @param <T> type of the response
 */
public interface RequestBuilder<T> {

    /**
     * Returns the URL that will be queried by the request
     *
     * @return URL
     */
    public String getUrl();

    /**
     * Send the completed request to the API, returns the response
     *
     * @return appropriate Java object for this request
     */
    public T sendRequest();

}
