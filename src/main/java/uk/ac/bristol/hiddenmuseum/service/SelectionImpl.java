package uk.ac.bristol.hiddenmuseum.service;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SelectionImpl implements Selection{
    @Override
    public List<String> getKey(String str) {
        List artists=new ArrayList();
        for(String key:getSelection(str).keySet()){
            artists.add(key);
        }
        return artists;
    }

    @Override
    public Map<String, String> getSelection(String url) {
        //"C:\\Users\\lenovo\\Desktop\\skeleton\\skeleton\\doc\\ArtistMapper.txt"
        String fileName = url;
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
