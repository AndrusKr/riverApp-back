package by.andrus.riversappback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author AndrusKr
 */
@SpringBootApplication
@EnableCaching /* ToDO Add Redis to store cache there */
public class RiversAppBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiversAppBackApplication.class, args);
    }

}
