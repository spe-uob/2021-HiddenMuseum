package uk.ac.bristol.hiddenmuseum.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Controller to handle invalid HTTP requests
 */
@Controller
public class ErrorPageController implements ErrorController {

    /**
     * Request handler for invalid HTTP requests<br>
     * Returns the correct error page depending on what the error is
     *
     * @param request faulty HTTP request
     * @return appropriate error page
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            } else return "error-default";
        }
        else return "error-default";
    }
}
