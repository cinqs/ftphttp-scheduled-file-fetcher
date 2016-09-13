package eu.cisong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration
@Configuration
@ImportResource("classpath:beans.xml")
public class Main {
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
    
}