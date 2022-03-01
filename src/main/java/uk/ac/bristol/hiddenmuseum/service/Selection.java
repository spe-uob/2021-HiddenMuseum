package uk.ac.bristol.hiddenmuseum.service;

import java.util.List;
import java.util.Map;

public interface Selection {
    List<String> getKey(String str);

    Map<String,String> getSelection(String url);
}
