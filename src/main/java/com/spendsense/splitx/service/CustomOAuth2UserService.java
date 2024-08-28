package com.spendsense.splitx.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();


        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Extract user details from OAuth2User
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        
        // Save user details to database
        saveUserToDatabase(email, name);

        return oAuth2User;
    }

    private void saveUserToDatabase(String email, String name) {
        // Implement database save logic here
    	System.out.println("Date saved" + email + " " +  name);
    }
}
