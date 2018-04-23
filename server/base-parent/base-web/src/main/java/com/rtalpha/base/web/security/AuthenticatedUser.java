package com.rtalpha.base.web.security;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Obtain the name of the currently authenticated user from anywhere in
 * application
 * 
 * @author Ricky
 * @since Feb 28, 2017
 */
public class AuthenticatedUser {

	/**
	 * @return the currently authenticated user
	 */
	@Nonnull
	public static String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();

		if (principal == null) {
			return null;
		}

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

	/**
	 * Clear the security context of current user and invalidate its session if
	 * provided
	 * 
	 */
	public static void invalidateCurrentUser(@Nullable HttpSession session) {
		if (session != null) {
			session.invalidate();
		}

		SecurityContextHolder.clearContext();
	}
}
