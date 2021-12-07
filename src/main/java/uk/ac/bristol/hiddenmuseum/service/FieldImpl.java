package uk.ac.bristol.hiddenmuseum.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.json.simple.*;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

/**
 * @deprecated
 * Use the {@link SearchRequestBuilder} instead.
 */
@Deprecated
@Service
public class FieldImpl {

    public static JSONObject returnJsonFields(String results) {
        Object data = JSONValue.parse(results);
        JSONObject jsonData = (JSONObject)data;
        JSONArray AllRecords = (JSONArray)jsonData.get("records");
        JSONObject ArtPiece = (JSONObject)AllRecords.get(0);
        JSONObject Fields = (JSONObject)ArtPiece.get("fields");
        return Fields;
    }

}