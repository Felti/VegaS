package com.vega.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vega.app.services.impl.UserDetailsImpl;
import com.vega.app.utils.JwtTokenUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsImpl userDetailsImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (ObjectUtils.isEmpty(authHeader)) {
			chain.doFilter(request, response);
			return;
		}

		final String login = jwtTokenUtil.getUsernameFromToken(authHeader);

		// Once we get the token validate it.
		if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			var userDetails = userDetailsImpl.loadUserByUsername(login);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(authHeader, userDetails))) {

				var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
