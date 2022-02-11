package uk.ac.bristol.hiddenmuseum.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.bristol.hiddenmuseum.controller.IndexController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CSVBuilder {
    @Autowired
    private IndexController indexController = new IndexController();

    public JSONObject getJson() {
        return this.indexController.getFields();
    }

    //tab is a delimiter for this CSV
    public String getCSV()  {

        JSONObject json = getJson();
        System.out.println(json);
        Iterator<String> keys = (Iterator<String>) json.keySet().iterator();
        List<String> fieldList = new ArrayList<>();

        while(keys.hasNext()){
            String key = keys.next();
            fieldList.add(key);
        }
        StringBuilder csv = new StringBuilder();
        for(String field : fieldList)   {
            csv.append(field);
            csv.append("\t");
        }
        for (JSONObject jsonChild : (Iterable<JSONObject>) json.values()) {
            for (String field : fieldList) {
                csv.append(jsonChild.getOrDefault(field, "DATA UNAVAILABLE"));
                csv.append("\t");
            }
        }
        return csv.toString();


    }
}
