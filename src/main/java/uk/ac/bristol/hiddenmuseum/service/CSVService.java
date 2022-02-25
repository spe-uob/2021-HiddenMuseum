package uk.ac.bristol.hiddenmuseum.service;

import uk.ac.bristol.hiddenmuseum.requests.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to gather CSV File data.
 */
public class CSVService {
    /**
     *
     * gathers all the field names from the database.
     *
     * @return a list of all the field names.
     */
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
    /**
     *
     * gathers all the records that match a specific query.
     * @param query the string you want to search the database for.
     * @return a list of all the appropriate records.
     */
    public static List<SearchRecord> getRecords(String query) {
        List<SearchRecord> records = new ArrayList<>();
        SearchRequestBuilder srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        srq.setLimit(1000);
        srq.setQuery(query);
        SearchResponse response = srq.sendRequest();
        return response.records;
    }
    /**
     *
     * gathers a specific record that matches a specific record ID.
     * @param query the record ID of the item you want to look up.
     * @return the record that matches the record ID.
     */
    public static SearchRecord getRecord(String query) {
        LookupRequestBuilder lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters", query);
        SearchRecord record = lrq.sendRequest();
        return record;
    }
    /**
     *
     * generates the CSV file from the fields and the records supplied.
     * @param query the string you want to search the database for.
     * @return a CSV file containing all records that match the query provided.
     */
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
    /**
     *
     * gathers a specific record that matches a specific record ID.
     * @param query the record ID of the item you want to look up.
     * @return the CSV file corresponding to the recordID given as a query.
     */
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
