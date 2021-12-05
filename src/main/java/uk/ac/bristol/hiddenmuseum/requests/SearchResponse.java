package uk.ac.bristol.hiddenmuseum.requests;

import java.io.Serializable;
import java.util.List;

public class SearchResponse implements Serializable {

    public int nhits;
    public List<SearchRecord> records;

}
