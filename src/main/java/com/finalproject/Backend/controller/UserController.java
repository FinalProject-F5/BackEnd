package com.finalproject.Backend.controller;

import com.finalproject.Backend.dto.request.UserRequest;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.service.ExperienceService;
import com.finalproject.Backend.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
import java.util.Optional; 

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    ExperienceService experienceService;
   
   UserService userService; 
   UserRepository userRepository;

   public UserController(UserService userService, UserRepository userRepository) {
       this.userService = userService;
       this.userRepository = userRepository;
   }

   @GetMapping
   public ResponseEntity<List<User>> getAllUsers() {
       List<User> users = userService.getAllUsers();  
       return ResponseEntity.ok(users);
   }



   @PostMapping 
   public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
       User user = userService.createUser(userRequest); 
       return ResponseEntity.ok(user);
   }

   
    @GetMapping("/myexperiences")
    public ResponseEntity<List<ExperienceResponseDTO>> getExperiencesByUser() {
        Optional<User> authenticatedUser = userService.getAuthenticatedUser();
        if (!authenticatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(experienceService.getByUserId(authenticatedUser.get().getId()));
    }

}
