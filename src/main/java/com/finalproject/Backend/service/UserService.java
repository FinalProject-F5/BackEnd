package com.finalproject.Backend.service;
 

import com.finalproject.Backend.dto.request.UserRequest;
import com.finalproject.Backend.exception.ResourceNotFoundException;
import com.finalproject.Backend.model.Country;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.UserRepository;  
import com.finalproject.Backend.repository.CountryRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService { 

   UserRepository userRepository; 
   PasswordEncoder passwordEncoder;
    CountryRepository countryRepository;


   public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CountryRepository countryRepository) {
       this.countryRepository = countryRepository;
       this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
   }

   public List<User> getAllUsers() {
       return userRepository.findAll();
   }

   public User getUserById(Long id) {
       return userRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
   }

   public Optional<User> getUserByEmail(String email) {
       return userRepository.findByEmail(email);
   }

   public User createUser(UserRequest userRequest) {
       if (userRepository.existsByEmail(userRequest.getEmail())) {
           throw new RuntimeException("El email ya está en uso");
       }

       Country country = countryRepository.findById(userRequest.getCountryCode())
            .orElseThrow(() -> new RuntimeException("Country not found"));


       User user = new User(null,
                            userRequest.getName(),
                            userRequest.getEmail(),
                            passwordEncoder.encode(userRequest.getPassword()),
                            country,
                            "",
                            "",
                            null,  
                            null);
       return userRepository.save(user);
   }

   public User updateUser(Long id, UserRequest userRequest) {
       User userFound = getUserById(id);
       if (!userFound.getEmail().equals(userRequest.getEmail())) {
           if (userRepository.existsByEmail(userRequest.getEmail())) {
               throw new RuntimeException("El email ya está en uso");
           } 
       }


        User userData = new User(id, 
                            userFound.getName(),
                            userFound.getEmail(),
                            userFound.getPassword(),
                            userFound.getCountry(),
                            userFound.getInterests(),
                            userFound.getBio(),
                            userFound.getCreatedAt(),
                            userFound.getUpdatedAt()); 

       return userRepository.save(userData);
   }

   public void deleteUser(Long id) {
       User user = getUserById(id);
       userRepository.delete(user);
   }

    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return getUserByEmail(userDetails.getUsername());
    }
   
}
