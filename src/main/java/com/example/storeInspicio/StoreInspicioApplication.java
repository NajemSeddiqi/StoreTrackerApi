package com.example.storeInspicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class StoreInspicioApplication {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/stores").allowedOrigins("https://storetrackerdeluxe.herokuapp.com");
                registry.addMapping("/api/suggestion").allowedOrigins("https://storetrackerdeluxe.herokuapp.com");
                registry.addMapping("/api/stores").allowedOrigins("http://localhost:5000");
                registry.addMapping("/api/suggestion").allowedOrigins("http://localhost:5000");
            }
        };
    }

}
