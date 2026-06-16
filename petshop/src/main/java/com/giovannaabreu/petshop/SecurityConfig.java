package com.giovannaabreu.petshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Exemplo: desabilitando CSRF
            .authorizeHttpRequests(auth -> auth
                // --- COLOQUE SUAS REGRAS ESPECÍFICAS AQUI ---
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/login").permitAll()
                
                // --- O anyRequest() DEVE FICAR POR ÚLTIMO ---
                .anyRequest().authenticated() 
            );

        return http.build();
    }

    
    // Bean do BCryptPasswordEncoder para criptografar e validar senhas
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}