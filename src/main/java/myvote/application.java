package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Lenovo on 3/2/2015.
 */
@ComponentScan
@EnableAutoConfiguration
public class application {
    public static void main(String [] args)
    {
        SpringApplication.run(application.class, args);
    }
}
