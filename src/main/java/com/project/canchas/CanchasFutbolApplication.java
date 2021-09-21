package com.project.canchas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CanchasFutbolApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CanchasFutbolApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(CanchasFutbolApplication.class, args);
    }

}
