package uk.ac.bristol.hiddenmuseum.service;

import uk.ac.bristol.hiddenmuseum.requests.*;
import java.util.ArrayList;
import java.util.List;

public class CSVService {

    public static List<String> getFields() {
        SchemaRequestBuilder srb = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        SchemaResponse response = srb.sendRequest();

        List<String> fields = new ArrayList<>();
        for (SchemaField field : response.fields) {
            fields.add(field.name);
        }

        return fields;
    }

    public static List<SearchRecord> getRecords(String query) {
        List<SearchRecord> records = new ArrayList<>();
        SearchRequestBuilder srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        srq.setLimit(1000);
        srq.setQuery(query);
        SearchResponse response = srq.sendRequest();
        return response.records;
    }

    public static SearchRecord getRecord(String query) {
        LookupRequestBuilder lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters", query);
        SearchRecord record = lrq.sendRequest();
        return record;
    }

    //tab is a delimiter for this CSV
    public static String getCSV(String query)  {
        List<String> fieldList = getFields();
        StringBuilder csv = new StringBuilder();
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(field.replaceAll("\"", "\"\""));
            csv.append("\"");
            csv.append(",");
        }

        csv.append("\n");

        List<SearchRecord> data = getRecords(query);
        for (SearchRecord record : data) {
            for (String field : fieldList) {
                csv.append("\"");
                csv.append(record.fields.getOrDefault(field, "").toString().replaceAll("\"", "\"\""));
                csv.append("\"");
                csv.append(",");
            }
            csv.append("\n");
        }

        return csv.toString();
    }

    //for the item lookup
    public static String getCSVItem(String query)  {
        List<String> fieldList = getFields();
        StringBuilder csv = new StringBuilder();
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(field.replaceAll("\"", "\"\""));
            csv.append("\"");
            csv.append(",");
        }

        csv.append("\n");

        SearchRecord record = getRecord(query);
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(record.fields.getOrDefault(field, "").toString().replaceAll("\"", "\"\""));
            csv.append("\"");
            csv.append(",");
        }

        return csv.toString();

    }
}
