package com.finalproject.Backend.service;
 

import com.finalproject.Backend.dto.request.LoginRequest;
import com.finalproject.Backend.dto.response.JwtResponse;
import com.finalproject.Backend.exception.UnauthorizedException;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.security.JwtUtils; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService { 

   AuthenticationManager authenticationManager;
 
   UserRepository userRepository;
 
   JwtUtils jwtUtils;


    public AuthService(@Autowired AuthenticationManager authenticationManager,
                       @Autowired UserRepository userRepository,
                       @Autowired JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }
    
   

   public JwtResponse authenticateUser(LoginRequest loginRequest) {
       System.out.println("Starting authentication process for email: " + loginRequest.getEmail());
       
       try {
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

           System.out.println("Authentication successful. Generating JWT token.");
           
           SecurityContextHolder.getContext().setAuthentication(authentication);
           
           String jwt = jwtUtils.generateJwtToken(authentication);
           
           UserDetails userDetails = (UserDetails) authentication.getPrincipal();
           String email = userDetails.getUsername();
           
           User user = userRepository.findByEmail(email)
                   .orElseThrow(() -> {
                       System.out.println("User not found after authentication: " + email);
                       return new UnauthorizedException("Credenciales inválidas");
                   });
           
           System.out.println("Authentication and token generation completed for user ID: " + user.getId());
           
           return new JwtResponse(
                   jwt,
                   "Bearer",
                   user.getId(),
                   user.getName(),
                   user.getEmail());
       } catch (Exception e) {
           System.out.println("Error during authentication: {}" + e.getMessage());
           throw e;
       }
   }

   public boolean verifyToken(String token) {
       try {
           if (jwtUtils.validateJwtToken(token)) {
               String email = jwtUtils.getEmailFromJwtToken(token);
               return userRepository.findByEmail(email).isPresent();
           }
           return false;
       } catch (Exception e) {
           System.out.println("Error verifying token: {}" + e.getMessage());
           return false;
       }
   }
}