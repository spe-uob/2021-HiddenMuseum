package uk.ac.bristol.hiddenmuseum.requests;

import org.json.simple.JSONObject;

import java.io.Serializable;

/**
 * Object returned by a lookup request
 * Also included as part of a search response's results
 */
public class SearchRecord implements Serializable {

    public String datasetid;
    public String recordid;
    public JSONObject fields;
    public String record_timestamp;

}
