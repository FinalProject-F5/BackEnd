package com.finalproject.Backend.controller;

import com.finalproject.Backend.dto.request.UserRequest;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;
import com.finalproject.Backend.service.UserService;
import jakarta.validation.Valid; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;
 
import java.util.List; 

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
   
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

   @GetMapping("/{id}") 
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
       User user = userService.getUserById(id); 
       return ResponseEntity.ok(user);
   }

   @PostMapping 
   public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
       User user = userService.createUser(userRequest); 
       return ResponseEntity.ok(user);
   }

   @PutMapping("/{id}") 
   public ResponseEntity<?> updateUser(
           @PathVariable Long id,
           @Valid @RequestBody UserRequest userRequest) {
       
       User user = userService.updateUser(id, userRequest); 
       return ResponseEntity.ok(user);
   }

   @DeleteMapping("/{id}") 
   public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       userService.deleteUser(id);
       return ResponseEntity.ok("User successfully deleted");
   }

   

}
