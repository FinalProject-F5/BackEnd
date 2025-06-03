package com.finalproject.Backend.config;

import java.io.IOException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; 
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import com.finalproject.Backend.security.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
     private JwtUtils jwtUtils;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }
    

    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
           throws ServletException, IOException {

       String path = request.getRequestURI();
       String method = request.getMethod();

       if (method.equals("OPTIONS")) {
           response.setStatus(HttpServletResponse.SC_OK);
           chain.doFilter(request, response);
           return;
       }

       if (path.contains("/api/auth") || path.contains("/api/countries/all")) {
           chain.doFilter(request, response);
           return;
       }
       
       final String requestTokenHeader = request.getHeader("Authorization");
       
       String name = null;
       String jwtToken = null;
       
       if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
           jwtToken = requestTokenHeader.substring(7);
           try {
               name = jwtUtils.getNameFromJwtToken(jwtToken);
           } catch (IllegalArgumentException e) {
               logger.error("Unable to get JWT token");
           } catch (ExpiredJwtException e) {
               logger.error("JWT token expired");
           }
       } else {
           logger.warn("JWT Token does not begin with Bearer String");
       }
       
       if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(name);
           
           if (jwtUtils.validateJwtToken(jwtToken)) {
               UsernamePasswordAuthenticationToken authenticationToken = 
                       new UsernamePasswordAuthenticationToken(
                               userDetails, null, userDetails.getAuthorities());
               
               authenticationToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request));
               
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }
       
       chain.doFilter(request, response);
   }
}
