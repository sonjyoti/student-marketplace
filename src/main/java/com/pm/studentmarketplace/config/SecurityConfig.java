package com.pm.studentmarketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                "/",
                                "/h2-console/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers("/seller/**").hasRole("SELLER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(csrf->csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers->headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .formLogin(form->form
                        .defaultSuccessUrl("/seller/dashboard", true)
                        .permitAll()
                );
        return http.build();
    }
}

// permitAll() -> Allows public access
// ignoringRequestMatchers() -> H2 uses POST without CSRF
// frameOptions().sameOrigin() -> H2 console uses iframe