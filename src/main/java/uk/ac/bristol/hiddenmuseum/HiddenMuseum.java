package uk.ac.bristol.hiddenmuseum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hidden Museum web app
 *
 * @see <a href="https://github.com/spe-uob/2021-HiddenMuseum">spe-uob/2021-HiddenMuseum</a>
 */
@SpringBootApplication
public class HiddenMuseum {

    /**
     * Entry point for the application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HiddenMuseum.class, args);
    }

}
