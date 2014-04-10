package com.abstracta.webstore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
    																	throws AuthenticationException {
        String attempUsername = request.getParameter("j_username");
        
        request.getSession().setAttribute("attempUsername", attempUsername);
        
        return super.attemptAuthentication(request, response); 
    } 
}
