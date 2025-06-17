package com.finalproject.Backend.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finalproject.Backend.dto.ImageDTO;
import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.exception.ResourceNotFoundException;
import com.finalproject.Backend.mapper.ExperienceMapper;
import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.model.ExperienceImage;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.ExperienceImageRepository;
import com.finalproject.Backend.repository.ExperienceRepository;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceImageService experienceImageService;
    private final ExperienceImageRepository experienceImageRepository;

    public ExperienceService(ExperienceRepository experienceRepository, ExperienceImageService experienceImageService, ExperienceImageRepository experienceImageRepository) {
        this.experienceRepository = experienceRepository;
        this.experienceImageService = experienceImageService;
        this.experienceImageRepository = experienceImageRepository;
    }

      public List<Experience> getAllExperiences() {
       return experienceRepository.findAll();
   }

   public Experience getExperienceById(Long id) {
       return experienceRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Experience", "id", id));
   }

    public List<ExperienceResponseDTO> getAll() {
        return experienceRepository.findAll()
                .stream()
                .map(ExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExperienceResponseDTO> searchByTitle(String title) {

        if (title == null || title.trim().length() < 3) {
            return Collections.emptyList();
        }

        return experienceRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(ExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ExperienceResponseDTO> getByUserId(Long userId) {
        return experienceRepository.findByUserId(userId)
                .stream()
                .map(ExperienceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ExperienceResponseDTO> getById(Long id) {
        return experienceRepository.findById(id)
                .map(ExperienceMapper::toDTO);
    }

    public ExperienceResponseDTO create(ExperienceRequestDTO dto, User user) {
        Experience experience = ExperienceMapper.toEntity(dto, user);
        experience = experienceRepository.save(experience);
        
        try {
            if (dto.getImages() != null && !dto.getImages().isEmpty()) {
                for (ImageDTO imageDTO : dto.getImages()) {
                    String filePath = experienceImageService.saveBase64File(
                        imageDTO.getBase64(), 
                        imageDTO.getName(), 
                        imageDTO.getType()
                    );
                    
                    ExperienceImage experienceImage = new ExperienceImage();
                    experienceImage.setImageName(experienceImageService.extractFileName(filePath));
                    experienceImage.setImagePath(filePath); 
                    experienceImage.setContentType(imageDTO.getType());
                    experienceImage.setImageSize(imageDTO.getSize());
                    experienceImage.setExperience(experience);
                    
                    experienceImageRepository.save(experienceImage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error update images: " + e.getMessage());
        }
       

        return ExperienceMapper.toDTO(experience);
    }

    public ExperienceResponseDTO update(Long id, ExperienceRequestDTO dto, User user) {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        experience.setTitle(dto.getTitle());
        experience.setLocation(dto.getLocation());
        experience.setCategory(dto.getCategory());
        experience.setDescription(dto.getDescription());
        experience.setDuration(dto.getDuration()); 
        experience.setPrice(dto.getPrice()); 
      //  experience.setItinerary(dto.getItinerary());
        experience.setObservation(dto.getObservation());
        experience.setHost(dto.getHost());
        experience.setEmail(dto.getEmail());
        experience.setMobile(dto.getMobile()); 
        experience.setAddInfo(dto.getAddInfo());
        experience.setUser(user);

         if (dto.getImages() != null && !dto.getImages().isEmpty()) { 

            List<ExperienceImage> existingImages = experienceImageRepository.findByExperienceId(id);
            for (ExperienceImage img : existingImages) {
                experienceImageService.deleteFile(img.getImagePath());
                experienceImageRepository.delete(img);
            }
             
            try {
                for (ImageDTO imageDTO : dto.getImages()) {
                    String filePath = experienceImageService.saveBase64File(
                        imageDTO.getBase64(), 
                        imageDTO.getName(), 
                        imageDTO.getType()
                    );
                    
                    ExperienceImage experienceImage = new ExperienceImage();
                    experienceImage.setImageName(experienceImageService.extractFileName(filePath));
                    experienceImage.setImagePath(filePath); 
                    experienceImage.setContentType(imageDTO.getType());
                    experienceImage.setImageSize(imageDTO.getSize());
                    experienceImage.setExperience(experience);
                    
                    experienceImageRepository.save(experienceImage);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error updating images: " + e.getMessage());
            }
        }

        return ExperienceMapper.toDTO(experienceRepository.save(experience));
    }

    public void delete(Long id) {
        List<ExperienceImage> images = experienceImageRepository.findByExperienceId(id);
        for (ExperienceImage img : images) {
            experienceImageService.deleteFile(img.getImagePath());
        }
        experienceRepository.deleteById(id);
    }
}
