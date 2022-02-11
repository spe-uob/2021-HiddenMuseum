package uk.ac.bristol.hiddenmuseum.requests;

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
