package com.spendsense.splitx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.spendsense.splitx.service.CustomAuthenticationSuccessHandler;
import com.spendsense.splitx.service.CustomInvalidSessionStrategy;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomInvalidSessionStrategy customInvalidSessionStrategy;
    
    @Autowired
    private  CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
   
    
    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomInvalidSessionStrategy customInvalidSessionStrategy) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customInvalidSessionStrategy = customInvalidSessionStrategy;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest.anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2.successHandler(customAuthenticationSuccessHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout") // Define the logout URL
                        .invalidateHttpSession(true) // Invalidate session
                        .deleteCookies("JSESSIONID") // Delete cookies
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(customAuthenticationEntryPoint) // Handle authentication exceptions
                .accessDeniedHandler(customAccessDeniedHandler) // Handle access denied exceptions
                );

        
        
        return http.build();
    }
}
