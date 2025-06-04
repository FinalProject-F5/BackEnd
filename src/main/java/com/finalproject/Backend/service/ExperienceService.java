package com.finalproject.Backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.repository.ExperienceRepository;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public List<Experience> getAll() {
        return experienceRepository.findAll();
    }

    public List<Experience> getByUserId(Long userId) {
        return experienceRepository.findByUserId(userId);
    }

    public Optional<Experience> getById(Long id) {
        return experienceRepository.findById(id);
    }

    public Experience create(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience update(Long id, Experience newExp) {
        return experienceRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(newExp.getTitle());
                    existing.setHost(newExp.getHost());
                    existing.setCategory(newExp.getCategory());
                    existing.setDescription(newExp.getDescription());
                    existing.setLocation(newExp.getLocation());
                    existing.setPrice(newExp.getPrice());
                    existing.setStartDate(newExp.getStartDate());
                    existing.setEndDate(newExp.getEndDate());
                    return experienceRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Experience not found"));
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }
}
