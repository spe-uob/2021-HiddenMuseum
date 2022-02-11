
package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.exceptions.ParserInitializationException;

import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.*;  

@Controller
public class InfographicController {
    @GetMapping("/infographics")
    public String Info(Model model){
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        var response = srq.sendRequest();
        var listRecords = response.records;
        List<String> dates = new ArrayList<String>();
        List<Integer> intDates = new ArrayList<Integer>();
        
        /*convert the dates to integers*/
        for (int i=0;i<listRecords.size();i++ ){
            dates.add(listRecords.get(i).fields.get("year_of_creation").toString());
        }
        /*find the highest and lowest dates*/
        
        Integer high = 0;
        Integer low = 9999;
        for (String date : dates){
            if (date.length()==4){ /*only add if its just the 4 numbers, other formats exist like "about 1970" but those are ignored*/
            
                intDates.add(Integer.parseInt(date));
                if (Integer.parseInt(date) > high){
                    high = Integer.parseInt(date);
                }
                if (Integer.parseInt(date) < low){
                    low = Integer.parseInt(date);
                }
            }
        }
        /*make a list of all the numbers from highest to lowest and instantiate the frequency of each date as 0*/
        
        List<Integer> datesToInclude = new ArrayList<Integer>();
        List<Integer> numOfDates = new ArrayList<Integer>();
        for (int i=low;i<=high;i++){
            datesToInclude.add(i);
            numOfDates.add(0);
        }
        /*calculating the frequency of each date*/
        
        for(Integer date : intDates){
            numOfDates.set(date-low, numOfDates.get(date-low)+1);
        }

        model.addAttribute("datesToInclude", datesToInclude);
        model.addAttribute("numOfDates",  numOfDates);
        System.out.println(datesToInclude);
        System.out.println(numOfDates);
        return "infographics";
    }}