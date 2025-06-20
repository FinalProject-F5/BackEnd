package com.finalproject.Backend.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import com.finalproject.Backend.security.JwtUtils;
import com.finalproject.Backend.repository.UserRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
@EnableWebSecurity
public class TestSecurityConfig {
    
    @MockBean
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Permitir todas las rutas /api/** en tests
                .anyRequest().authenticated()
            );
        
        return http.build();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils(userRepository);
    }
}
