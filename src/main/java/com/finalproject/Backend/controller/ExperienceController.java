package com.finalproject.Backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.service.ExperienceService;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public List<Experience> getAllExperiences() {
        return experienceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
        return experienceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Experience> getExperiencesByUser(@PathVariable Long userId) {
        return experienceService.getByUserId(userId);
    }

    @PostMapping
    public Experience createExperience(@RequestBody Experience experience) {
        return experienceService.create(experience);
    }

    @PutMapping("/{id}")
    public Experience updateExperience(@PathVariable Long id, @RequestBody Experience experience) {
        return experienceService.update(id, experience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
  