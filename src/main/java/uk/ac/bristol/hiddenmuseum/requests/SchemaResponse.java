package uk.ac.bristol.hiddenmuseum.requests;

import java.io.Serializable;
import java.util.*;

/**
 * Object returned by a schema request
 */
public class SchemaResponse implements Serializable {

    public String datasetid;
    public List<SchemaField> fields;
    
}
