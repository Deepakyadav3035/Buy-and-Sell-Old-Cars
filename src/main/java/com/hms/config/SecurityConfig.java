package com.hms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {


    private final JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        http.csrf().disable().cors().disable();

        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

        http.authorizeHttpRequests().anyRequest().permitAll();

//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/hms/user/Manager-signup","/api/v1/hms/user/Sells_manager-signup","/api/v1/hms/user/signin","/api/v1/hms/user/login-otp")
//                .permitAll()
//                .requestMatchers("/api/v1/car").hasAnyRole("Admin","Manager")
//                .anyRequest()
//                .authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
