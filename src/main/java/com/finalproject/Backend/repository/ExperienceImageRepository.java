package com.finalproject.Backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.finalproject.Backend.model.ExperienceImage;

public interface ExperienceImageRepository extends JpaRepository<ExperienceImage, Long> {
    List<ExperienceImage> findByExperienceId(Long experienceId);
}