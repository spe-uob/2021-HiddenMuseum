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

public interface RequestBuilder<T> {

    public String getUrl();
    public T sendRequest();

}
