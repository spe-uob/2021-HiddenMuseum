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
    public static List<String> getFields(String dataset) {
        SchemaRequestBuilder srb = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/",
                dataset);
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
    public static List<SearchRecord> getRecords(String query, List<String> values, String dataset) {

        SearchRequestBuilder srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                dataset);
        srq.setLimit(1000);
        srq.setQuery(query);
        int len = values.size();
        for(int i = 0; ((3*i)) < len; i++) {
            if (values.get((3 * i) + 1).equals("refineBy")) {
                if(!values.get(3 * i).equals("") && !values.get((3 * i) + 2).equals(""))    {
                    srq.refineBy(values.get(3 * i), values.get((3 * i) + 2));
                }
            } else {
                if(!values.get(3 * i).equals("") && !values.get((3 * i) + 2).equals(""))    {
                    srq.exclude(values.get(3 * i), values.get((3 * i) + 2));
                }
            }
        }
        SearchResponse response = srq.sendRequest();
        return response.records;
    }
    /**
     *
     * gathers a specific record that matches a specific record ID.
     * @param query the record ID of the item you want to look up.
     * @return the record that matches the record ID.
     */
    public static SearchRecord getRecord(String query, String dataset) {
        LookupRequestBuilder lrq = new LookupRequestBuilder("https://opendata.bristol.gov.uk/",
                dataset, query);
        SearchRecord record = lrq.sendRequest();
        return record;
    }
    /**
     *
     * generates the CSV file from the fields and the records supplied.
     * @param query the string you want to search the database for.
     * @return a CSV file containing all records that match the query provided.
     */
    public static String getCSV(String query, List<String> values, String dataset)  {
        List<String> fieldList = getFields(dataset);
        StringBuilder csv = new StringBuilder();
        int i = 0;
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(field.replaceAll("\"", "\"\""));
            csv.append("\"");
            if(i++ == fieldList.size() - 1) {
                break;
            }
            csv.append(",");
        }

        csv.append("\n");

        List<SearchRecord> data = getRecords(query, values, dataset);
        for (SearchRecord record : data) {
            i = 0;
            for (String field : fieldList) {
                csv.append("\"");
                csv.append(record.fields.getOrDefault(field, "").toString().replaceAll("\"", "\"\""));
                csv.append("\"");
                if(i++ == fieldList.size() - 1) {
                    break;
                }
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
    public static String getCSVItem(String query, String dataset)  {
        List<String> fieldList = getFields(dataset);
        StringBuilder csv = new StringBuilder();
        int i = 0;
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(field.replaceAll("\"", "\"\""));
            csv.append("\"");
            if(i++ == fieldList.size() - 1) {
                break;
            }
            csv.append(",");
        }

        csv.append("\n");

        SearchRecord record = getRecord(query, dataset);
        i = 0;
        for (String field : fieldList) {
            csv.append("\"");
            csv.append(record.fields.getOrDefault(field, "").toString().replaceAll("\"", "\"\""));
            csv.append("\"");
            if(i++ == fieldList.size() - 1) {
                break;
            }
            csv.append(",");
        }

        return csv.toString();

    }
}
