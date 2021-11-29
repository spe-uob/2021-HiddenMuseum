package com.drop_box_demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drop_box_demo.config.RestTemplateConfig;
import com.drop_box_demo.pojo.RawData.JsonRootBean;
import com.drop_box_demo.pojo.RawData.Records;
import com.drop_box_demo.pojo.showInfo.ShowInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DemoImpl implements  DemoService{
    @Override
    public String getDemoInfo(String medium,String objectType,String artist) {
        try{
            artist="Lucien PISSARRO";
            RestTemplate restTemplate = new RestTemplate(RestTemplateConfig.generateHttpRequestFactory());
            ResponseEntity<String> results = restTemplate.getForEntity("https://opendata.bristol.gov.uk/api/records/1.0/search/?dataset=open-data-gallery-3-european-old-masters&q=&facet=medium&facet=object_type&facet=artist&refine.medium="+medium+"&refine.object_type="+objectType+"&refine.artist="+artist, String.class);
            return results.getBody();
        }catch (Exception e){
            return e.getMessage();
        }

    }
    @Override
    public List<ShowInfo> processingRawData(String str) {
        List<ShowInfo> showInfos=new ArrayList<>();
        System.err.println(str);
        JsonRootBean jsonRootBean=JSONObject.parseObject(str,JsonRootBean.class);
        for (Records record : jsonRootBean.getRecords()) {
            ShowInfo showInfo=new ShowInfo(record.getFields().getSource(),record.getFields().getLabel(),record.getFields().getArtist(),record.getFields().getYear_of_creation(),record.getFields().getImage_of_object().getId());
            System.err.println(showInfo.getArtist());
            showInfos.add(showInfo);
        }
        return showInfos;
    }

    @Override
    public Map<String, String> dealInfo(String str) {
        JsonRootBean jsonRootBean= JSONObject.parseObject(str,JsonRootBean.class);
        Map maps=new HashMap();
        for (Records record : jsonRootBean.getRecords()) {
            maps.put(record.getFields().getArtist(),"");
        }
        return maps;
    }

    @Override
    public Map<String, String> getArtist() {
        String fileName = "C:\\config\\ArtistMapper.txt";
        File file = new File(fileName);
        Map<String, String> maps;
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            //System.err.println(sbf.toString());
            maps = (Map) JSON.parse(sbf.toString());
            return maps;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        //System.err.println(sbf.toString());

        maps = (Map) JSON.parse(sbf.toString());
        return maps;
    }


}
