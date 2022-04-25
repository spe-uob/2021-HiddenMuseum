package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SelectController {
    @GetMapping("/getSelector")
    public List getSelector(String id){
        //System.err.println("jinry");

        return splitString(getTxt("src/main/resources/static/"+id+".txt"));
    }

    public String getTxt(String path){
        try {
            File f = new File(path);
            FileInputStream in = new FileInputStream(f);

            byte[] b = new byte[(int)f.length()];
            in.read(b);
            String temp = new String(b);
            //System.out.println(temp);
            return temp;
        } catch (Exception e) {
            //logger.error("Error Message :", e);
            return e.getMessage();
        }
    }
    public List<String> splitString(String str){
        List<String> list=new ArrayList<>();
        String substring = str.substring(0, str.length() - 1);
        //System.out.println(substring);
        String[] split = substring.split(",");
        for (String string2 : split) {
            list.add(string2);
        }
        return list;
    }
}
