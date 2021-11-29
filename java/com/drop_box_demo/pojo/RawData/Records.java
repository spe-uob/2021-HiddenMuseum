package com.drop_box_demo.pojo.RawData;

/**
 * Copyright 2021 bejson.com
 */

/**
 * Auto-generated: 2021-11-27 2:45:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Records {

    private String datasetid;
    private String recordid;
    private Fields fields;
    private String record_timestamp;
    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }
    public String getDatasetid() {
        return datasetid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }
    public String getRecordid() {
        return recordid;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
    public Fields getFields() {
        return fields;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }
}