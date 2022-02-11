package uk.ac.bristol.hiddenmuseum.service;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.bristol.hiddenmuseum.controller.IndexController;
import uk.ac.bristol.hiddenmuseum.requests.*;

import java.util.ArrayList;
import java.util.List;


public class CSVBuilder {
    @Autowired
    private IndexController indexController = new IndexController();

    public List<String> getFields() {
        SchemaRequestBuilder srb = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        SchemaResponse response = srb.sendRequest();
        List<String> fields = new ArrayList<>();

        for (SchemaField field : response.fields) {
            fields.add(field.name);
        }
        return fields;
        //response.fields.stream().map(f -> f.name).toArray();
    }

    public List<SearchRecord> getRecords() {
        List<SearchRecord> records = new ArrayList<>();
        SearchRequestBuilder srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        srq.setLimit(1000);
        SearchResponse response = srq.sendRequest();
        return response.records;
    }

    //tab is a delimiter for this CSV
    public String getCSV()  {
        List<String> fieldList = getFields();
        StringBuilder csv = new StringBuilder();
        for(String field : fieldList)   {
            csv.append(field);
            csv.append("\t");
        }
        List<SearchRecord> data = getRecords();
        for (SearchRecord record : data) {
            for (String field : fieldList) {
                csv.append(record.fields.getOrDefault(field, "DATA UNAVAILABLE"));
                csv.append("\t");
            }
        }

        return csv.toString();


    }
}
