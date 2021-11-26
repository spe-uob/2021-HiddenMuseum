package com.hiddenmuseumdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
	
    @GetMapping("/")
    public String helloWorld(@RequestParam(defaultValue="test", required=false) String name, Model model)  {
        model.addAttribute("name", name);
        return "index";
    }
    
}


