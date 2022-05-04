
package uk.ac.bristol.hiddenmuseum.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.bristol.hiddenmuseum.requests.SchemaRequestBuilder;
import uk.ac.bristol.hiddenmuseum.requests.SearchRecord;
import uk.ac.bristol.hiddenmuseum.requests.SearchRequestBuilder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Controller
public class InfographicController {
    @GetMapping("/infographics")
    public String Info(@RequestParam(defaultValue = "open-data-gallery-3-european-old-masters", required = false)
                                   String dataset,
                                   Model model) {
        var srq = new SearchRequestBuilder("https://opendata.bristol.gov.uk/",
                dataset);
        srq.setLimit(999);
        var response = srq.sendRequest();
        var scrq = new SchemaRequestBuilder("https://opendata.bristol.gov.uk/", dataset);
        var schResponse = scrq.sendRequest();
        var fieldList = schResponse.fields.stream().map(f -> f.name).toArray();
        var listRecords = response.records;
        List<Integer> intDates = new ArrayList<Integer>();
        ArrayList<String> ListOfTitles = new ArrayList<String>();
        HashMap<String, String> MapofTitlesToID = new HashMap<>();

        HashMap<String,	Pair<String, Integer>> MapWithIdDateTitle = new HashMap<>();
        List<Integer> datesOfItems = new ArrayList<Integer>();
        HashMap<String, Integer> linksToItems = new HashMap<>();
        List<Integer> datesOfItemsAD = new ArrayList<Integer>();
        List<Integer> datesOfItemsBC = new ArrayList<Integer>();
        List<Integer> linksToItemsAD = new ArrayList<Integer>();
        List<Integer> linksToItemsBC = new ArrayList<Integer>();

        String titleField = "";
        String yearField = "";
        String imageField = "";
        boolean titleFound = false;
        boolean imageFound = false;
        boolean yearFound = false;

        for(Object field : fieldList)  {
            System.out.println(field);
            String regex = "title.|.title.|title|Title.|.Title.|Title";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher((String) field);
            if(matcher.find()) {
                titleField = (String) field;
                titleFound = true;
            }
            regex = "year.|.year.|year|Year.|.Year.|Year|date|.date.|date.";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher((String) field);
            if(matcher.find()) {
                yearField = (String) field;
                yearFound = true;
            }
            regex = "image.|.image.|image|Image.|.Image.|Image";
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher((String) field);
            if(matcher.find()) {
                imageField = (String) field;
                imageFound = true;
            }

            if(titleFound && yearFound && imageFound)    {
                continue;
            }
        }
        System.out.println("=========================");
        System.out.println(yearField);
        System.out.println(titleField);

        /* find the highest and lowest dates */
        Integer high = -9999;
        Integer low = 9999;
        for (SearchRecord record : listRecords) {
            String date = "";
            try {
                date = record.fields.get(yearField).toString();
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
                    MapWithIdDateTitle.put(record.recordid.toString(),Pair.of(record.fields.get(titleField).toString(),dateFound));
                    System.out.println(record.recordid.toString());
                } catch (Exception e) {
                    linksToItems.put("", (dateFound));
                    MapWithIdDateTitle.put("",Pair.of(record.fields.get(titleField).toString(),dateFound));
                }
                if (!(datesOfItems.contains((dateFound)))) {
                    datesOfItems.add((dateFound));
                    try {
                        //ListOfTitles.add(record.fields.get(titleField).toString());
                        MapofTitlesToID.put(record.recordid.toString(),record.fields.get(titleField).toString());
                        //System.out.println(MapofTitlesToID);
                    } catch (Exception e) {
                        MapofTitlesToID.put(record.recordid.toString(),"");
                        // ignore as it just means it doesn't have title_of_object
                    }
                } //else {
                    //String x = ListOfTitles.get(datesOfItems.indexOf((dateFound)));
                    //try {
                    //    ListOfTitles.set(datesOfItems.indexOf((dateFound)),
                     //           x + " | " + record.fields.get(titleField).toString());
                    //} catch (Exception e) {
                        // ignore as it just means it doesn't have title_of_object
                    //}
                //}
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
            usedDates.add(aa);
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(linksToItems.entrySet());
        List<Map.Entry<String, String>> list2 = new LinkedList<Map.Entry<String, String>>(MapofTitlesToID.entrySet());
        List<Map.Entry<String, Pair<String, Integer>>> list3 = new LinkedList<Map.Entry<String, Pair<String, Integer>>>(MapWithIdDateTitle.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        // Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.sort(list2, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                    Map.Entry<String, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.sort(list3, new Comparator<Map.Entry<String,  Pair<String, Integer>>>() {
            public int compare(Map.Entry<String,  Pair<String, Integer>> o1,
                    Map.Entry<String,  Pair<String, Integer>> o2) {
                return (o1.getValue().getLeft().compareTo(o2.getValue().getLeft()));
            }
        });
        Collections.sort(list3, new Comparator<Map.Entry<String,  Pair<String, Integer>>>() {
            public int compare(Map.Entry<String,  Pair<String, Integer>> o1,
                    Map.Entry<String,  Pair<String, Integer>> o2) {
                return (o1.getValue().getRight().compareTo(o2.getValue().getRight()));
            }
        });
        Map<String, Pair<String, Integer>> sortedMap3 = new LinkedHashMap<String, Pair<String, Integer>>();
        for (Map.Entry<String, Pair<String, Integer>> entry : list3) {
            sortedMap3.put(entry.getKey(), entry.getValue());
        }
        System.out.println("start");
        for (Map.Entry<String, Pair<String, Integer>> d : sortedMap3.entrySet()) {
            System.out.println(d);
        }
        System.out.println("end");
        // 3. Loop the sorted list and put it into a new insertion order Map
        // LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
         Map<String, String> sortedMap2 = new LinkedHashMap<String, String>();
         for (Map.Entry<String, String> entry : list2) {
             sortedMap2.put(entry.getKey(), entry.getValue());
         }
        
        // for (Integer aa : datesOfItems){
Integer TempDate = 99999999;
        for (Map.Entry<String, Pair<String, Integer>> d : sortedMap3.entrySet()) {
            if (datesOfItems.contains(d.getValue().getRight())) {
                ids.add(d.getKey());
                System.out.println("old");
                System.out.println(TempDate);
                System.out.println(d.getValue().getRight());
                if (d.getValue().getRight().equals(TempDate)){
                    System.out.println("Bruh");
                    ListOfTitles.set(ListOfTitles.size()-1, ListOfTitles.get(ListOfTitles.size()-1) + " | " + d.getValue().getLeft());
                }
                else{
                    ListOfTitles.add(d.getValue().getLeft());
                }
                TempDate = d.getValue().getRight();
                System.out.println("new");
                System.out.println(TempDate);
            }
         }
        
       /* for (Map.Entry<String, Integer> a : sortedMap.entrySet()) {
            // System.out.println(a.getValue());
            if (datesOfItems.contains(a.getValue())) {
                List<String> TempIdList = new ArrayList<String>();
                Integer date = a.getValue();
                System.out.println(a.getKey());
                sortedMap2.forEach((key, value) -> {
                    if (value == "bruh2") {
                        ids.add(a.getKey());
                    }});


                for (Map.Entry<String, String> b : sortedMap2.entrySet()) {
                    if (b.getValue()== "Saints Francis and Anthony Abbot"){
                        System.out.println("HHHMMMMH");
                    }
                    //System.out.println(b.getValue());
                    //if(b.getValue()==date){
                    //    TempIdList.add(b.getKey());
                    //}
                }
                //for (Map.Entry<String, String> c : sortedMap2.entrySet()) {
                  //  if(TempIdList.contains(c.getKey())){
                   //     ids.add(a.getKey());
                  //  }
                //}

                  */ //}
        //}
        Collections.sort(datesOfItems);
        // System.out.println(ids.get(0));
        // adding the data to the model and outputting it for developmental purposes
        model.addAttribute("datesToInclude", datesToInclude);
        model.addAttribute("numOfDates", numOfDates);
        model.addAttribute("ListOfTitles", ListOfTitles);
        model.addAttribute("datesOfItems", datesOfItems);
        model.addAttribute("usedDates", usedDates);
        model.addAttribute("ids", ids);
        model.addAttribute("dataset", dataset);
        model.addAttribute("titleField", titleField);
        model.addAttribute("imageField", imageField);
         System.out.println(datesToInclude);
         System.out.println(numOfDates);
         System.out.println(ListOfTitles);
         System.out.println(datesOfItems);
         System.out.println(usedDates);
         System.out.println(ids);
        return "infographics";
    }
}
