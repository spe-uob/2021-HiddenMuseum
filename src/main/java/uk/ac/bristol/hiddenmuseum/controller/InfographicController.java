
package uk.ac.bristol.hiddenmuseum.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.exceptions.ParserInitializationException;

import uk.ac.bristol.hiddenmuseum.requests.SearchRecord;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.*;  

@Controller
public class InfographicController {
    @GetMapping("/infographics")
    public String Info(Model model){
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/", "open-data-gallery-3-european-old-masters");
        srq.setLimit(999);
        var response = srq.sendRequest();
        var listRecords = response.records;
        List<Integer> intDates = new ArrayList<Integer>();
        ArrayList<String>ListOfTitles = new ArrayList<String>();
        List<Integer> datesOfItems = new ArrayList<Integer>();
       
        /*find the highest and lowest dates*/
        
        Integer high = 0;
        Integer low = 9999;
        for (SearchRecord record : listRecords){
            String date = "";
            try {
                date = record.fields.get("year_of_creation").toString();
            } catch (Exception e) {
                //ignore
            }
            if (date.length()==4){ /*only add if its just the 4 numbers, other formats exist like "about 1970" but those are ignored*/
            
                intDates.add(Integer.parseInt(date));
                if (Integer.parseInt(date) > high){
                    high = Integer.parseInt(date);
                }
                if (Integer.parseInt(date) < low){
                    low = Integer.parseInt(date);
                }
                //if its not been added to the lists add it and if there is already one just add it to the Strign list
                if (!(datesOfItems.contains(Integer.parseInt(date)))){
                    datesOfItems.add(Integer.parseInt(date));
                    try {
                        ListOfTitles.add(record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
                else{
                    String x = ListOfTitles.get(datesOfItems.indexOf(Integer.parseInt(date)));
                    try {
                        ListOfTitles.set(datesOfItems.indexOf(Integer.parseInt(date)),x +" | "+ record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
            }
            System.out.println(date);
            String[] arrofDate = date.split(" ");
            System.out.println(arrofDate[0]);
            try {
                System.out.println(arrofDate[1]);
            } catch (Exception e) {
                //ignore as it just means it doesnt have an alternate date form
            }
            try {
                // Add some of alternate dates that start with either about or after
                if (arrofDate[1].length()==4 && (arrofDate[0].equals("about")||arrofDate[0].equals("About") ||arrofDate[0].equals("After") ||arrofDate[0].equals("after"))){
                    System.out.println("hello");
                    intDates.add(Integer.parseInt(arrofDate[1]));
                    if (Integer.parseInt(arrofDate[1]) > high){
                        high = Integer.parseInt(arrofDate[1]);
                    }
                    if (Integer.parseInt(arrofDate[1]) < low){
                        low = Integer.parseInt(arrofDate[1]);
                    }
                }
            } catch (Exception e) {
                //ignore as it just means it doesnt have an alternate date form
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
        model.addAttribute("ListOfTitles",ListOfTitles);
        model.addAttribute("datesOfItems",datesOfItems);
        System.out.println(datesToInclude);
        System.out.println(numOfDates);
        System.out.println(ListOfTitles);
        System.out.println(datesOfItems);
        return "infographics";
    }}