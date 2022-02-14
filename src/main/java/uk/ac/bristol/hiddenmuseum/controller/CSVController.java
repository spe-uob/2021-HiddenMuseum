package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.bristol.hiddenmuseum.service.CSVBuilder;

/**
 * Controller to export CSV File
 */
@RestController
public class CSVController {

    /**
     *
     * Returns the page with the exported CSV
     *
     * @return appropriate error page
     */
    @RequestMapping("/export")
    public String getCSV(@RequestParam(defaultValue = "", required = false) String q) {
        CSVBuilder csvBuilder = new CSVBuilder();
        String csv = csvBuilder.getCSV(q);
        return csv;
    }
}
