package com.finalproject.Backend.mapper;

import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.model.User;

public class ExperienceMapper {

    public static Experience toEntity(ExperienceRequestDTO dto, User user) {
        Experience exp = new Experience();
        exp.setTitle(dto.getTitle());
        exp.setHost(dto.getHost());
        exp.setCategory(dto.getCategory());
        exp.setDescription(dto.getDescription());
        exp.setLocation(dto.getLocation());
        exp.setPrice(dto.getPrice());
        exp.setStartDate(dto.getStartDate());
        exp.setEndDate(dto.getEndDate());
        exp.setUser(user);
        return exp;
    }

    public static ExperienceResponseDTO toDTO(Experience exp) {
        return new ExperienceResponseDTO(
                exp.getId(),
                exp.getTitle(),
                exp.getHost(),
                exp.getCategory(),
                exp.getDescription(),
                exp.getLocation(),
                exp.getPrice(),
                exp.getStartDate(),
                exp.getEndDate(),
                exp.getUser().getId(),
                exp.getUser().getName()
        );
    }
}
 