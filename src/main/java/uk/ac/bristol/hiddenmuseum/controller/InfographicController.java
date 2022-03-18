
package uk.ac.bristol.hiddenmuseum.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.exceptions.ParserInitializationException;
import org.yaml.snakeyaml.tokens.TagTuple;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.bristol.hiddenmuseum.requests.SearchRecord;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.*;

@Controller
public class InfographicController {
    @GetMapping("/infographics")
    public String Info(Model model) {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                "open-data-gallery-3-european-old-masters");
        srq.setLimit(999);
        var response = srq.sendRequest();
        var listRecords = response.records;
        List<Integer> intDates = new ArrayList<Integer>();
        ArrayList<String> ListOfTitles = new ArrayList<String>();
        List<Integer> datesOfItems = new ArrayList<Integer>();
        HashMap<String, Integer> linksToItems = new HashMap<>();
        List<Integer> datesOfItemsAD = new ArrayList<Integer>();
        List<Integer> datesOfItemsBC = new ArrayList<Integer>();
        List<Integer> linksToItemsAD = new ArrayList<Integer>();
        List<Integer> linksToItemsBC = new ArrayList<Integer>();
        /* find the highest and lowest dates */

        Integer high = -9999;
        Integer low = 9999;
        for (SearchRecord record : listRecords) {
            String date = "";
            try {
                date = record.fields.get("year_of_creation").toString();
            } catch (Exception e) {
                // ignore as it just means it doesn't have year_of_creation
            }
            Pattern pattern = Pattern.compile("[0-9]+"); // pattern match to get a date
            Matcher matcher = pattern.matcher(date);
            boolean matchFound = matcher.find();

            if (matchFound) {
                String justDate = matcher.group();
                Integer dateFound = Integer.parseInt(justDate);
                // pattern match to find if its ad or bc
                Pattern AD = Pattern.compile("AD", Pattern.CASE_INSENSITIVE);
                Pattern BC = Pattern.compile("BC", Pattern.CASE_INSENSITIVE);
                Matcher matcherAD = AD.matcher(date);
                Matcher matcherBC = BC.matcher(date);
                if (matcherAD.find()) {
                    intDates.add((dateFound));
                    datesOfItemsAD.add((dateFound));
                    if (high < (dateFound)) {
                        high = (dateFound);
                    }
                    if (low > (dateFound)) {
                        low = (dateFound);
                    }
                    ;
                } else if (matcherBC.find()) {
                    intDates.add((dateFound) * -1);
                    datesOfItemsBC.add((dateFound));
                    if (low > (dateFound) * -1) {
                        low = (dateFound) * -1; // minus 1 cus its bc
                    }
                    ;
                    if (high < (dateFound) * -1) {
                        high = (dateFound) * -1;
                    }
                    ;
                } else {
                    // presume AD if no period specified
                    intDates.add((dateFound));
                    datesOfItemsAD.add((dateFound));
                    if (high < (dateFound)) {
                        high = (dateFound);
                    }
                    if (low > (dateFound)) {
                        low = (dateFound);
                    }
                    ;
                } // adding the link to a hashmap and the titles to a list
                try {
                    linksToItems.put(record.recordid.toString(), (dateFound));
                    System.out.println(record.recordid.toString());;
                } catch (Exception e) {
                    linksToItems.put("", (dateFound));
                }
                if (!(datesOfItems.contains((dateFound)))) {
                    datesOfItems.add((dateFound));
                    try {
                        ListOfTitles.add(record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        // ignore as it just means it doesn't have title_of_object
                    }
                } else {
                    String x = ListOfTitles.get(datesOfItems.indexOf((dateFound)));
                    try {
                        ListOfTitles.set(datesOfItems.indexOf((dateFound)),
                                x + " | " + record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        // ignore as it just means it doesn't have title_of_object
                    }
                }
            }
        }
        /*
         * make a list of all the numbers from highest to lowest and instantiate the
         * frequency of each date as 0
         */

        List<Integer> datesToInclude = new ArrayList<Integer>();
        List<Integer> numOfDates = new ArrayList<Integer>();
        for (int i = low; i <= high; i++) {
            datesToInclude.add(i);
            numOfDates.add(0);
        }
        /* calculating the frequency of each date */

        for (Integer date : intDates) {
            numOfDates.set(date - low, numOfDates.get(date - low) + 1);
        }

        // need to do some finicky stuff to get the links in the correct format and
        // placement
        // make a list of all non-zero numOfDates
        // make another for all ids
        // make another for all used dates
        List<Integer> numOfDatesNonZero = new ArrayList<Integer>();
        List<String> ids = new ArrayList<String>();
        List<Integer> usedDates = new ArrayList<Integer>();
        for (Integer num : numOfDates) {
            if (num != 0) {
                numOfDatesNonZero.add(num);
            }
        }

        // put data to both lists
        for (Integer aa : numOfDatesNonZero) {
            usedDates.add(aa);}
        

        //for (Integer aa : datesOfItems){
            for (Map.Entry<String, Integer> a : linksToItems.entrySet()){
                //System.out.println(a.getValue());
                ArrayList<Integer> idsDates = new ArrayList<Integer>();
                
                var temp =( a.getValue());
                
                for (var i = 0; i<idsDates.size();i++ ){
                    if (idsDates.get(i) > temp){
                        idsDates.add(i, a.getValue()); // this is the bit that needs fixing 
                        ids.add(i,a.getKey());}} // crrently works but links are jsut in wrong places
                    }
                
                //System.out.println(ids.get(0));
            // adding the data to the model and outputting it for developmental purposes
            model.addAttribute("datesToInclude", datesToInclude);
            model.addAttribute("numOfDates", numOfDates);
            model.addAttribute("ListOfTitles", ListOfTitles);
            model.addAttribute("datesOfItems", datesOfItems);
            model.addAttribute("usedDates", usedDates);
            model.addAttribute("ids", ids);
            // System.out.println(datesToInclude);
            //System.out.println(numOfDates);
            // System.out.println(ListOfTitles);
            //System.out.println(datesOfItems);
            // System.out.println(usedDates);
            // System.out.println(ids);
            return "infographics";
        }
    }
