package com.spendsense.splitx.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.spendsense.splitx.service.CustomAuthenticationSuccessHandler;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	
	 private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
	        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
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
		            );
		

		return http.build();
	}
}
