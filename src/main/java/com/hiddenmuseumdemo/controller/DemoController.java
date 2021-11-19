package com.hiddenmuseumdemo.controller;

import com.hiddenmuseumdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;
    @GetMapping("/demo")
    public String test(String medium,String objectType,String artist){
        return demoService.getDemoInfo(medium,objectType,artist);
    }
}
