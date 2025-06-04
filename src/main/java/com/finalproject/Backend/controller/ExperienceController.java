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

import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.service.ExperienceService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

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

    @GetMapping("/user/{userId}")
    public List<ExperienceResponseDTO> getExperiencesByUser(@PathVariable Long userId) {
        return experienceService.getByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<ExperienceResponseDTO> createExperience(@Valid @RequestBody ExperienceRequestDTO dto) {
        return ResponseEntity.ok(experienceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(@PathVariable Long id,
                                                                  @Valid @RequestBody ExperienceRequestDTO dto) {
        return ResponseEntity.ok(experienceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        experienceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
