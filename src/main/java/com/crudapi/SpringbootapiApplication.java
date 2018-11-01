package com.crudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *  Main Application.
 *
 */
@SpringBootApplication
@EnableWebMvc
@EnableJpaAuditing
public class SpringbootapiApplication {

    /** 
     * This is the main method.
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootapiApplication.class, args);
    }
}
