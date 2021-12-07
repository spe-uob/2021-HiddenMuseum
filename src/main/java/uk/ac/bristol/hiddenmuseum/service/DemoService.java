package uk.ac.bristol.hiddenmuseum.service;

import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

/**
 * @deprecated
 * Use the {@link SearchRequestBuilder} instead.
 */
@Deprecated
public interface DemoService {

    String getDemoInfo(String medium, String objectType, String artist);

}
