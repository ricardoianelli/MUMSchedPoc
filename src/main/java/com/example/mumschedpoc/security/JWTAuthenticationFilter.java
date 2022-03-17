package com.example.mumschedpoc.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.mumschedpoc.dto.UserCredentialsDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {

        try {
            UserCredentialsDTO credentialsDTO = new ObjectMapper()
                    .readValue(req.getInputStream(), UserCredentialsDTO.class);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(credentialsDTO.getEmail(),
                            credentialsDTO.getPassword(),
                            new ArrayList<>());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        SpringSecurityUser user = (SpringSecurityUser) auth.getPrincipal();
        String username = user.getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("Username", username);
        res.addHeader("UserId", user.getId().toString());
        res.addHeader("Roles", user.getUserRoleIds().toString());
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Unauthorized\", "
                    + "\"message\": \"Incorrect credentials\", "
                    + "\"path\": \"/login\"}";
        }
    }
}