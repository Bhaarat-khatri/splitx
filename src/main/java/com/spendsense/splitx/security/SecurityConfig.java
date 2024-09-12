package com.spendsense.splitx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.spendsense.splitx.service.CustomAuthenticationSuccessHandler;
import com.spendsense.splitx.service.CustomInvalidSessionStrategy;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomInvalidSessionStrategy customInvalidSessionStrategy;

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
                .sessionManagement(session -> session
                		.invalidSessionStrategy(customInvalidSessionStrategy)
                        .maximumSessions(1) // Allow one session per user
                )
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession() // Handle session fixation
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Session creation policy
                );

        return http.build();
    }

    // Extend JSESSIONID cookie validity to 1 year
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieMaxAge(31536000); // 1 year in seconds
        serializer.setCookieName("JSESSIONID");
        return serializer;
    }
}
