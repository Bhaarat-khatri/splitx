package com.spendsense.splitx.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spendsense.splitx.entity.User;
import com.spendsense.splitx.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Autowired
	private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Get user details
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        System.out.println(oauth2User);
        // Redirect URL with user details as query parameters
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        if(userRepository.findByEmail(email) == null) {
            userRepository.save(user);
        }        
        System.out.println(email + name + "out");
        String redirectUrl = "http://localhost:3000/redirectURI";
        
        response.sendRedirect(redirectUrl);
    }
}