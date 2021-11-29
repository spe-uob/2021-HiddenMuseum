package com.drop_box_demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.drop_box_demo.pojo.showInfo.ShowInfo;
import com.drop_box_demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/showInfo")
    public String test(String medium, String artist, String objectType, Model model){
        List<ShowInfo> showInfos = demoService.processingRawData(demoService.getDemoInfo(medium, objectType, artist));
        model.addAttribute("list",showInfos);
        return "search::show";
    }
    @PostMapping("/getArtist")
    @ResponseBody
    public String getInfo(@RequestBody String str){
        return JSONObject.toJSONString(demoService.dealInfo(str));
    }
    @GetMapping("/getInfo")
    @ResponseBody
    public String getArtist(){
        return JSONObject.toJSONString(demoService.getArtist());
    }

    @GetMapping("/toSearch")
    public String toSearch(Model model){
        List artists=new ArrayList();
        for(String key:demoService.getArtist().keySet()){
            artists.add(key);
        }
        model.addAttribute("artists",artists);
        return "search";
    }
}
