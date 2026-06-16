package com.giovannaabreu.petshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500") // <--- Coloque aqui a URL do seu frontend
                .allowedMethods("HEAD", "PUT", "POST", "PATCH", "DELETE", "GET")
                .allowedHeaders("*") // permite todos os headers
                .allowCredentials(true); // se precisar enviar cookies ou auth
            }
        };
    }
}