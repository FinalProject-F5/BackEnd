package com.finalproject.Backend.dto.response;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceResponseDTO {

    private Long id;
    private String title;
    private Time duration;
    private String host;
    private String email;
    private String mobile;
    private String category;
    private String description;
    private String location;
    private BigDecimal price;
    private String itinerary;
     private String observation;
    private String addInfo;
    private Long userId;
    private String userName;  

    
}