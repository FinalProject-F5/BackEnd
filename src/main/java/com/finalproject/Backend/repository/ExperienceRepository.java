package com.finalproject.Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.Backend.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findByUserId(Long userId);
    List<Experience> findByCategory(String category);
    List<Experience> findByTitleContainingIgnoreCase(String title);
}
