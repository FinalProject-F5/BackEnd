package com.finalproject.Backend.dto.response;

import java.time.LocalDate;

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
    private String host;
    private String category;
    private String description;
    private String location;
    private String price;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
    private String userName;  
}