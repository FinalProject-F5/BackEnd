package com.finalproject.Backend.controller;


import com.finalproject.Backend.dto.request.LoginRequest;
import com.finalproject.Backend.dto.request.UserRequest;
import com.finalproject.Backend.dto.response.JwtResponse;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.service.AuthService;
import com.finalproject.Backend.service.UserService;

import jakarta.validation.Valid; 
  
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController { 
    
   AuthService authService;
 
   UserService userService;

   public AuthController(AuthService authService, UserService userService) {
       this.authService = authService;
       this.userService = userService;
   }

   @PostMapping("/login")
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       System.out.println("Login request received for email: " + loginRequest.getEmail());
       try {
           JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
           System.out.println("Login successful for user: " + jwtResponse.getEmail());
           return ResponseEntity.ok(jwtResponse);
       } catch (Exception e) {
           System.out.println("Error during login process: " + e.getMessage());
           throw e;
       }
   }

   @PostMapping("/register")
   public ResponseEntity<Void> registerUser(@Valid @RequestBody UserRequest userRequest) { 
       userService.createUser(userRequest); 
       return ResponseEntity.noContent().build();
   } 
 
}