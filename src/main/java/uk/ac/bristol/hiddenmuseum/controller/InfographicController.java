
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
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(date);
            boolean matchFound = matcher.find();
            
            if (matchFound){
                System.out.println(matcher.group()); 
                Pattern AD = Pattern.compile("AD", Pattern.CASE_INSENSITIVE);
                Pattern BC = Pattern.compile("BC", Pattern.CASE_INSENSITIVE);
                Matcher matcherAD = AD.matcher(date);
                Matcher matcherBC = BC.matcher(date);
                if (matcherAD.find()){

                }
                else if (matcherAD.find()){
                    
                }
                else{

                }
            }
            if (date.length() == 4) { /*
                                       * only add if its just the 4 numbers, other formats exist like "about 1970" but
                                       * those are ignored
                                       */

                intDates.add(Integer.parseInt(date));
                try {
                    linksToItems.put(record.fields.get("recordid").toString(), Integer.parseInt(date));
                } catch (Exception e) {
                    linksToItems.put("", Integer.parseInt(date));
                }
                if (Integer.parseInt(date) > high) {
                    high = Integer.parseInt(date);
                }
                if (Integer.parseInt(date) < low) {
                    low = Integer.parseInt(date);
                }
                // if its not been added to the lists add it and if there is already one just
                // add it to the String list
                if (!(datesOfItems.contains(Integer.parseInt(date)))) {
                    datesOfItems.add(Integer.parseInt(date));
                    try {
                        ListOfTitles.add(record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        // ignore as it just means it doesn't have title_of_object
                    }
                } else {
                    String x = ListOfTitles.get(datesOfItems.indexOf(Integer.parseInt(date)));
                    try {
                        ListOfTitles.set(datesOfItems.indexOf(Integer.parseInt(date)),
                                x + " | " + record.fields.get("title_of_object").toString());
                    } catch (Exception e) {
                        // ignore as it just means it doesn't have title_of_object
                    }
                }
            }
            // split the date by ' ' so if the date for instance is "about 1984" then it'll
            // become "about" and "1984" and we just ignore about
            String[] arrofDate = date.split(" ");
            try {
                // Add some of alternate dates that start with either about or after
                if (arrofDate[1].length() == 4 && (arrofDate[0].equals("about") || arrofDate[0].equals("About")
                        || arrofDate[0].equals("After") || arrofDate[0].equals("after"))) {
                    intDates.add(Integer.parseInt(arrofDate[1]));
                    try {
                        linksToItems.put(record.fields.get("recordid").toString(), Integer.parseInt(arrofDate[1]));
                    } catch (Exception e) {
                        linksToItems.put("", Integer.parseInt(arrofDate[1]));
                    }
                    if (Integer.parseInt(arrofDate[1]) > high) {
                        high = Integer.parseInt(arrofDate[1]);
                    }
                    if (Integer.parseInt(arrofDate[1]) < low) {
                        low = Integer.parseInt(arrofDate[1]);
                    }
                    // if its not been added to the lists add it and if there is already one just
                    // add it to the String list
                    if (!(datesOfItems.contains(Integer.parseInt(arrofDate[1])))) {
                        datesOfItems.add(Integer.parseInt(arrofDate[1]));
                        try {
                            ListOfTitles.add(record.fields.get("title_of_object").toString());
                        } catch (Exception e) {
                            // ignore as it just means it doesn't have title_of_object
                        }
                    } else {
                        String x = ListOfTitles.get(datesOfItems.indexOf(Integer.parseInt(arrofDate[1])));
                        try {
                            ListOfTitles.set(datesOfItems.indexOf(Integer.parseInt(arrofDate[1])),
                                    x + " | " + record.fields.get("title_of_object").toString());
                        } catch (Exception e) {
                            // ignore as it just means it doesn't have title_of_object
                        }
                    }
                }
            } catch (Exception e) {
                // ignore as it just means it doesnt have an alternate date form
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
        //sort elements by values  
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(linksToItems.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to both lists
        for (Map.Entry<String, Integer> aa : list) {
            usedDates.add(aa.getValue());
            ids.add(aa.getKey());
        }

        // adding the data to the model and outputting it for developmental purposes
        model.addAttribute("datesToInclude", datesToInclude);
        model.addAttribute("numOfDates", numOfDates);
        model.addAttribute("ListOfTitles", ListOfTitles);
        model.addAttribute("datesOfItems", datesOfItems);
        model.addAttribute("usedDates", usedDates);
        model.addAttribute("ids", ids);
        //System.out.println(datesToInclude);
        //System.out.println(numOfDates);
        //System.out.println(ListOfTitles);
        ///System.out.println(datesOfItems);
        //System.out.println(usedDates);
        //System.out.println(ids);
        return "infographics";
    }
}