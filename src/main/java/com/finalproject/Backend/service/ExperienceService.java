package com.finalproject.Backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.exception.ResourceNotFoundException;
import com.finalproject.Backend.mapper.ExperienceMapper;
import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.model.User;
import com.finalproject.Backend.repository.ExperienceRepository;
import com.finalproject.Backend.repository.UserRepository;

@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;

    public ExperienceService(ExperienceRepository experienceRepository, UserRepository userRepository) {
        this.experienceRepository = experienceRepository;
        this.userRepository = userRepository;
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

    public ExperienceResponseDTO create(ExperienceRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
        Experience experience = ExperienceMapper.toEntity(dto, user);
        return ExperienceMapper.toDTO(experienceRepository.save(experience));
    }

    public ExperienceResponseDTO update(Long id, ExperienceRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        experience.setTitle(dto.getTitle());
        experience.setLocation(dto.getLocation());
        experience.setCategory(dto.getCategory());
        experience.setDescription(dto.getDescription());
        experience.setDuration(dto.getDuration()); 
        experience.setPrice(dto.getPrice()); 
        experience.setItinerary(dto.getItinerary());
        experience.setObservation(dto.getObservation());
        experience.setHost(dto.getHost());
        experience.setEmail(dto.getEmail());
        experience.setMobile(dto.getMobile()); 
        experience.setAddInfo(dto.getAddInfo());
        experience.setUser(user);

        return ExperienceMapper.toDTO(experienceRepository.save(experience));
    }

    public void delete(Long id) {
        experienceRepository.deleteById(id);
    }
}
