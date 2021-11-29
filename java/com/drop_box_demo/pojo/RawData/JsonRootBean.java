package com.drop_box_demo.pojo.RawData;

/**
 * Copyright 2021 bejson.com
 */
import java.util.List;

/**
 * Auto-generated: 2021-11-27 2:45:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int nhits;
    private Parameters parameters;
    private List<Records> records;
    public void setNhits(int nhits) {
        this.nhits = nhits;
    }
    public int getNhits() {
        return nhits;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    public Parameters getParameters() {
        return parameters;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }
    public List<Records> getRecords() {
        return records;
    }

}
