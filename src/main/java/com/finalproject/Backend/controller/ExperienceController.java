package com.finalproject.Backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.service.ExperienceService;
import com.finalproject.Backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/experiences")
@CrossOrigin(origins = "http://localhost:5173")

public class ExperienceController {

    @Autowired
    UserService userService;

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienceResponseDTO> getExperienceById(@PathVariable Long id) {
        return experienceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(@Valid @RequestBody ExperienceRequestDTO dto) {
        Optional<User> authenticatedUser = getAuthenticatedUser();
        if (!authenticatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(experienceService.create(dto, authenticatedUser.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(@PathVariable Long id,
            @Valid @RequestBody ExperienceRequestDTO dto) {
        Optional<User> authenticatedUser = getAuthenticatedUser();
        if (!authenticatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(experienceService.update(id, dto, authenticatedUser.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
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
