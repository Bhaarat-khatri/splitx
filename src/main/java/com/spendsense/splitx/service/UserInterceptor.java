package com.spendsense.splitx.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Step 1: Verify the session (implicitly done by Spring Security)

		// Step 2: Get the email from the authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		String email = "";

		if (principal instanceof OidcUser) {
			// For OIDC authentication (OpenID Connect)
			OidcUser oidcUser = (OidcUser) principal;
			email = oidcUser.getEmail(); // Direct method for getting email
		} else if (principal instanceof OAuth2User) {
			// For OAuth2 authentication
			OAuth2User oauth2User = (OAuth2User) principal;
			Map<String, Object> attributes = oauth2User.getAttributes();
			email = (String) attributes.get("email");
		} else {
			// Fallback case
			throw new IllegalStateException("Unsupported principal type: " + principal.getClass().getName());
		}

		// This should be the email if you're using email as the principal
		System.out.println("Inside interceptor" + email);
		// Step 3: Get user ID from the database using the email
		Long userId = userService.findUserByEmail(email).getUserId();

		System.out.println("dfs" + userId + email);
		// Step 4: Add the user ID to the request
		request.setAttribute("userId", userId);

		return true; // Continue processing the request
	}
}
