package uk.ac.bristol.hiddenmuseum.service;

import uk.ac.bristol.hiddenmuseum.requests.*;

import java.util.ArrayList;
import java.util.List;


public class CSVBuilder {

    public List<String> getFields() {
        SchemaRequestBuilder srb = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        SchemaResponse response = srb.sendRequest();
        List<String> fields = new ArrayList<>();

        for (SchemaField field : response.fields) {
            fields.add(field.name);
        }
        return fields;
    }

    public List<SearchRecord> getRecords(String query) {
        List<SearchRecord> records = new ArrayList<>();
        SearchRequestBuilder srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        srq.setLimit(1000);
        srq.setQuery(query);
        SearchResponse response = srq.sendRequest();
        return response.records;
    }

    public SearchRecord getRecord(String query) {
        LookupRequestBuilder lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters", query);
        SearchRecord record = lrq.sendRequest();
        return record;
    }

    //tab is a delimiter for this CSV
    public String getCSV(String query)  {
        List<String> fieldList = getFields();
        StringBuilder csv = new StringBuilder();
        for(String field : fieldList)   {
            csv.append(field);
            csv.append("\t");
        }
        List<SearchRecord> data = getRecords(query);
        for (SearchRecord record : data) {
            for (String field : fieldList) {
                csv.append(record.fields.getOrDefault(field, "DATA UNAVAILABLE"));
                csv.append("\t");
            }
        }

        return csv.toString();
    }

    //for the item lookup
    public String getCSVItem(String query)  {
        List<String> fieldList = getFields();
        StringBuilder csv = new StringBuilder();
        for(String field : fieldList)   {
            csv.append(field);
            csv.append("\t");
        }
        SearchRecord data = getRecord(query);
            for (String field : fieldList) {
                csv.append(data.fields.getOrDefault(field, "DATA UNAVAILABLE"));
                csv.append("\t");
            }
        return csv.toString();

    }
}
