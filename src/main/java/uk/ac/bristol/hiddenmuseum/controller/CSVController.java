package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bristol.hiddenmuseum.service.CSVService;

import java.util.List;

/**
 * Controller to export CSV File
 */
@RestController
public class CSVController {
    /**
     *
     * gathers csv relating to the query given or just the empty string if none given.
     *
     * @return appropriate csv file.
     */
    @RequestMapping("/export")
    public String getCSV(
            @RequestParam(defaultValue = "", required = false) String q,
            @RequestParam(defaultValue = "" ,required = false) List<String> values,
            @RequestParam(defaultValue = "open-data-gallery-3-european-old-masters", required = false) String dataset
            ) {
        String csv = CSVService.getCSV(q, values, dataset);
        return csv;
    }
    /**
     *
     * gathers csv relating to the item given or just the empty string if none given.
     *
     * @return appropriate csv file.
     */
    @RequestMapping("/exportItem")
    public String getCSVItem(@RequestParam(defaultValue = "", required = false) String q,
                             @RequestParam(defaultValue = "open-data-gallery-3-european-old-masters", required = false) String dataset) {
        String csv = CSVService.getCSVItem(q, dataset);
        return csv;
    }
}

