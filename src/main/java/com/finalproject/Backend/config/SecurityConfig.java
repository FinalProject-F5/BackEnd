package com.finalproject.Backend.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.finalproject.Backend.model.Country;
import com.finalproject.Backend.repository.CountryRepository;
import com.ibm.icu.util.ULocale;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

 
    private JwtRequestFilter jwtRequestFilter;
    private CorsFilter corsFilter;

    public SecurityConfig(  JwtRequestFilter jwtRequestFilter,
                           CorsFilter corsFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.corsFilter = corsFilter; 
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/countries/all").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
    }


    @Bean
    public CommandLineRunner initCountries(CountryRepository countryRepository) {
        return args -> {
            if (countryRepository.count() == 0) {
                for (String code : ULocale.getISOCountries()) {
                    ULocale locale = new ULocale("", code);
                    String name = locale.getDisplayCountry(ULocale.getDefault());
                    countryRepository.save(new Country(code, name));
                } 
            }
        };
    }

}
