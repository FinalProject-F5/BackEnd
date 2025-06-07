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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

//    @GetMapping("/{id}") 
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.getUserById(id); 
//        return ResponseEntity.ok(user);
//    }

   @PostMapping 
   public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
       User user = userService.createUser(userRequest); 
       return ResponseEntity.ok(user);
   }
// si vamos hacer el USER PROFILE, esta parte debe ser corregida, solo the USER logged puede modificar SU perfil, con el codigo actual puede modificar y eliminar cualquier usuario.

//    @PutMapping("/{id}") 
//    public ResponseEntity<?> updateUser(
//            @PathVariable Long id,
//            @Valid @RequestBody UserRequest userRequest) {
       
//        User user = userService.updateUser(id, userRequest); 
//        return ResponseEntity.ok(user);
//    }

//    @DeleteMapping("/{id}") 
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.ok("User successfully deleted");
//    }

   
    @GetMapping("/myexperiences")
    public ResponseEntity<List<ExperienceResponseDTO>> getExperiencesByUser() {
        Optional<User> authenticatedUser = getAuthenticatedUser();
        if (!authenticatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(experienceService.getByUserId(authenticatedUser.get().getId()));
    }

    private Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userService.getUserByEmail(userDetails.getUsername());
    }
}
