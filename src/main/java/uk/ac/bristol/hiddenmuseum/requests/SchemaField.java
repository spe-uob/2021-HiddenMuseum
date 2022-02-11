package uk.ac.bristol.hiddenmuseum.requests;

import java.io.Serializable;

/**
 * Object returned by a schema request<br />
 * Included as part of a list
 */
public class SchemaField implements Serializable {

    public String name;
    public String label;
    public String type;
    
}
