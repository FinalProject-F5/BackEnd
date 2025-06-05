package com.finalproject.Backend.mapper;



import com.finalproject.Backend.dto.request.ExperienceRequestDTO;
import com.finalproject.Backend.dto.response.ExperienceResponseDTO;
import com.finalproject.Backend.model.Experience;
import com.finalproject.Backend.model.User;

public class ExperienceMapper {

    public static Experience toEntity(ExperienceRequestDTO dto, User user) {
        Experience exp = new Experience();
        exp.setTitle(dto.getTitle());
        exp.setLocation(dto.getLocation());
        exp.setCategory(dto.getCategory());
        exp.setDescription(dto.getDescription());
        exp.setDuration(dto.getDuration()); 
        exp.setPrice(dto.getPrice()); 
        exp.setItinerary(dto.getItinerary());
        exp.setObservation(dto.getObservation());
        exp.setHost(dto.getHost());
        exp.setEmail(dto.getEmail());
        exp.setMobile(dto.getMobile()); 
        exp.setAddInfo(dto.getAddInfo());
        exp.setUser(user);

        return exp;
    }

    public static ExperienceResponseDTO toDTO(Experience exp) {
        return new ExperienceResponseDTO(
                exp.getId(),
                exp.getTitle(),
                exp.getLocation(),
        exp.getCategory(),
        exp.getDescription(),
        exp.getDuration(),
        exp.getPrice(),
        exp.getItinerary(),
        exp.getObservation(),
        exp.getHost(),
        exp.getEmail(),
        exp.getMobile(), 
        exp.getAddInfo(),
               exp.getUser().getId(),
                exp.getUser().getName()
        );
    }
}
 