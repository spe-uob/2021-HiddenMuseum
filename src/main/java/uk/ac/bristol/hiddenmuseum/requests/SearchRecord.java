package uk.ac.bristol.hiddenmuseum.requests;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class SearchRecord implements Serializable {

    public String datasetid;
    public String recordid;
    public JSONObject fields;
    public String record_timestamp;

}
