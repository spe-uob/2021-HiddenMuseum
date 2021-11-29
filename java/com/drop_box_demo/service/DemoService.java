package com.drop_box_demo.service;

import com.drop_box_demo.pojo.showInfo.ShowInfo;


import java.util.List;
import java.util.Map;

public interface DemoService {
    String getDemoInfo(String medium,String objectType,String artist);

    Map<String,String> dealInfo(String str);

    Map<String,String> getArtist();

    List<ShowInfo> processingRawData(String str);
}
